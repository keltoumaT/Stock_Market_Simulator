package com.masterpiece.stockmarketsimulator.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MemberDto {


    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;

    @Size(min = 2, max = 50)
    private String firstName;

    @Size(max = 200)
    private String email;

    @Size(min = 8, max = 50)
    private String password;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}
