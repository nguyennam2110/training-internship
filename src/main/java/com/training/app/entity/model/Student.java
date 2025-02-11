package com.training.app.entity.model;

import com.training.app.entity.enums.Gender;

public class Student {

  private Integer id;
  private String name;
  private Gender gender;
  private int age;
  private Room room;


  public Student() {
  }

  public Student(Integer id, String name, Gender gender, int age, Room room) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.age = age;
    this.room = room;
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

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }
}
