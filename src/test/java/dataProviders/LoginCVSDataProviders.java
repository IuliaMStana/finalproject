package dataProviders;
import com.opencsv.exceptions.CsvException;
import objectModels.LoginModel;
import objectModels.RegisterModel;
import org.testng.annotations.DataProvider;
import utils.CSVUtils;
import utils.TestDataUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class LoginCVSDataProviders {
    @DataProvider(name = "errorLoginData")
    public Iterator<Object[]> csvLoginErrorCollection() throws IOException, CsvException {
        Collection<Object[]> dp = new ArrayList<>();
        List<String[]> csvData = CSVUtils.readCsv("src//test//resources//Data//errorLoginData.csv");

        // Column indices for RegisterModel fields
        int genderPoz = 0, firstNamePoz = 1, lastNamePoz = 2, datePoz = 3, emailPoz = 4, companyPoz = 5,
                isSelectedPoz = 6, passwordPoz = 7, confirmPasswordPoz = 8;

        // Column indices for LoginModel fields
        int loginEmailPoz = 4, loginPasswordPoz = 7, errorWrongPasswordPoz = 9, errorInvalidAccPoz = 10;

        // Iterate through each line in the CSV data
        for (int i = 1; i < csvData.size(); i++) {
            String[] line = csvData.get(i);

            // Create RegisterModel
            boolean isSelected = Boolean.parseBoolean(line[isSelectedPoz]);
            RegisterModel registerModel = new RegisterModel(line[genderPoz], line[firstNamePoz],
                    line[lastNamePoz], line[datePoz], line[emailPoz], line[companyPoz], isSelected,
                    line[passwordPoz], line[confirmPasswordPoz]);
            registerModel.getAccount().setEmail(TestDataUtils.generateUniqueEmail(registerModel.getAccount().getEmail()));

            // Create LoginModel
            String loginEmail = line[loginEmailPoz];
            String loginPassword = line[loginPasswordPoz];
            String errorWrongPassword = line[errorWrongPasswordPoz];
            String errorInvalidAcc = line[errorInvalidAccPoz];
            LoginModel loginError = new LoginModel(loginEmail, loginPassword, null, null,
                    errorWrongPassword, errorInvalidAcc);

            // Add both models to the data provider
            dp.add(new Object[]{registerModel, loginError});
        }
        return dp.iterator();
    }
}