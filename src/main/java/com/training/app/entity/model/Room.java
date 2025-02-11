package com.training.app.entity.model;

import java.util.ArrayList;
import java.util.List;

public class Room {



    public class Classroom {
        private Integer id;
        private String name;
        private List<Student> students;

        public Classroom() {
            this.students = new ArrayList<>();
        }

        public Classroom(Integer id, String name) {
            this.id = id;
            this.name = name;
            this.students = new ArrayList<>();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void addStudent(Student student) {
            this.students.add(student);
        }

        public void removeStudent(Integer studentId) {
            this.students.removeIf(student -> student.getId().equals(studentId));
        }

        public void listStudents() {
            for (Student student : students) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getName() +
                        ", Gender: " + student.getGender() + ", Age: " + student.getAge());
            }
        }
    }


}
