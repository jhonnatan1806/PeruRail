package com.tsoft.bot.frontend.models;

import java.util.Date;

public class Passenger {

    private int id;
    private String firstName;
    private String surname;
    private String gender;
    private String country;
    private String typedoc;
    private String document;
    private Date birthdate;
    private String telephone;
    private String email;

    public Passenger(int id, String firstName, String surname, String gender, String country, String typedoc, String document, Date birthdate, String telephone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.typedoc = typedoc;
        this.document = document;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.email = email;
    }

    public Passenger(int id, String firstName, String surname, String gender, String country, String typedoc, String document, Date birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.typedoc = typedoc;
        this.document = document;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTypedoc() {
        return typedoc;
    }

    public void setTypedoc(String typedoc) {
        this.typedoc = typedoc;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}