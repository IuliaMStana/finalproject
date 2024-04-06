package objectModels;

public class LoginModel {
    private AccountLoginModel account;
    private String emptyEmailError;
    private String wrongEmailError;
    private String errorWrongPassword;
    private String errorInvalidAcc;


    // Constructor
    public LoginModel() {

    }

    public LoginModel(String email, String password, String emptyEmailError, String wrongEmailError,
                      String errorWrongPassword, String errorInvalidAcc) {
        this.account = new AccountLoginModel();
        this.account.setPassword(password);
        this.account.setEmail(email);

        this.emptyEmailError = emptyEmailError;
        this.wrongEmailError = wrongEmailError;
        this.errorWrongPassword = errorWrongPassword;
        this.errorInvalidAcc = errorInvalidAcc;

    }

    public LoginModel(String email, String password) {
        this.account = new AccountLoginModel();
        this.account.setPassword(password);
        this.account.setEmail(email);
    }

    public LoginModel(String email, String password, String emptyEmailError, String wrongEmailError) {
        this.account = new AccountLoginModel();
        this.account.setPassword(password);
        this.account.setEmail(email);

        this.emptyEmailError = emptyEmailError;
        this.wrongEmailError = wrongEmailError;
    }


    public AccountLoginModel getAccount() {
        return account;
    }

    public void setAccount(AccountLoginModel account) {
        this.account = account;
    }

    public String getEmptyEmailError() {
        return emptyEmailError;
    }

    public void setEmptyEmailError(String emptyEmailError) {
        this.emptyEmailError = emptyEmailError;
    }

    public String getWrongEmailError() {
        return wrongEmailError;
    }

    public void setWrongEmailError(String wrongEmailError) {
        this.wrongEmailError = wrongEmailError;
    }

    public String getErrorWrongPassword() {
        return errorWrongPassword;
    }

    public void setErrorWrongPassword(String errorWrongPassword) {
        this.errorWrongPassword = errorWrongPassword;
    }

    public String getErrorInvalidAcc() {
        return errorInvalidAcc;
    }

    public void setErrorInvalidAcc(String errorInvalidAcc) {
        this.errorInvalidAcc = errorInvalidAcc;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "account={email:" + account.getEmail() + ",password=" + account.getPassword() +
                "}, emptyEmailError='" + emptyEmailError + '\'' +
                ", wrongEmailError='" + wrongEmailError + '\'' +
                ", errorWrongPassword='" + errorWrongPassword + '\'' +
                ", errorInvalidAcc='" + errorInvalidAcc + '\'' +
                '}';
    }

}


