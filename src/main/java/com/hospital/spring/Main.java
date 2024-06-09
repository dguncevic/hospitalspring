package com.hospital.spring;

import com.hospital.beans.City;
import com.hospital.beans.Gender;
import com.hospital.beans.Odijel;
import com.hospital.beans.Role;
import com.hospital.beans.Status;
import com.hospital.beans.Termini;
import com.hospital.beans.User;
import com.hospital.beans.Zupanije;
import com.hospital.tools.MyFormatter;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import javafx.collections.ObservableList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
@RestController

public class Main {

    private static Logger logger;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        try {
            setLogging();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            Main.getLogger().severe("ERROR occured...");
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        Main.logger = logger;
    }

    private static void setLogging() throws IOException {
        MyFormatter formatter = new MyFormatter();
        FileHandler mh = new FileHandler("my.log", 1024 * 1024 * 100, 30, true);
        mh.setFormatter(formatter);

        Main.setLogger(Logger.getLogger(Main.class.getName()));
        Main.getLogger().addHandler(mh);
        Main.getLogger().info("Server started");

    }

    @GetMapping(path = "api/viewusers")
    public ObservableList<User> viewUsers() {
        return UserOperations.getAllUsers();

    }

    @GetMapping(path = "api/addphonenumber")
    public String addPhoneNumber(int phoneNumber, String username) {
        return UserOperations.addCityToUser(phoneNumber, username) ? "OK" : "NOK";
    }

    @GetMapping(path = "api/getuserbyusername")
    public User getUserByUsername(String username) {
        return UserOperations.getUserByUsername(username);
    }

    @GetMapping(path = "api/getallodijeli")
    public ObservableList<Odijel> getAllOdijeli() {
        return HospitalOperations.getAllOdijeli();
    }

    @GetMapping(path = "api/getallcities")
    public ObservableList<City> getAllCities() {
        return CityOperations.getAllCities();
    }

    @GetMapping(path = "api/getcitiesbystateid")
    public ObservableList<City> getCitiesByStateId(int id) {
        return CityOperations.getCitiesByStateid(id);
    }

    @GetMapping(path = "api/getallstates")
    public ObservableList<Zupanije> getAllStates() {
        return CityOperations.getAllStates();
    }

    @GetMapping(path = "api/getallroles")
    public ObservableList<Role> getAllRoles() {
        return HospitalOperations.getAllRoles();
    }

    @GetMapping(path = "api/getallstatusi")
    public ObservableList<Status> getAllStatusi() {
        return HospitalOperations.getAllStatusi();
    }

    @GetMapping(path = "api/addnewuser")
    public String addNewUser(String firstName, String lastName, String username, String password, int city, String adresa, int odijel, String email, Gender spol, String oib, String phoneNumber, String dateOfBirth, int role) {

        Gender g = Gender.MALE;

        if (spol.equals("FEMALE")) {
            g = Gender.FEMALE;
        }

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);

        City tcity = new City();
        tcity.setId(city);
        user.setCity(tcity);

        user.setAdress(adresa);

        Odijel todijel = new Odijel();
        todijel.setId(odijel);
        user.setOdijel(todijel);
        user.setEmail(email);
        user.setSpol(g);
        user.setOib(oib);

        Role trole = new Role();
        trole.setId(role);
        user.setRole(trole);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            java.util.Date date = dateFormat.parse(dateOfBirth);
            sqlDate = new java.sql.Date(date.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setDateOfBirth(sqlDate);
        user.setPhoneNumber(phoneNumber);

        return UserOperations.addNewUser(user) ? "OK" : "NOK";
    }

    @GetMapping(path = "api/getallpatients")
    public ObservableList<User> getAllPatients() {
        return UserOperations.getAllPatients();
    }

    @GetMapping(path = "api/getalltermini")
    public ObservableList<Termini> getAllTermini(String query) {
        return HospitalOperations.getAllTermini(query);
    }

    @PostMapping(path = "api/getallusers")
    public ObservableList<User> getAllUsers(String query) {
        System.out.println(query);
        return UserOperations.getAllUsers(query);

    }

    @PostMapping(path = "api/getuserbyquery")
    public User getUserbyQuery(String query) {
        return UserOperations.getUserByQuery(query);
    }

    @PostMapping(path = "api/addnewtermin")
    public String addNewTermin(int patientid, int doctorid, String vrijemedolaska, String opis) {
        return HospitalOperations.addNewTermin(patientid, doctorid, vrijemedolaska, opis) ? "OK" : "NOK";
    }

    @GetMapping(path = "api/getmymetadata")
    public ResultSetMetaData getMyMetaData() {
        return UserOperations.getMyMetaData();
    }

    @GetMapping(path = "api/getcolcount")
    public int getMyColCount() {
        return UserOperations.getMyColCount();
    }

    @GetMapping(path = "api/getcolcount2")
    public int getMyColCount(String query) {
        return UserOperations.getMyColCount(query);
    }

    @GetMapping(path = "api/getcolnames")
    public ObservableList<String> getMyColNames() {
        return UserOperations.getMyColNames();
    }

    @GetMapping(path = "api/getcolnames2")
    public ObservableList<String> getMyColNames(String query) {
        return UserOperations.getMyColNames(query);
    }

    @GetMapping(path = "api/getallstrings")
    public ObservableList<String> getAllStrings() {
        return UserOperations.getAllStrings();
    }

    @GetMapping(path = "api/getallstrings2")
    public ObservableList<String> getAllStrings(String query) {
        return UserOperations.getAllStrings(query);
    }

}
