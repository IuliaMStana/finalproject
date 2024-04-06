package pageObjects;
import objectModels.AccountRegisterModel;
import objectModels.RegisterModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Objects;

public class RegistrationPage extends BasePage {
    @FindBy(xpath = "//input[@type='radio' and @value='F']")
    private WebElement gender;
    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@id='Email']")
    private WebElement email;
    @FindBy(xpath = "//input[@id='Company']")
    private WebElement companyName;
    @FindBy(xpath = "//input[@id='Password']")
    private WebElement password;
    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    private WebElement confirmPassword;
    @FindBy(xpath = "//*[@id=\"Newsletter\"]")
    private WebElement newsletterCheckbox;
    @FindBy(xpath = "//*[@id=\"register-button\"]")
    private WebElement registerButton;
    @FindBy(xpath = "//h1[text()='Register']")
    private WebElement confirmRegistrationHeader;
    @FindBy(xpath = "//div[text()='Your registration completed']")
    private WebElement confirmRegistrationBody;
    @FindBy(xpath = "//a[@class='button-1 register-continue-button']")
    private WebElement continueButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitForPageToLoad() {
        gender = wait.until(ExpectedConditions.
                visibilityOf(gender));
        firstName = wait.until(ExpectedConditions.
                visibilityOf(firstName));
        lastName = wait.until(ExpectedConditions.
                visibilityOf(lastName));
        email = wait.until(ExpectedConditions.
                visibilityOf(email));
        password = wait.until(ExpectedConditions.
                visibilityOf(password));
        companyName = wait.until(ExpectedConditions.
                visibilityOf(companyName));
        confirmPassword = wait.until(ExpectedConditions.
                visibilityOf(confirmPassword));
    }

    public void register(String genderInput, String firstNameInput, String lastNameInput,
                         String dateInput, String emailInput, String companyInput, boolean isSelected, String passwordInput,
                         String confirmPasswordInput) {
        System.out.println("Select gender: " + genderInput);
        chooseGender(genderInput);
        System.out.println("Enter firstName: " + firstName);
        enterFirstName(firstNameInput);
        System.out.println("Enter lastName: " + lastNameInput);
        enterLastName(lastNameInput);
        System.out.println("Enter date of birth: " + dateInput);
        enterDateOfBirth(dateInput);
        System.out.println("Enter email: " + emailInput);
        enterEmail(emailInput);
        System.out.println("Enter company: " + companyInput);
        enterCompany(companyInput);
        System.out.println("Newsletter: " + isSelected);
        chooseNewsletter(isSelected);
        System.out.println("Enter password: " + passwordInput);
        enterPassword(passwordInput);
        System.out.println("Enter confirmPassword" + confirmPasswordInput);
        confirmPassword(confirmPasswordInput);
        clickRegister();
    }
    public void chooseGender(String gender) {
        if (!Objects.equals(gender, "")) {
            driver.findElement(By.xpath("//input[@value='" + gender + "']")).click();
        }
    }

    public void enterFirstName(String firstNameInput) {
        firstName.clear();
        firstName.sendKeys(firstNameInput);
    }

    public void enterLastName(String lastNameInput) {
        lastName.clear();
        lastName.sendKeys(lastNameInput);
    }

    public void enterDateOfBirth(String date) {
        if (!Objects.equals(date, "")) {
            String[] substrings = date.split("\\s+");
            String day = substrings[0];
            String month = substrings[1];
            String year = substrings[2];
            Select selectDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
            selectDay.selectByValue(day);
            Select selectMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
            selectMonth.selectByVisibleText(month);
            Select selectYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
            selectYear.selectByValue(year);
        }
    }

    public void enterEmail(String emailInput) {
        email.clear();
        email.sendKeys(emailInput);
    }

    public void enterCompany(String companyInput) {
        companyName.clear();
        companyName.sendKeys(companyInput);
    }

    public void chooseNewsletter(boolean isCheckboxSelected) {
        if (isCheckboxSelected) {
            newsletterCheckbox.click();
        }
    }

    public void enterPassword(String passwordInput) {
        password.clear();
        password.sendKeys(passwordInput);
    }

    public void confirmPassword(String confirmPasswordInput) {
        confirmPassword.clear();
        confirmPassword.sendKeys(confirmPasswordInput);
    }

    public void clickRegister() {
        registerButton.click();
    }

    public void verifyRegistration() {
        wait.until(ExpectedConditions.visibilityOf(confirmRegistrationHeader));
        Assert.assertTrue(confirmRegistrationHeader.isDisplayed(), "Confirm registration header is not displayed");
        Assert.assertTrue(confirmRegistrationBody.isDisplayed(), "Confirm registration body is not displayed");
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button is not displayed");
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getErrorText(String field) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@id='" + field + "-error']")));
        return driver.findElement(By.xpath("//span[@id='" + field + "-error']")).getText().replace("\n", " ");
    }
}



