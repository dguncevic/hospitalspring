/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;


import java.sql.Timestamp;

/**
 *
 * @author 19par
 */
public class Termini {
    
    private int id;
    private int patientid;
    private int doctorid;
    private String patient_firstname;
    private String patient_lastname;
    private String doctor_firstname;
    private String doctor_lastname;
    private String opis;
    private Timestamp vrijemedolaska;
    private Timestamp vrijemekreiranjatermina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public String getPatient_firstname() {
        return patient_firstname;
    }

    public void setPatient_firstname(String patient_firstname) {
        this.patient_firstname = patient_firstname;
    }

    public String getPatient_lastname() {
        return patient_lastname;
    }

    public void setPatient_lastname(String patient_lastname) {
        this.patient_lastname = patient_lastname;
    }

    public String getDoctor_firstname() {
        return doctor_firstname;
    }

    public void setDoctor_firstname(String doctor_firstname) {
        this.doctor_firstname = doctor_firstname;
    }

    public String getDoctor_lastname() {
        return doctor_lastname;
    }

    public void setDoctor_lastname(String doctor_lastname) {
        this.doctor_lastname = doctor_lastname;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Timestamp getVrijemedolaska() {
        return vrijemedolaska;
    }

    public void setVrijemedolaska(Timestamp vrijemedolaska) {
        this.vrijemedolaska = vrijemedolaska;
    }

    public Timestamp getVrijemekreiranjatermina() {
        return vrijemekreiranjatermina;
    }

    public void setVrijemekreiranjatermina(Timestamp vrijemekreiranjatermina) {
        this.vrijemekreiranjatermina = vrijemekreiranjatermina;
    }
    
    
    
}
