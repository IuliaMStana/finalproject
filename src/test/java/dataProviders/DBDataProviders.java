package dataProviders;
import objectModels.LoginModel;
import org.testng.annotations.DataProvider;
import utils.ConstantUtils;
import utils.DatabaseUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class DBDataProviders {
   String config = ConstantUtils.CONFIG_FILE;
    @DataProvider(name = "login_errors_sql")
    public Iterator<Object[]> loginErrorsDpCollection() throws Exception {
        Collection<Object[]> dp = new ArrayList<>();

        DatabaseUtils databaseUtils = new DatabaseUtils();
        // db connection
        Connection connection = databaseUtils.connect();
        Statement statement = databaseUtils.getStatement(connection);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM loginerrorsusername");
        while (resultSet.next()) {
            LoginModel lm = new LoginModel(resultSet.getString("email"),
                    resultSet.getString("password"), resultSet.getString("emptyEmailError"),
                    resultSet.getString("wrongEmailError"));
            dp.add(new Object[]{lm});
        }
        return dp.iterator();
    }
}
