package io.simpolor.json.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.json.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JacksonTest {

    @Test
    public void testObjectToJsonStr() throws JsonProcessingException {

        // given
        Student student = new Student();
        student.setSeq(1);
        student.setName("simpolor");
        student.setHobbies(Arrays.asList("soccer"));

        // when
        ObjectMapper objectMapper = new ObjectMapper();
        String actual = objectMapper.writeValueAsString(student);

        // print
        System.out.println(actual);

        // then
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    public void testJsonStrToObject() throws JsonProcessingException {

        // given
        String jsonStr = "{\"seq\":1,\"name\":\"simpolor\",\"hobbies\":[\"soccer\"]}";

        // when
        ObjectMapper objectMapper = new ObjectMapper();
        Student actual = objectMapper.readValue(jsonStr, Student.class);

        // print
        System.out.println(actual.toString());

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getSeq).isEqualTo(1L);
    }

    @Test
    public void testJsonArrayToList() throws JsonProcessingException {

        // given
        String jsonStr = "{\"seq\":1,\"name\":\"simpolor\",\"hobbies\":[\"soccer\"]}";
        String jsonStr2 = "{\"seq\":2,\"name\":\"simpolor2\",\"hobbies\":[\"soccer2\"]}";
        String jsonArray = "["+jsonStr+","+jsonStr2+"]";

        // when
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> actual = objectMapper.readValue(jsonArray, new TypeReference<List<Student>>() {});

        // print
        System.out.println(actual.toString());

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).first().extracting(Student::getSeq).isEqualTo(1L);
    }

    @Test
    public void testJsonStrToMap() throws JsonProcessingException {

        // given
        String jsonStr = "{\"seq\":1,\"name\":\"simpolor\",\"hobbies\":[\"soccer\"]}";

        // when
        ObjectMapper objectMapper = new ObjectMapper();
        Map actual = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});

        // print
        System.out.println(actual.toString());

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).contains(Assertions.entry("seq", 1));
    }

    @Test
    public void testConfigureByUnknownProperties() throws JsonProcessingException {

        // given
        String jsonStr = "{\"seq\":1,\"name2\":\"simpolor\",\"hobbies\":[\"soccer\"]}";

        // when
        ObjectMapper objectMapper = new ObjectMapper();
        // 존재하지 않는 필드 무시 설정
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Student actual = objectMapper.readValue(jsonStr, Student.class);

        // print
        System.out.println(actual.toString());

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1);
        Assertions.assertThat(actual.getName()).isNull();
    }


}
