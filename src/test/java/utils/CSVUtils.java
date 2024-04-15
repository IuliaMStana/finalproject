package utils;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import objectModels.RegisterModel;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

    public class CSVUtils {

        public static List<String[]> readCsv(String filePath) throws IOException, CsvException {
            File file = new File(filePath);
            Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
            //csv reader
            CSVReader csvReader = new CSVReader(reader);
            return csvReader.readAll();
        }
        public static Collection<Object[]> readRegisterAndLoginData(String csvFilePath) throws IOException, CsvException {
            Collection<Object[]> dp = new ArrayList<>();
            List<String[]> csvData = CSVUtils.readCsv(csvFilePath);

            // Column indices for RegisterModel fields
            int genderPoz = 0, firstNamePoz = 1, lastNamePoz = 2, datePoz = 3, emailPoz = 4, companyPoz = 5,
                    isSelectedPoz = 6, passwordPoz = 7, confirmPasswordPoz = 8;

            // Iterate through each line in the CSV data
            for (int i = 1; i < csvData.size(); i++) {
                String[] line = csvData.get(i);

                // Create RegisterModel
                boolean isSelected = Boolean.parseBoolean(line[isSelectedPoz]);
                RegisterModel newUser= new RegisterModel(line[genderPoz], line[firstNamePoz],
                        line[lastNamePoz], line[datePoz], line[emailPoz], line[companyPoz], isSelected,
                        line[passwordPoz], line[confirmPasswordPoz]);
                newUser.getAccount().setEmail(TestDataUtils.generateUniqueEmail(newUser.getAccount().getEmail()));
                dp.add(new Object[]{newUser});

            }
            return dp;
        }
    }