package deb.springboot.externalproperties.springprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.boot.env.EnvironmentPostProcessor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DbPropertyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> dbProps = null;
        try {
            dbProps = loadPropsFromDb();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PropertySource<?> propertySource = new MapPropertySource("dbProperties", dbProps);
        environment.getPropertySources().addFirst(propertySource); // add FIRST for highest precedence
    }

    private Map<String, Object> loadPropsFromDb() throws ClassNotFoundException {
        String dbHost = System.getProperty("db.host");
        String dbPort = System.getProperty("db.port");
        String dbService = System.getProperty("db.service");
        String dbUser = System.getProperty("db.user");
        String dbPass = System.getProperty("db.pass");
        String jdbcUrl = "jdbc:oracle:thin:@//" + dbHost + ":" + dbPort + "/" + dbService;

        Map<String, Object> props = new HashMap<>();
        Class.forName("oracle.jdbc.OracleDriver");
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PROPERTY_NAME, PROPERTY_VALUE FROM DW.APP_PROPERTIES");
            while (rs.next()) {
                props.put(rs.getString("PROPERTY_NAME"), rs.getString("PROPERTY_VALUE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load properties from DB", e);
        }
        return props;
    }

}
