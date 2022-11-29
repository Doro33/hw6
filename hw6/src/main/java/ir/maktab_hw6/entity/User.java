package ir.maktab_hw6.entity;

import java.time.LocalDate;

public class User {
    private final String USERNAME;
    private final String PASSWORD;
    private final String NATIONAL_CODE;
    private final LocalDate BIRTHDAY;

    public User(String USERNAME, String nationalCode, LocalDate birthday) {
        this.USERNAME = USERNAME;
        this.PASSWORD = nationalCode;
        this.NATIONAL_CODE = nationalCode;
        this.BIRTHDAY = birthday;
    }


    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getNationalCode() {
        return NATIONAL_CODE;
    }

    public LocalDate getBirthday() {
        return BIRTHDAY;
    }
    public void setId(int id) {
    }

    public String toString() {
        return "username: " + USERNAME + '\n'
                + "password: " + PASSWORD + '\n'
                + "national code: " + NATIONAL_CODE + '\n'
                + "birthday: " + BIRTHDAY;
    }


}