package com.hospital.spring;

import com.hospital.beans.*;
import com.hospital.tools.Statics;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import javax.swing.text.TableView;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author 19par
 */
public class UserOperations {

    static String url = Statics.getUrl();
    static Properties props = new Properties();
    public static String str = "";
    private static ObservableList<ObservableList> data;

    static {

        props.setProperty("user", Statics.getUsername());
        props.setProperty("password", Statics.getPassword());
        props.setProperty("ssl", Statics.getUsessl());
    }

    static ObservableList<User> getAllUsers() {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            try ( Connection con = DriverManager.getConnection(url, props)) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from users");
                while (rs.next()) {

                    User temp = new User();
                    temp.setId(rs.getInt("id"));
                    temp.setFirstName(rs.getString("firstName"));
                    temp.setLastName(rs.getString("lastName"));
                    temp.setUsername(rs.getString("username"));
                    temp.setPassword(rs.getString("password"));
                    temp.setAdress(rs.getString("adresa"));
                    temp.setDateOfBirth(rs.getDate("dateOfBirth"));
                    temp.setSpol(rs.getString("spol").equals("MALE") ? Gender.MALE : Gender.FEMALE);
                    temp.setDateEntered(rs.getDate("dateEntered"));
                    temp.setDateModified(rs.getDate("dateModified"));
                    temp.setOib(rs.getString("oib"));
//                Role trole = new Role();
//                trole.setId(rs.getInt("role"));
//                temp.setRole(trole);

                    System.out.println(temp);
                    users.add(temp);
                }
                rs.close();
                st.close();
            }
        } catch (Exception e) {

        }

        return users;
    }

    static boolean addCityToUser(int phoneNumber, String username) {
        boolean result = false;
        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            String s = "update users set phoneNumber = " + phoneNumber + " where username = '" + username + "';";
            System.out.println(s);

            result = st.execute(s);

            st.close();
            con.close();
        } catch (Exception e) {

        }

        return !result;
    }

    static User getUserByUsername(String username) {

        String querry = "select * from alluserdata where username = '" + username + "';";

        User temp = new User();
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(querry);

            while (rs.next()) {

                temp.setId(rs.getInt("id"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setAdress(rs.getString("adresa"));
                temp.setDateOfBirth(rs.getDate("dateOfBirth"));
                temp.setSpol(rs.getString("spol").equals("MALE") ? Gender.MALE : Gender.FEMALE);
                temp.setDateEntered(rs.getDate("dateEntered"));
                temp.setDateModified(rs.getDate("dateModified"));
                temp.setOib(rs.getString("oib"));
                temp.setEmail(rs.getString("email"));
                temp.setPhoneNumber(rs.getString("phonenumber"));

                Role trole = new Role();
                trole.setId(rs.getInt("roleid"));
                trole.setRoleName(rs.getString("rolename"));
                trole.setLevel(rs.getInt("level"));
                temp.setRole(trole);

                Odijel tOdijel = new Odijel();
                tOdijel.setId(rs.getInt("odijeliid"));
                tOdijel.setImeOdijela(rs.getString("imeodijela"));
                temp.setOdijel(tOdijel);

                Status tStatus = new Status();
                tStatus.setId(rs.getInt("statusiid"));
                tStatus.setStatus(rs.getString("status"));
                temp.setStatus(tStatus);

                City tCity = new City();
                tCity.setId(rs.getInt("cityid"));
                tCity.setCityName(rs.getString("cityname"));
                temp.setCity(tCity);

            }

            System.out.println(temp);

            rs.close();

        } catch (Exception e) {

        }
        Main.getLogger().info(temp + " je dohvatio usera preko usernamea...");

        return temp;
    }

    static boolean addNewUser(User user) {

        boolean result = false;

        try {
            try ( Connection con = DriverManager.getConnection(url, props)) {
                Statement st = con.createStatement();
                String s = "insert into users (firstname, lastname, username, password, city, adresa, odijel, email, spol, oib, phonenumber, dateofbirth, status, role) values ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getUsername() + "', '" + user.getPassword() + "', " + user.getCity().getId() + ", '" + user.getAdress() + "', " + user.getOdijel().getId() + ", '" + user.getEmail() + "', '" + user.getSpol() + "', " + user.getOib() + ", " + user.getPhoneNumber() + ", '" + user.getDateOfBirth() + "', 1, " + user.getRole().getId() + ");";
                System.out.println(s);

                result = st.execute(s);

                st.close();
            }
        } catch (Exception e) {

        }

        Main.getLogger().info(user + " je dodan ...");

        return !result;
    }

    static ObservableList<User> getAllUsers(@RequestBody String query) {
//        query = query.replaceAll("%20", " ");
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                User temp = new User();
                temp.setId(rs.getInt("id"));
                temp.setFirstName(rs.getString("ime"));
                temp.setLastName(rs.getString("prezime"));
                temp.setSpol(rs.getString("spol").equals("MALE") ? Gender.MALE : Gender.FEMALE);
                temp.setDateOfBirth(rs.getDate("dateOfBirth"));
                temp.setAdress(rs.getString("adresa"));
                temp.setOib(rs.getString("oib"));
                temp.setEmail(rs.getString("email"));
                temp.setPhoneNumber(rs.getString("phonenumber"));

                temp.setDateEntered(rs.getDate("dateEntered"));
                temp.setDateModified(rs.getDate("dateModified"));

                Role trole = new Role();
                trole.setId(rs.getInt("roleid"));
                trole.setRoleName(rs.getString("rolename"));
                temp.setRole(trole);

                Status tstatus = new Status();
                tstatus.setStatus(rs.getString("status"));
                temp.setStatus(tstatus);

                Odijel todijel = new Odijel();
                todijel.setId(rs.getInt("odijeliid"));
                todijel.setImeOdijela(rs.getString("imeodijela"));
                temp.setOdijel(todijel);

                City tcity = new City();
                tcity.setCityName(rs.getString("cityname"));
               
                temp.setCity(tcity);
                

                users.add(temp);
            }
            rs.close();
            st.close();
            con.close();
            
         
        } catch (Exception e) {
            Main.getLogger().severe("Someone tried to get access to all patients and failed");
        }

        return users;
    }

    static ObservableList<User> getAllPatients() {
        ObservableList<User> patients = FXCollections.observableArrayList();

        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery("select * from allpatientdata where rolename = 'Pacijent';");
            while (rs.next()) {

                User temp = new User();
                temp.setId(rs.getInt("id"));
                temp.setFirstName(rs.getString("ime"));
                temp.setLastName(rs.getString("prezime"));
                temp.setSpol(rs.getString("spol").equals("MALE") ? Gender.MALE : Gender.FEMALE);
                temp.setDateOfBirth(rs.getDate("dateOfBirth"));
                temp.setAdress(rs.getString("adresa"));
                temp.setOib(rs.getString("oib"));
                temp.setEmail(rs.getString("email"));
                temp.setPhoneNumber(rs.getString("phonenumber"));

                temp.setDateEntered(rs.getDate("dateEntered"));
                temp.setDateModified(rs.getDate("dateModified"));

                Role trole = new Role();
                trole.setId(rs.getInt("roleid"));
                trole.setRoleName(rs.getString("rolename"));
                temp.setRole(trole);

                Status tstatus = new Status();
                tstatus.setStatus(rs.getString("status"));
                temp.setStatus(tstatus);

                Odijel todijel = new Odijel();
                todijel.setId(rs.getInt("odijeliid"));
                todijel.setImeOdijela(rs.getString("imeodijela"));
                temp.setOdijel(todijel);

                City tcity = new City();
                tcity.setCityName(rs.getString("cityname"));
                temp.setCity(tcity);

                patients.add(temp);
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            Main.getLogger().severe("Someone tried to get access to all patients and failed");
        }

        return patients;
    }

    static ResultSetMetaData getMyMetaData() {

        ResultSetMetaData rsmd;

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from allpatientdata where rolename = 'Pacijent'");

            rsmd = rs.getMetaData();
            return rsmd;

        } catch (SQLException ex) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    static int getMyColCount() {

        ResultSetMetaData rsmd;
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery("select * from allpatientdata where rolename = 'Pacijent'");

            rsmd = rs.getMetaData();
            rs.close();
            return rsmd.getColumnCount();

        } catch (SQLException ex) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    static int getMyColCount(String query) {

        ResultSetMetaData rsmd;
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(query);

            rsmd = rs.getMetaData();
            rs.close();
            return rsmd.getColumnCount();

        } catch (SQLException ex) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    static ObservableList<String> getMyColNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        ResultSetMetaData rsmd;
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery("select * from allpatientdata where rolename = 'Pacijent'");

            rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            for (int i = 0; i < colCount; i++) {
                names.add(rsmd.getColumnName(i + 1));
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return names;
    }

    static ObservableList<String> getMyColNames(String query) {
        ObservableList<String> names = FXCollections.observableArrayList();
        ResultSetMetaData rsmd;
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(query);

            rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            for (int i = 0; i < colCount; i++) {
                names.add(rsmd.getColumnName(i + 1));
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return names;
    }

    static ObservableList<String> getAllStrings() {

        ObservableList<String> strings = FXCollections.observableArrayList();
        ResultSetMetaData rsmd;
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery("select * from allpatientdata where rolename = 'Pacijent'");

            rsmd = rs.getMetaData();

            while (rs.next()) {

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    //Iterate Column
                    strings.add(rs.getString(i));
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return strings;
    }

    static ObservableList<String> getAllStrings(String query) {

        ObservableList<String> strings = FXCollections.observableArrayList();
        ResultSetMetaData rsmd;
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(query);

            rsmd = rs.getMetaData();

            while (rs.next()) {

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    //Iterate Column
                    strings.add(rs.getString(i));
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return strings;
    }

    static User getUserByQuery(String query) {

        User temp = new User();
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                temp.setId(rs.getInt("id"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setAdress(rs.getString("adresa"));
                temp.setDateOfBirth(rs.getDate("dateOfBirth"));
                temp.setSpol(rs.getString("spol").equals("MALE") ? Gender.MALE : Gender.FEMALE);
                temp.setDateEntered(rs.getDate("dateEntered"));
                temp.setDateModified(rs.getDate("dateModified"));
                temp.setOib(rs.getString("oib"));
                temp.setEmail(rs.getString("email"));
                temp.setPhoneNumber(rs.getString("phonenumber"));

                Role trole = new Role();
                trole.setId(rs.getInt("roleid"));
                trole.setRoleName(rs.getString("rolename"));
                trole.setLevel(rs.getInt("level"));
                temp.setRole(trole);

                Odijel tOdijel = new Odijel();
                tOdijel.setId(rs.getInt("odijeliid"));
                tOdijel.setImeOdijela(rs.getString("imeodijela"));
                temp.setOdijel(tOdijel);

                Status tStatus = new Status();
                tStatus.setId(rs.getInt("statusiid"));
                tStatus.setStatus(rs.getString("status"));
                temp.setStatus(tStatus);

                City tCity = new City();
                tCity.setId(rs.getInt("cityid"));
                tCity.setCityName(rs.getString("cityname"));
                temp.setCity(tCity);

            }

            System.out.println(temp);

            rs.close();

        } catch (Exception e) {

        }
        Main.getLogger().info(temp + " je dohvatio usera preko usernamea...");

        return temp;
    }

}
