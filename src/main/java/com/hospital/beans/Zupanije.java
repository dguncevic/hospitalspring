/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;

import lombok.Data;

/**
 *
 * @author 19par
 */
@Data
public class Zupanije {
    
    private int id;
    private String nazivzupanije;
    private int pozivni;
    
    @Override
    public String toString(){
        return nazivzupanije;
    }
    
}
