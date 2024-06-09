/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;

public class Odijel {

    private int id;
    private String imeOdijela;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImeOdijela() {
        return imeOdijela;
    }

    public void setImeOdijela(String imeOdijela) {
        this.imeOdijela = imeOdijela;
    }

    @Override
    public String toString() {
        return "Odijel: " + imeOdijela;
    }

}
