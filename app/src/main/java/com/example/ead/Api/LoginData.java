package com.example.ead.Api;

public class LoginData {
    private String uniqueIdentifier,password,userType;

    public LoginData(String uniqueIdentifier, String password, String userType) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.password = password;
        this.userType = userType;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
