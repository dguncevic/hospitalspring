/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.spring;

import com.hospital.beans.Gender;
import com.hospital.beans.Odijel;
import com.hospital.beans.Role;
import com.hospital.beans.Status;
import com.hospital.beans.Termini;
import com.hospital.beans.User;
import static com.hospital.spring.UserOperations.props;
import static com.hospital.spring.UserOperations.url;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 19par
 */
public class HospitalOperations {
    
    static ObservableList<Odijel> getAllOdijeli() {
        
        ObservableList<Odijel> odijeli = FXCollections.observableArrayList();
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();  ResultSet rs = st.executeQuery("select * from odijeli");) {
            
            while (rs.next()) {
                Odijel temp = new Odijel();
                temp.setId(rs.getInt("id"));
                temp.setImeOdijela(rs.getString("imeodijela"));
                odijeli.add(temp);
                
            }
            
        } catch (Exception e) {
            
        }
        return odijeli;
    }
    
    static ObservableList<Role> getAllRoles() {
        
        ObservableList<Role> roles = FXCollections.observableArrayList();
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();  ResultSet rs = st.executeQuery("select * from roles");) {
            
            while (rs.next()) {
                Role temp = new Role();
                temp.setId(rs.getInt("id"));
                temp.setRoleName(rs.getString("rolename"));
                temp.setLevel(rs.getInt("level"));
                roles.add(temp);
                
            }
            
        } catch (Exception e) {
            
        }
        return roles;
        
    }
    
    static ObservableList<Status> getAllStatusi() {
        
        ObservableList<Status> statusi = FXCollections.observableArrayList();
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();  ResultSet rs = st.executeQuery("select * from statusi");) {
            
            while (rs.next()) {
                Status temp = new Status();
                temp.setId(rs.getInt("id"));
                temp.setStatus(rs.getString("status"));
                statusi.add(temp);
            }
            
        } catch (Exception e) {
            
        }
        return statusi;
    }
    
    static boolean addNewTermin(int patientid, int doctorid, String vrijemedolaska, String opis) {
        boolean result = false;
        
        try {
            try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement()) {
                String s = "insert into termin (patientid, doctorid, vrijemedolaska, opis) values (" + patientid + ", " + doctorid + ", '" + vrijemedolaska + "', '" + opis + "');";
                System.out.println(s);
                
                result = st.execute(s);
                
            }
        } catch (Exception e) {
            Main.getLogger().severe("Termin nije dodan..");
        }
        
        Main.getLogger().info("Termin je dodan ...");
        
        return !result;
    }
    
    static ObservableList<Termini> getAllTermini(String query) {
        
        ObservableList<Termini> termini = FXCollections.observableArrayList();
        try ( Connection con = DriverManager.getConnection(url, props);  Statement st = con.createStatement();  ResultSet rs = st.executeQuery(query);) {
            
            while (rs.next()) {
                Termini temp = new Termini();
                temp.setId(rs.getInt("id"));
                temp.setPatientid(rs.getInt("patientid"));
                temp.setPatient_firstname(rs.getString("patient_firstname"));
                temp.setPatient_lastname(rs.getString("patient_lastname"));
                temp.setDoctorid(rs.getInt("doctorid"));
                temp.setDoctor_firstname(rs.getString("doctor_firstname"));
                temp.setDoctor_lastname(rs.getString("doctor_lastname"));
                temp.setOpis(rs.getString("opis"));
                temp.setVrijemedolaska(rs.getTimestamp("vrijemedolaska"));
                temp.setVrijemekreiranjatermina(rs.getTimestamp("vrijemekreiranjatermina"));
                termini.add(temp);
            }
            
        } catch (Exception e) {
            Main.getLogger().severe("Termini nisu dohvaceni..");
        }
        return termini;
        
    }
    
}
