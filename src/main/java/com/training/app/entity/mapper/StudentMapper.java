package com.training.app.entity.mapper;

import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.model.Student;

public interface StudentMapper {

  Student toEntity(StudentDTO studentDTO);

}
