package io.simpolor.modelmapper.controller;

import io.simpolor.modelmapper.model.ResultDto;
import io.simpolor.modelmapper.model.StudentDto;
import io.simpolor.modelmapper.repository.entity.Student;
import io.simpolor.modelmapper.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	private final ModelMapper modelMapper;

	@PostConstruct
	public void init(){
		modelMapper.createTypeMap(Student.class, StudentDto.class)
				.addMapping(student -> student.getStudentId(), StudentDto::setId);

		modelMapper.createTypeMap(StudentDto.class, Student.class)
				.addMapping(dto -> dto.getId(), Student::setStudentId);
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	public List<StudentDto> list() {

		List<Student> students = studentService.getAll();
		if(CollectionUtils.isEmpty(students)){
			return Collections.EMPTY_LIST;
		}

		List<StudentDto> studentDtos = new ArrayList<>();
		for(Student student : students){
			StudentDto studentDto = modelMapper.map(student, StudentDto.class);
			studentDtos.add(studentDto);
		}

		return studentDtos;
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.GET)
	public StudentDto detail(@PathVariable Long studentId) {

		Student student = studentService.get(studentId);

		StudentDto studentDto = modelMapper.map(student, StudentDto.class);
		return studentDto;
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResultDto register(@RequestBody StudentDto request) {

		Student entity = modelMapper.map(request, Student.class);
		Student student = studentService.create(entity);

		return ResultDto.builder()
				.id(student.getStudentId())
				.build();
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.PUT)
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto request) {

		request.setId(studentId);

		Student entity = modelMapper.map(request, Student.class);
		studentService.update(entity);
	}

	@RequestMapping(value="/{studentId}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}
}
