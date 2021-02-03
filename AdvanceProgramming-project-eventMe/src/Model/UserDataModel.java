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

public class UserDataModel {

    private databaseConn conn;
    private ResultSet rs;
    private ArrayList<Users> userList = new ArrayList<>();
    private ArrayList<Admins> adminList = new ArrayList<>();
//    private ArrayList<Users> eventList = new ArrayList<>();

    public UserDataModel() {
        conn = new databaseConn();
        setUserArrayListByDatabase(conn);
        setAdminArrayListByDatabase(conn);
    }

    public void setAdminArrayListByDatabase(databaseConn c) {
        Admins a = new Admins();
        try {
            rs = c.getStm().executeQuery("select * from admins");
            while (rs.next()) {
                a.setAid(rs.getInt("aid"));
                a.setaName(rs.getString("aname"));
                a.setPassword(rs.getString("password"));
                adminList.add(a);
            }
        } catch (Exception e) {
        }
    }

    public void setUserArrayListByDatabase(databaseConn c) {
        try {
            rs = c.getStm().executeQuery("select * from users");
            while (rs.next()) {
                Users u = new Users();
                u.setUid(rs.getInt("uid"));
                u.setFname(rs.getString("fname"));
                u.setLname(rs.getString("lname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                userList.add(u);
            }
            for (Users users : userList) {
                System.out.println(users);
            }
        } catch (Exception e) {
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

    public ArrayList<Users> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<Users> userList) {
        this.userList = userList;
    }

}
