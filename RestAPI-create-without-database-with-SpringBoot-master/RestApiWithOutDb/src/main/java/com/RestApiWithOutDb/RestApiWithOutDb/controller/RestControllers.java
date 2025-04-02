package com.RestApiWithOutDb.RestApiWithOutDb.controller;


import com.RestApiWithOutDb.RestApiWithOutDb.model.Student;
import com.RestApiWithOutDb.RestApiWithOutDb.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/student")
public class RestControllers {
    @Autowired
    private Services services;



    @PostMapping("/create")
    public String createStudent(@RequestBody Student student){
       String s =  services.createServices(student);
        return s;
    }


    @GetMapping("/getId")
    public Student getStudent(@RequestParam int id){
        return services.getService(id);
    }


    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam int id){
            services.deleteServices(id);
            return "delete Successfully";
    }


    @PostMapping("/update")
    public Student update(@RequestBody Student student,int id){

        return services.updateServices( id, student );




    }


    @GetMapping("/getAll")
    public List<Student> getAll(){
        return services.getAllStudent();
    }




}
