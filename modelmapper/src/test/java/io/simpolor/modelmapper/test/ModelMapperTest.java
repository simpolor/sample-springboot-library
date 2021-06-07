package io.simpolor.modelmapper.test;

import io.simpolor.modelmapper.model.StudentDto;
import io.simpolor.modelmapper.model.StudentMappingDto;
import io.simpolor.modelmapper.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ModelMapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testMap(){

        // given
        StudentDto studentDto = new StudentDto();
        studentDto.setSeq(1L);
        studentDto.setName("simpolor");
        studentDto.setHobbies(Arrays.asList("축구"));

        // when
        Student actual = modelMapper.map(studentDto, Student.class);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1L);
        Assertions.assertThat(actual.getName()).isEqualTo("simpolor");
        Assertions.assertThat(actual.getHobbies()).isEqualTo(Arrays.asList("축구"));
    }

    @Test
    public void testAddMapping(){

        // config
        modelMapper.createTypeMap(StudentMappingDto.class, Student.class)
                .addMapping(StudentMappingDto::getStudentSeq, Student::setSeq)
                .addMapping(StudentMappingDto::getStudentName, Student::setName)
                .addMapping(StudentMappingDto::getStudentHobbies, Student::setHobbies);

        // given
        StudentMappingDto studentMappingDto = new StudentMappingDto();
        studentMappingDto.setStudentSeq(1L);
        studentMappingDto.setStudentName("simpolor");
        studentMappingDto.setStudentHobbies(Arrays.asList("축구"));

        // when
        Student actual = modelMapper.map(studentMappingDto, Student.class);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1L);
        Assertions.assertThat(actual.getName()).isEqualTo("simpolor");
        Assertions.assertThat(actual.getHobbies()).isEqualTo(Arrays.asList("축구"));
    }

    @Test
    public void testAddMappings(){

        // config
        modelMapper.createTypeMap(StudentMappingDto.class, Student.class)
                .addMappings(mapping -> {
                    mapping.map(source -> source.getStudentSeq(), Student::setSeq);
                    mapping.map(source -> source.getStudentName(), Student::setName);
                    mapping.map(source -> source.getStudentHobbies(), Student::setHobbies);
                    // mapping.skip(Student::setSeq);
                });

        // given
        StudentMappingDto studentMappingDto = new StudentMappingDto();
        studentMappingDto.setStudentSeq(1L);
        studentMappingDto.setStudentName("simpolor");
        studentMappingDto.setStudentHobbies(Arrays.asList("축구"));

        // when
        Student actual = modelMapper.map(studentMappingDto, Student.class);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1L);
        Assertions.assertThat(actual.getName()).isEqualTo("simpolor");
        Assertions.assertThat(actual.getHobbies()).isEqualTo(Arrays.asList("축구"));
    }
}
