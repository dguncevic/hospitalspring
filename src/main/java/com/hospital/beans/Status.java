/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;

import lombok.Data;

@Data
public class Status {

    private int id;
    private String status;

    @Override
    public String toString() {
        return status;
    }

}
