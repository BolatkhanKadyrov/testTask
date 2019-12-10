package com.github.bolatkhankadyrov.helpers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    private long id;
    private String name;
    private String username;
    private String email;
    private AddressModel address;
    private String phone;
    private String website;
    private CompanyModel company;

    public UserModel(
            long id,
            String name,
            String username,
            String email,
            AddressModel address,
            String phone,
            String website,
            CompanyModel company
    ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public UserModel() {
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }

    public Long getId() {
        return id;
    }

    public UserModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressModel getAddress() {
        return address;
    }

    public UserModel setAddress(AddressModel address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public UserModel setWebsite(String website) {
        this.website = website;
        return this;
    }

    public CompanyModel getCompany() {
        return company;
    }

    public UserModel setCompany(CompanyModel company) {
        this.company = company;
        return this;
    }
}
