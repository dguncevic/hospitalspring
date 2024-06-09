package com.hospital.spring;

import com.hospital.tools.Statics;
import com.hospital.beans.City;
import com.hospital.beans.Odijel;
import com.hospital.beans.Zupanije;
import static com.hospital.spring.UserOperations.props;
import static com.hospital.spring.UserOperations.url;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CityOperations {

    static ObservableList<City> getAllCities() {
        ObservableList<City> cities = FXCollections.observableArrayList();

        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();  ResultSet rs = st.executeQuery("select * from gradovi order by nazivgrada");) {

            while (rs.next()) {
                City temp = new City();
                temp.setId(rs.getInt("id"));
                temp.setCityName(rs.getString("nazivgrada"));
                temp.setZip(rs.getInt("postbroj"));

                cities.add(temp);

            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            Main.getLogger().severe("Netko nije dohvatio gradove...");
        }
        Main.getLogger().info("Netko je izlistao gradove...");
        return cities;

    }

    static ObservableList<Zupanije> getAllStates() {
        ObservableList<Zupanije> zupanije = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from zupanije order by nazivzupanije");
            while (rs.next()) {
                Zupanije temp = new Zupanije();
                temp.setId(rs.getInt("id"));
                temp.setNazivzupanije(rs.getString("nazivzupanije"));
                temp.setPozivni(rs.getInt("pozivni"));

                zupanije.add(temp);

            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            Main.getLogger().severe("Netko nije dohvatio zupanije...");
        }
        Main.getLogger().info("Netko je izlistao zupanije...");
        return zupanije;

    }

    static ObservableList<City> getCitiesByStateid(int id) {

        ObservableList<City> cities = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection(url, props);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from gradovi where zupanija = " + id+" order by nazivgrada asc");
            while (rs.next()) {
                City temp = new City();
                temp.setId(rs.getInt("id"));
                temp.setCityName(rs.getString("nazivgrada"));
                temp.setZip(rs.getInt("postbroj"));

                cities.add(temp);

            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            Main.getLogger().severe("Netko nije dohvatio gradove...");
        }
        Main.getLogger().info("Netko je izlistao gradove...");
        return cities;
    }
}
