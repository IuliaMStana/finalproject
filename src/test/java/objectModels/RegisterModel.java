package objectModels;
public class RegisterModel {
    private AccountRegisterModel account;
    private String firstNameError;
    private String lastNameError;
    private String emailError;
    private String passwordError;
    private String confirmPasswordError;

    // Constructor
    public RegisterModel(){

    }

    public RegisterModel(String gender, String firstName, String lastName, String date,
                         String email, String company, boolean isSelected, String password,
                         String confirmPassword, String firstNameError, String lastNameError,
                         String emailError, String passwordError, String confirmPasswordError) {
        this.account = new AccountRegisterModel();
        this.account.setGender(gender);
        this.account.setFirstName(firstName);
        this.account.setLastName(lastName);
        this.account.setDate(date);
        this.account.setEmail(email);
        this.account.setCompany(company);
        this.account.setSelected(isSelected);
        this.account.setPassword(password);
        this.account.setConfirmPassword(confirmPassword);

        this.firstNameError= firstNameError;
        this.lastNameError = lastNameError;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
    }

    public RegisterModel(String gender, String firstName, String lastName, String date,
                         String email, String company, boolean isSelected, String password,
                         String confirmPassword){
        this.account = new AccountRegisterModel();
        this.account.setGender(gender);
        this.account.setFirstName(firstName);
        this.account.setLastName(lastName);
        this.account.setDate(date);
        this.account.setEmail(email);
        this.account.setCompany(company);
        this.account.setSelected(isSelected);
        this.account.setPassword(password);
        this.account.setConfirmPassword(confirmPassword);
    }

    public AccountRegisterModel getAccount() {
        return account;
    }

    public void setAccount(AccountRegisterModel account) {
        this.account = account;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "account={gender:" + account.getGender() + ",firstName=" + account.getFirstName() +
                ",lastName=" + account.getLastName()+ ",date=" + account.getDate() + ",email=" + account.getEmail()+
                ",company=" + account.getCompany()+ ",isSelected=" + account.isSelected() + ",password=" + account.getPassword()
                + ",confirmPassword=" + account.getConfirmPassword()+"}"+
                ", lastNameError='" + lastNameError + '\'' +
                ", emailError='" + emailError + '\'' +
                ", passwordError='" + passwordError + '\'' +
                ", confirmPasswordError='" + confirmPasswordError + '\'' +
                '}';
    }
}


