package dataProviders;
import com.opencsv.exceptions.CsvException;
import objectModels.RegisterModel;
import org.testng.annotations.DataProvider;
import utils.CSVUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import static utils.CSVUtils.readRegisterAndLoginData;


public class RegisterAndLoginCVSDataProviders {
    @DataProvider(name = "registerAndLoginData")
    public Iterator<Object[]> cvsRegisterPositiveData() throws IOException, CsvException {
        return readRegisterAndLoginData("src//test//resources//Data//registerAndLoginData.cvs").iterator();
    }
}
