package com.example.test2b_jaspreet;

public class Contact_Information {

    private int id;

    private String contact_name;

    private int phone;

    private String address;

    private int age;

    public Contact_Information(int id, String contact_name, int phone, String address) {
        this.id = id;
        this.contact_name = contact_name;
        this.phone = phone;
        this.address = address;
       // this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contact_name;
    }

    public void setContactName(String contact_name) {
        this.contact_name = contact_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
