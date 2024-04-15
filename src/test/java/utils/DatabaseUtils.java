package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

    public class DatabaseUtils {
        String dbHostname, dbUser, dbPassword, dbSchema, dbPort;
        String configFile = ConstantUtils.CONFIG_FILE;

        public void getConnectionDetails() {
            dbHostname = ConfigUtils.getGenericElement(configFile, "dbHostname");
            dbUser = ConfigUtils.getGenericElement(configFile, "dbUser");
            dbPassword = ConfigUtils.getGenericElement(configFile, "dbPassword");
            dbSchema = ConfigUtils.getGenericElement(configFile, "dbSchema");
            dbPort = ConfigUtils.getGenericElement(configFile, "dbPort");
        }

        public Connection connect() throws SQLException {
    getConnectionDetails();
    String dbUrl = "jdbc:mysql://" + dbHostname + ":" + dbPort + "/" + dbSchema;
    return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
}
        public Statement getStatement(Connection connection) throws SQLException {
            return connection.createStatement();
        }
    }

