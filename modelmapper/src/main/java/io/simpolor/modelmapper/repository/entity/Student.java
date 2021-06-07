package io.simpolor.modelmapper.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Student {

    private Long seq;

    private String name;

    private List<String> hobbies;
}
