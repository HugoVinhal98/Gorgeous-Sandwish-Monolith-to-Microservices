package springboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.boot.model.source.spi.JdbcDataType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Jdbc;
import org.springframework.boot.autoconfigure.session.JdbcSessionDataSourceInitializer;

@SpringBootApplication
public class SpringbootCrudHibernateExampleApplication {
	
	//LAPTOP-A4CVM70D\SQLEXPRESS
	public static final String URL = "jdbc:sqlserver://LAPTOP-A4CVM70D\\SQLEXPRESS;databaseName=arsoft;integratedSecurity=true;";
	public static Connection connection;
 
	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudHibernateExampleApplication.class, args);
		Connect();
	}
 
	public static boolean Connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(URL);
			return true;
		} catch (ClassNotFoundException ex) {
			return false;
		} catch (SQLException ex) {
			return false;
		}
	}
 
	public static boolean Disconnect() {
		try {
			connection.close();
			return true;
		} catch (SQLException ex) {
			return false;
		}
	}
}
