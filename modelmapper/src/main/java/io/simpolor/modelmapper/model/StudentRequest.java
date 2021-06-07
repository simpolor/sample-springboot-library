package io.simpolor.modelmapper.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentRequest {

    private Long seq;

    private String name;

    private List<String> hobbies;
}
