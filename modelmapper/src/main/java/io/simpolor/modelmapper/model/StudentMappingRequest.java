package io.simpolor.modelmapper.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentMappingRequest {

    private Long studentSeq;

    private String studentName;

    private List<String> studentHobbies;
}
