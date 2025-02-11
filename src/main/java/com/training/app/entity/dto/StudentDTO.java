package com.training.app.entity.dto;

import com.training.app.entity.enums.Gender;

public class StudentDTO {

  private String name;
  private Gender gender;
  private int age;
  private Integer roomId;

  public StudentDTO(String name, Gender gender, int age, Integer roomId) {
    this.name = name;
    this.gender = gender;
    this.age = age;
    this.roomId = roomId;
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

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }
}
