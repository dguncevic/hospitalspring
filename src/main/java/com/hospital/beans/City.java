/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;

import lombok.Data;

@Data
public class City {

    private int id;

    private String cityName;
    private String cityState;
    private int zip;
    private Zupanije zupanija;

    @Override
    public String toString() {
        return "Grad: " + cityName;
    }

}
