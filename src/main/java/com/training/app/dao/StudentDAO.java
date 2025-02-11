package com.training.app.dao;

import com.training.app.entity.dto.StudentDTO;

public interface StudentDAO {

  int createStudent(StudentDTO studentRequest);

  void exportStudentsToExcel();
}
