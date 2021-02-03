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

    private databaseConn conn;
    private ResultSet rs;
    private int countAtt;
    private int countReg;
    private ArrayList<Users> userAttendList = new ArrayList<>();
    private ArrayList<Users> searchAttendList = new ArrayList<>();
    private ArrayList<Users> userUnAttendList = new ArrayList<>();
    private ArrayList<Users> searchUnAttendList = new ArrayList<>();
    private ArrayList<Admins> AdminList = new ArrayList<>();
    Admins ad = new Admins();
    Event ev = new Event();

    public DataModel() {

    }

    public void initModel() throws SQLException {
        conn = new databaseConn();
        setAdminArrayListByDatabase(conn);
//        setUserArrayListByDatabase(conn);
//        setUnattendUserArrayListByDatabase(conn);
    }

    public boolean login(String name, String Password) throws SQLException {
        boolean found = false;
        for (Admins adm : AdminList) {
            if (adm.getaName().equalsIgnoreCase(name)
                    && adm.getPassword().equals(Password)) {
                setEventByDatabase(adm.getEvid());
                setUnattendUserArrayListByDatabase(conn);
                setUserArrayListByDatabase(conn);
                ad = adm;
                found = true;
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
        countAtt = d.countAtt;
        countReg = d.countReg;
    }

    public ObservableList<Users> attendReport(ObservableList<Users> obs) {
        try {
            rs = conn.getStm().executeQuery("SELECT * from users where EXISTS"
                    + " ( select * from attendees where users.uid = attendees.uid and attendees.evid = "
                    + "" + ev.getEvid() + ""
                    //                    + "1"
                    + ")");
            while (rs.next()) {
                Users u = new Users();
                u.setUid(rs.getInt("uid"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
//                System.out.println(u);
                obs.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("attend report XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
        }
        System.out.println("obs size : " + obs.size());
        return obs;
    }
    public ObservableList<Users> certifReport(ObservableList<Users> obs) {
        try {
            rs = conn.getStm().executeQuery("SELECT * from users u ,attendees a where "
//                    + " ( select * from attendees where users.uid = attendees.uid and attendees.evid = "
                    + " u.uid = a.uid and evid = "
                    + "" + ev.getEvid() + ""
                    //                    + "1"
                    + "");
            while (rs.next()) {
                Users u = new Users();
                u.setUid(rs.getInt("uid"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                if(rs.getBoolean("send_certif"))
                u.setCertif("Sended");
                else
                u.setCertif("Unsended");
                obs.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("attend report XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
        }
        System.out.println("obs size : " + obs.size());
        return obs;
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
            rs = conn.getStm().executeQuery("select * from events where evid =" + evid);
            while (rs.next()) {
                ev.setEvid(rs.getInt("evid"));
                ev.setEvName(rs.getString("evname"));
                ev.setEvDateStart(String.valueOf(rs.getDate("evdate_start")));
                ev.setEvDateEnd(String.valueOf(rs.getDate("evdate_end")));
            }
        } catch (SQLException ex) {
            System.out.println("Not Found EVID of Admin");
            ex.printStackTrace();
        }
    }

    public void setUserArrayListByDatabase(databaseConn c) {
        try {
            rs = c.getStm().executeQuery("SELECT * from users where EXISTS "
                + "( select * from attendees "
                + " where users.uid = attendees.uid and attend=1 and attendees.evid = "
                + "" + ev.getEvid() + ""
                + ")");
            while (rs.next()) {
                Users u = new Users();
                u.setUid(rs.getInt("uid"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                userAttendList.add(u);
            }
//                System.out.println(userAttendList.size());
        } catch (Exception e) {
            System.out.println("Error Attend database");
            e.printStackTrace();
        }
    }

    public void setUnattendUserArrayListByDatabase(databaseConn c) throws SQLException {
//        try {
        rs = c.getStm().executeQuery("SELECT * from users where EXISTS ( select * from attendees where users.uid = attendees.uid and attend=0 and attendees.evid =1)  ");
//        rs = c.getStm().executeQuery("SELECT * from users where EXISTS "
//                + "( select * from attendees "
//                + " where users.uid = attendees.uid and attend=0 and attendees.evid = "
//                + "" + ev.getEvid() + ""
//                //                    + "1"
//                + ")");
        while (rs.next()) {
            Users u = new Users();
            u.setUid(rs.getInt("uid"));
            u.setFname(rs.getString("fname"));
            u.setLname(rs.getString("lname"));
            u.setEmail(rs.getString("email"));
            u.setPhone(rs.getString("phone"));
//            System.out.println(u);
            userUnAttendList.add(u);
        }
//        } catch (Exception e) { System.out.println("Error unattend");
//            System.out.println(e);
//        }
    }

    public void setAttendTrue(Users u) {
        if (ev.getEvid() != 0) {
            try {
                conn.getStm().executeUpdate("UPDATE attendees SET attend = 1 WHERE "
                        + " uid =" + u.getUid() + " and evid = " + ev.getEvid() );
            } catch (Exception e) {
                System.out.println("set attend true");
                e.printStackTrace();
            }
        }
    }

    public void setAttendFalse(Users u) {
        if (ev.getEvid() != 0) {
            try {
                System.out.println("uid = " + u.getUid());
                System.out.println("evid = " + ev.getEvid());
                conn.getStm().executeUpdate("UPDATE attendees SET attend = 0 WHERE "
                        + " uid = " + u.getUid() + " and evid =" + ev.getEvid() );
            } catch (Exception e) {
                System.out.println("set attend false ");
                e.printStackTrace();
            }
        }
    }

    public int countAttend() throws SQLException {
        rs = conn.getStm().executeQuery("select count(*)  from attendees where attend = 1 and evid = " + ev.getEvid());
        rs.next();
        countAtt = rs.getInt("count(*)");
        return countAtt;
    }

    public int countRegister() throws SQLException {
        rs = conn.getStm().executeQuery("select count(*)  from attendees where evid = " + ev.getEvid());
        rs.next();
        countReg = rs.getInt("count(*)");
        return countReg;
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

}
