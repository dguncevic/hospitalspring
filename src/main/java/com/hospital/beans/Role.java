/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;

import lombok.Data;

@Data
public class Role {

    private int id;
    private String roleName;
    private int level;

    @Override
    public String toString() {
        return ""+id;
    }

}
