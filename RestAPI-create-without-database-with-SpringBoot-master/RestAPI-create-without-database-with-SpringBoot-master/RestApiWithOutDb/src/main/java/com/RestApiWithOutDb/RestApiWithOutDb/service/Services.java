

import com.RestApiWithOutDb.RestApiWithOutDb.service.StudentNotFoundException;

public Student getService(int id){
    for(Student s : list){
        if(s.getId() == id){
            return s;
        }
    }
    throw new StudentNotFoundException("Student with ID " + id + " not found");
}

public String deleteServices(int id) {
    for(Student s : list){
        if(s.getId()==id){
            list.remove(s);
            return "Successfully deleted student";
        }
    }
    throw new StudentNotFoundException("Student with ID " + id + " not found");
}

public Student updateServices(int id, Student student){
    for(Student s : list){
        if(s.getId()==id){
            s.setName(student.getName());
            s.setRoll(student.getRoll());
            s.setAdress(student.getAdress());
            return s;
        }
    }
    throw new StudentNotFoundException("Student with ID " + id + " not found");
}
