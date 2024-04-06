package database;

import objectModels.LoginModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import utils.ConstantUtils;
import utils.GenericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class Database {
   String config = ConstantUtils.CONFIG_FILE;
    @DataProvider(name = "sqlDP")
    public Iterator<Object[]> sqlDpCollection() throws Exception {
        Collection<Object[]> dp = new ArrayList<>();

        // Load database configuration from properties file
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(config)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load database properties file");
        }

        // Get database connection details from properties
        String dbHostname = prop.getProperty("dbHostname");
        String dbUser = prop.getProperty("dbUser");
        String dbPassword = prop.getProperty("dbPassword", "");
        String dbSchema = prop.getProperty("dbSchema");
        String dbPort = prop.getProperty("dbPort", "3306");


        // Establish database connection
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM loginerrorsusername")) {

            // Process ResultSet and populate data provider
            while (resultSet.next()) {
                LoginModel lm = new LoginModel(resultSet.getString("email"),
                        resultSet.getString("password"), resultSet.getString("emptyEmailError"),
                        resultSet.getString("wrongEmailError"));
                dp.add(new Object[]{lm});
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute SQL query");
        }

        return dp.iterator();
    }

}