package dataProviders;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.Iterator;
import static utils.CSVUtils.readRegisterAndLoginData;


public class RegisterAndLoginCVSDataProviders {
    @DataProvider(name = "registerAndLoginData")
    public Iterator<Object[]> cvsRegisterPositiveData() throws IOException, CsvException {
        return readRegisterAndLoginData("src//test//resources//Data//registerAndLoginData.cvs").iterator();
    }
}
