package com.example.jsononlinedemodatabase;

public class ExtractJsonData {

    private String name, password, contact, country;

    public ExtractJsonData(String name, String password, String contact, String country) {
        this.setName(name);
        this.setPassword(password);
        this.setContact(contact);
        this.setCountry(country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
