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
public class Admins {

    private int aid;
    private String aName;
    private String password;
    private int evid;

    public Admins() {
    }

    public Admins(int aid, String aName, String password, int evid) {
        this.aid = aid;
        this.aName = aName;
        this.password = password;
        this.evid = evid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEvid() {
        return evid;
    }

    public void setEvid(int evid) {
        this.evid = evid;
    }

    @Override
    public String toString() {
        return "Admins{" + "aid=" + aid + ",\n"
                + " aName=" + aName + ",\n"
                + " password=" + password + ",\n"
                + " evid=" + evid + "}\n\n";
    }

}
