/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Asano
 */
public class Users {

    private int uid;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String certif;
    

    public Users() {
    }

    public Users(int uid, String fname, String lname, String phone, String email) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertif() {
        return certif;
    }

    public void setCertif(String certif) {
        this.certif = certif;
    }



    @Override
    public String toString() {
        return "Users{" + "uid=" + uid
                + ", fname=" + fname + ""
                + ", lname=" + lname + "\n"
                + ", phone=" + phone + "\n"
                + ", email=" + email + "\n"
                + "}\n";
    }
}
