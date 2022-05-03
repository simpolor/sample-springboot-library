package io.simpolor.modelmapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private Long id;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;
}
