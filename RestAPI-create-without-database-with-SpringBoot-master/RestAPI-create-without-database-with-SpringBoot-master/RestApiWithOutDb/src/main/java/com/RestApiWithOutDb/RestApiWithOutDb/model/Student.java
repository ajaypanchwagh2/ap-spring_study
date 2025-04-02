package com.RestApiWithOutDb.RestApiWithOutDb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private int roll;

    private String adress;

//    public Student(int id, String name, int roll, String adress) {
//        this.id = id;
//        this.name = name;
//        this.roll = roll;
//        this.adress = adress;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getRoll() {
//        return roll;
//    }
//
//    public void setRoll(int roll) {
//        this.roll = roll;
//    }
//
//    public String getAdress() {
//        return adress;
//    }
//
//    public void setAdress(String adress) {
//        this.adress = adress;
//    }



}
