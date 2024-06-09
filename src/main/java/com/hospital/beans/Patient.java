/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;

import javafx.collections.ObservableList;
import lombok.Data;

@Data
public class Patient extends User{
 
    ObservableList<Bolest> bolesti;
    ObservableList<Recept> recepti;
}
