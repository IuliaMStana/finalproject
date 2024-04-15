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

public class RegisterCVSDataProviders {
    @DataProvider(name = "errorsRegistrationData")
    public Iterator<Object[]> csvErrorsRegistrationCollection() throws IOException, CsvException {
        Collection<Object[]> dp = new ArrayList<>();
        List<String[]> csvData = CSVUtils.readCsv("src//test//resources//Data//errorsRegistrationData.csv");

        int genderPoz = 0, firstNamePoz = 1, lastNamePoz = 2, datePoz = 3, emailPoz = 4, companyPoz = 5,
                isSelectedPoz = 6, passwordPoz = 7, confirmPasswordPoz = 8, firstNameErrorPoz = 9,
                lastNameErrorPoz = 10, emailErrorPoz = 11, passwordErrorPoz = 12, confirmPasswordErrorPoz = 13;

        //we have header on csv, in this case we will drop first line
        for (int i = 1; i < csvData.size(); i++) {
            String[] line = csvData.get(i);
            boolean isSelected = Boolean.parseBoolean(line[isSelectedPoz]);
            RegisterModel errorsRegistration = new RegisterModel(line[genderPoz], line[firstNamePoz],
                    line[lastNamePoz], line[datePoz], line[emailPoz], line[companyPoz], isSelected, line[passwordPoz],
                    line[confirmPasswordPoz], line[firstNameErrorPoz], line[lastNameErrorPoz], line[emailErrorPoz],
                    line[passwordErrorPoz], line[confirmPasswordErrorPoz]);
            dp.add(new Object[]{errorsRegistration});
        }
        return dp.iterator();
    }
}

