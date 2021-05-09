package io.done3app.web.user;

import io.done3app.web.validation.FieldMatch;
import io.done3app.web.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class AppUser {

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @ValidEmail
    private String email;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    public AppUser() {}

    public AppUser(@NotNull(message = "is required") @Size(min = 1, message = "is required") String email, @NotNull(message = "is required") @Size(min = 1, message = "is required") String password, @NotNull(message = "is required") @Size(min = 1, message = "is required") String matchingPassword, @NotNull(message = "is required") @Size(min = 1, message = "is required") String firstName, @NotNull(message = "is required") @Size(min = 1, message = "is required") String lastName) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
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
}
