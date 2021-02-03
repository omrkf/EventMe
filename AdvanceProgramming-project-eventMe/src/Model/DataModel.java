package Model;

import java.io.File;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

    private databaseConn conn ;
    private ResultSet rs;
    private ArrayList<Users> userAttendList = new ArrayList<>();
    private ArrayList<Users> searchAttendList = new ArrayList<>();
    private ArrayList<Users> userUnAttendList = new ArrayList<>();
    private ArrayList<Users> searchUnAttendList = new ArrayList<>();
    private ArrayList<Admins> AdminList = new ArrayList<>();
    Admins ad = new Admins();
    Event ev = new Event();

    public DataModel() {

    }
    public void initModel() throws SQLException{
        conn = new databaseConn();
        setAdminArrayListByDatabase(conn);
        setUserArrayListByDatabase(conn);
        setUnattendUserArrayListByDatabase(conn);
    }
    public boolean login(String name , String Password){
        boolean found = false;
        for (Admins adm : AdminList) {
            if(adm.getaName().equalsIgnoreCase(name)
             && adm.getPassword().equals(Password)){
                setEventByDatabase(adm.getEvid());
                ad = adm;
                found =true;
            }
        }
        return found;
    }
    public void setModel(DataModel d) {
        conn = d.getConn();
        ad = d.getAd();
        ev = d.getEv();
        userAttendList.addAll(d.getUserAttendList());
        userUnAttendList.addAll(d.getUserUnAttendList());
        
    }

    public ArrayList<Users> getSearchAttendList() {
        return searchAttendList;
    }

    public void setSearchAttendList(ArrayList<Users> searchAttendList) {
        this.searchAttendList = searchAttendList;
    }

    public ArrayList<Users> getSearchUnAttendList() {
        return searchUnAttendList;
    }

    public void setSearchUnAttendList(ArrayList<Users> searchUnAttendList) {
        this.searchUnAttendList = searchUnAttendList;
    }
    
    public ArrayList<Users> getUserAttendList() {
        return userAttendList;
    }

    public void setUserAttendList(ArrayList<Users> userAttendList) {
        this.userAttendList = userAttendList;
    }

    public ArrayList<Users> getUserUnAttendList() {
        return userUnAttendList;
    }

    public void setUserUnAttendList(ArrayList<Users> userUnAttendList) {
        this.userUnAttendList = userUnAttendList;
    }
    
    public Admins getAd() {
        return ad;
    }

    public void setAd(Admins ad) {
        this.ad = ad;
    }

    public Event getEv() {
        return ev;
    }

    public void setEv(Event ev) {
        this.ev = ev;
    }

    public void setAdminArrayListByDatabase(databaseConn c) {
        try {
            rs = c.getStm().executeQuery("select * from admins");
            while (rs.next()) {
                Admins a = new Admins();
                a.setAid(rs.getInt("aid"));
                a.setaName(rs.getString("aname"));
                a.setPassword(rs.getString("password"));
                a.setEvid(rs.getInt("evid"));
                AdminList.add(a);
            }
        } catch (Exception e) {
        }
    }

    public void setEventByDatabase(int evid) {
        try {
            rs = conn.getStm().executeQuery("select * from events where evid =" +evid);
            while (rs.next()) {
                ev.setEvid(rs.getInt("evid"));
                ev.setEvName(rs.getString("evname"));
                ev.setEvDateStart(String.valueOf(rs.getDate("evdate_start")));
                ev.setEvDateEnd(String.valueOf(rs.getDate("evdate_end")));
            }
        } catch (SQLException ex) {System.out.println("Not Found EVID of Admin");
        ex.printStackTrace();
        }
    }

    public void setUserArrayListByDatabase(databaseConn c) {
        try {
            rs = c.getStm().executeQuery("SELECT * from users where EXISTS"
                    + " ( select * from attendees where users.uid = attendees.uid)");
            while (rs.next()) {
                Users u = new Users();
                u.setUid(rs.getInt("uid"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                userAttendList.add(u);
            }
        } catch (Exception e) {System.out.println("Error Attend database");
        e.printStackTrace();
        }
    }

    public void setUnattendUserArrayListByDatabase(databaseConn c) throws SQLException {
//        try {
            rs = c.getStm().executeQuery("SELECT * from users where not EXISTS ( select * from attendees where users.uid = attendees.uid) ");
            while (rs.next()) {
                Users u = new Users();
                u.setUid(rs.getInt("uid"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                userUnAttendList.add(u);
            }
//        } catch (Exception e) { System.out.println("Error unattend");
//            System.out.println(e);
//        }
    }

    public void setAttendTrue(Users u) {
        if(ev.getEvid() != 0){
        try {
            conn.getStm().executeUpdate("insert into attendees (uid,evid)"
                    + "values (" + u.getUid() + "," + ev.getEvid() + ")");
        } catch (Exception e) {System.out.println("set attend true");
            e.printStackTrace();
        }
        }
    }

    public void setAttendFalse(Users u) {
        if(ev.getEvid() != 0){
        try {
            System.out.println("uid = "+u.getUid());
            System.out.println("evid = "+ev.getEvid());
            conn.getStm().executeUpdate("DELETE FROM attendees"
                    + " WHERE attendees.uid = "+u.getUid()+" "
                    + " AND attendees.evid = "+ev.getEvid()+";");
        } catch (Exception e) {System.out.println("set attend false ");
        e.printStackTrace();
        }
        }
    }

    public databaseConn getConn() {
        return conn;
    }

    public void setConn(databaseConn conn) {
        this.conn = conn;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    

}
