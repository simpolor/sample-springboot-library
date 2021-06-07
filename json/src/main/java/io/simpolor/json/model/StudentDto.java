package io.simpolor.json.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class StudentDto {

    long seq;

    String name;

    List<String> hobbies;
}
