/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.beans;

import java.sql.Date;
import lombok.Data;


@Data
class Recept {

    private int id;
    private int lijekid;
    private int korisnikid;
    private Date datumOd;
    private Date datumDo;

}
