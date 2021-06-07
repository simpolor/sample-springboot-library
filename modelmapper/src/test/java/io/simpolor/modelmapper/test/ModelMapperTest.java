package io.simpolor.modelmapper.test;

import io.simpolor.modelmapper.model.StudentRequest;
import io.simpolor.modelmapper.model.StudentMappingRequest;
import io.simpolor.modelmapper.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

// @SpringBootTest
public class ModelMapperTest {

    // @Autowired
    // private ModelMapper modelMapper;

    @Test
    public void testMap(){

        // config
        ModelMapper modelMapper = new ModelMapper();

        // given
        StudentRequest request = new StudentRequest();
        request.setSeq(1L);
        request.setName("simpolor");
        request.setHobbies(Arrays.asList("축구"));

        // when
        Student actual = modelMapper.map(request, Student.class);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1L);
        Assertions.assertThat(actual.getName()).isEqualTo("simpolor");
        Assertions.assertThat(actual.getHobbies()).isEqualTo(Arrays.asList("축구"));
    }

    @Test
    public void testAddMapping(){

        // config
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(StudentMappingRequest.class, Student.class)
                .addMapping(StudentMappingRequest::getStudentSeq, Student::setSeq)
                .addMapping(StudentMappingRequest::getStudentName, Student::setName)
                .addMapping(StudentMappingRequest::getStudentHobbies, Student::setHobbies);

        // given
        StudentMappingRequest request = new StudentMappingRequest();
        request.setStudentSeq(1L);
        request.setStudentName("simpolor");
        request.setStudentHobbies(Arrays.asList("축구"));

        // when
        Student actual = modelMapper.map(request, Student.class);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1L);
        Assertions.assertThat(actual.getName()).isEqualTo("simpolor");
        Assertions.assertThat(actual.getHobbies()).isEqualTo(Arrays.asList("축구"));
    }

    @Test
    public void testAddMappings(){

        // config
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(StudentMappingRequest.class, Student.class)
                .addMappings(mapping -> {
                    mapping.map(source -> source.getStudentSeq(), Student::setSeq);
                    mapping.map(source -> source.getStudentName(), Student::setName);
                    mapping.map(source -> source.getStudentHobbies(), Student::setHobbies);
                    // mapping.skip(Student::setSeq);
                });

        // given
        StudentMappingRequest request = new StudentMappingRequest();
        request.setStudentSeq(1L);
        request.setStudentName("simpolor");
        request.setStudentHobbies(Arrays.asList("축구"));

        // when
        Student actual = modelMapper.map(request, Student.class);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1L);
        Assertions.assertThat(actual.getName()).isEqualTo("simpolor");
        Assertions.assertThat(actual.getHobbies()).isEqualTo(Arrays.asList("축구"));
    }
}
