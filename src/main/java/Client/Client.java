package Client;

import java.time.LocalDate;

public class Client {
    String firstName;
    String lastName;
    String email;
    LocalDate age;
    Boolean isOverEighteen;
    String loanReason;

    public Client() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAge() {
        return age;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }

    public Boolean getIsOverEighteen() {
        return isOverEighteen;
    }

    public void setIsOverEighteen(Boolean overEighteen) {
        isOverEighteen = overEighteen;
    }

    public String getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }
}