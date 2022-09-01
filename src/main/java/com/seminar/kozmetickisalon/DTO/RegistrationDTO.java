package com.seminar.kozmetickisalon.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.seminar.kozmetickisalon.Constraints.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "Lozinke moraju biti jednake"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "Email mora biti jednak")
})

public class RegistrationDTO {
    

    @NotBlank(message = "Potrebno je upisati ime")
    private String firstName;

    @NotEmpty(message = "Potrebno je upisati prezime")
    private String lastName;

    @NotEmpty(message = "Potrebno je upisati email")
    @Email
    private String email;

    @NotEmpty(message = "Potrebno je upisati lozinku")
    private String password;

    @NotEmpty(message = "Potrebno je potvrditi lozinku")
    private String confirmPassword;

    @NotEmpty(message = "Potrebno je potvrditi email")
    @Email
    private String confirmEmail;

    public RegistrationDTO(String firstName, String lastName, String email, String password, String confirmPassword,
            String confirmEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.confirmEmail = confirmEmail;
    }

    public RegistrationDTO() {
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return String return the confirmEmail
     */
    public String getConfirmEmail() {
        return confirmEmail;
    }

    /**
     * @param confirmEmail the confirmEmail to set
     */
    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

}
