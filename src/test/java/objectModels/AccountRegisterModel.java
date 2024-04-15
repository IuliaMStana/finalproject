package objectModels;
import lombok.Getter;


public class AccountRegisterModel {
    // Getters and Setters

    @Getter
    private String gender;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private String date;
    @Getter
    private String email;
    @Getter
    private String company;

    private boolean isSelected;
    @Getter
    private String password;
    @Getter
    private String confirmPassword;

    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
