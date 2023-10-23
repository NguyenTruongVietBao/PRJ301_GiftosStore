/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author VietBao
 */
public class UserError {
    private String id;
    private String fullname;
    private String email;
    private String google_id;
    private String phone_number;
    private String address;
    private String password;
    private String role_id;
    private String confirmError;
    private String error;

    public UserError(String id, String fullname, String email, String google_id, String phone_number, String address, String password, String role_id, String confirmError, String error) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.google_id = google_id;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
        this.role_id = role_id;
        this.confirmError = confirmError;
        this.error = error;
    }
    
    public UserError() {
        this.id = "*";
        this.fullname = "*";
        this.email = "*";
        this.google_id = "*";
        this.phone_number = "*";
        this.address = "*";
        this.password = "*";
        this.role_id = "*";
        this.confirmError = "*";
        this.error = "*";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
}
