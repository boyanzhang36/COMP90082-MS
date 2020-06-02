package com.medsec.entity;

public class Radiology {
    private String id;
    private String name;
    private String contact;
    private String address;
    private String fax;
    private String website;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Radiology id(final String id) {
        this.id = id;
        return this;
    }

    public Radiology name(final String name) {
        this.name = name;
        return this;
    }

    public Radiology contact(final String contact) {
        this.contact = contact;
        return this;
    }

    public Radiology address(final String address) {
        this.address = address;
        return this;
    }

    public Radiology fax(final String fax) {
        this.fax = fax;
        return this;
    }

    public Radiology website(final String website) {
        this.website = website;
        return this;
    }
}
