package com.appdirect.healthmonitor.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class FlywayAutoConfiguration {
	protected FlywayAutoConfiguration() {
	}

	@Configuration
	@ConditionalOnProperty(prefix = "flyway", name = "active")
	@Import(FlywayJpaDependencyConfiguration.class)
	public static class FlywayConfiguration {
		@Value("${flyway.repair}")
		private Boolean repair;
		@Autowired
		private DataSource dataSource;

		@Bean(initMethod = "migrate")
		public Flyway flyway() {
			Flyway flyway = new Flyway();
			flyway.setTable("schema_version");
			flyway.setLocations("db/migration");
			flyway.setDataSource(dataSource);
			flyway.setBaselineOnMigrate(true);
			flyway.setBaselineVersionAsString("000");
			if (Boolean.TRUE.equals(this.repair)) {
				flyway.repair();
			}
			return flyway;
		}
	}

	/**
	 * Additional configuration to ensure that {@link EntityManagerFactory} beans depend-on the flyway bean.
	 */
	@Configuration
	@ConditionalOnProperty(prefix = "flyway", name = "active")
	protected static class FlywayJpaDependencyConfiguration extends EntityManagerFactoryDependsOnPostProcessor {

		public FlywayJpaDependencyConfiguration() {
			super("flyway");
		}
	}
}
