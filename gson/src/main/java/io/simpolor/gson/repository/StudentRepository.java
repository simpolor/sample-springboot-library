package io.simpolor.gson.repository;

import com.google.gson.Gson;
import io.simpolor.gson.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class StudentRepository {

    public static Long INDEX = 1L;
    public static Map<Long, String> studentMap = new HashMap<>();

    private  final Gson gson = new Gson();

    public List<Student> findAll(){

        String data = studentMap.keySet().stream()
                .map(key -> studentMap.get(key))
                .collect(Collectors.joining(", ", "[", "]"));

        if(StringUtils.isEmpty(data)){
            return Collections.EMPTY_LIST;
        }

        return gson.fromJson(data, new ListOfJson<>(Student.class));
    }

    public Optional<Student> findById(Long studentId){

        String data = studentMap.get(studentId);
        if(Objects.nonNull(data)){
            return Optional.of(gson.fromJson(data, Student.class));
        }

        return Optional.empty();
    }

    public Student save(Student student){

        if(Objects.isNull(student.getStudentId())){
            student.setStudentId(INDEX++);
        }

        studentMap.put(student.getStudentId(), gson.toJson(student));

        return student;
    }

    public void deleteById(Long studentId){

        if(studentMap.containsKey(studentId)){
            studentMap.remove(studentId);
        }
    }
}
