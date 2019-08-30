package iver.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties") //PropertySource 어노테이션을 추가해서 다른 설정파일도 사용할 수 있다. classpath는 resource 폴더를 의미
public class DatabaseConfiguration {

	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") //hikari connectionPool의 설정파일을 만듬
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean //위에서 만든 hikari connectionPool의 설정 파일을 이용해서 디비와 연결하는 데이터 소스를 생성
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println(dataSource.toString());
		return dataSource;
	}
}
