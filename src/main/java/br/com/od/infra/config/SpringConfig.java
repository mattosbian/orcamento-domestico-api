package br.com.od.infra.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



@Configuration
@ComponentScan(basePackages={"br.com.od.application","br.com.od.modelo"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"br.com.od.infra.repository"})
public class SpringConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	   LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	   emf.setDataSource(dataSource());
	   emf.setPackagesToScan("br.com.od.modelo");
	   emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	   
	   Properties properties = new Properties();
	   properties.put("hibernate.show_sql" ,true);
	  // properties.put("hibernate.format_sql", true);
	   
	   emf.setJpaProperties(properties);
	   
	   return emf;
	   
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:XE");
		dataSource.setUsername("usrorca");
		dataSource.setPassword("usrorca");
		
		return dataSource;

	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
			
		JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(emf);
	    return txManager;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
