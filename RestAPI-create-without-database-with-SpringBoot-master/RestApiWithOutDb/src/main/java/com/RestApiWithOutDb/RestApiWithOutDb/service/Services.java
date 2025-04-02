package com.RestApiWithOutDb.RestApiWithOutDb.service;

import com.RestApiWithOutDb.RestApiWithOutDb.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Services {

    List<Student> list;
    public Services() {
        this.list = new ArrayList<>();
    }

    //create
    public String createServices(Student student){

        list.add(student);
        return "Successfully created";

    }


//get the student
    public Student getService(int id){


        for(Student s : list){
            if(s.getId() == id){
                return s;
            }
        }
    throw new IllegalArgumentException("Student not found");
    }


    //delete the student
    public String deleteServices(int id)
    {
        for(Student s : list){
            if(s.getId()==id){
                list.remove(s);
                return "Successfully deleted student";
            }
        }
        throw new IllegalArgumentException("Student not found");


    }

    //update

    public Student updateServices(int id, Student student){

        for(Student s : list){
            if(s.getId()==id){
                s.setName(student.getName());
                s.setRoll(student.getRoll());
                s.setAdress(student.getAdress());
                return s;
            }
        }
        throw new IllegalArgumentException("id not found");

    }
public List<Student> getAllStudent()
{
    return list;
}

}
