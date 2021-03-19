package io.simpolor.json.test;

import com.google.gson.*;
import io.simpolor.json.model.Student;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GsonTest {

    @Test
    public void testObjectToJsonStr() {

        // given
        Student student = new Student();
        student.setSeq(1);
        student.setName("simpolor");
        student.setHobbies(Arrays.asList("soccer"));

        // when
        Gson gson = new Gson();
        String actual = gson.toJson(student);

        // print
        System.out.println(actual);

        // then
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    public void testJsonStrToObject() {

        // given
        String jsonStr = "{\"seq\":1,\"name\":\"simpolor\",\"hobbies\":[\"soccer\"]}";

        // when
        Gson gson = new Gson();
        Student actual = gson.fromJson(jsonStr, Student.class);

        // print
        System.out.println(actual.toString());

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSeq()).isEqualTo(1);
    }

    @Test
    public void testJsonStrToJsonElement() {

        // given
        String jsonStr = "{\"seq\":1,\"name\":\"simpolor\",\"hobbies\":[\"soccer\"]}";

        // when
        JsonParser parser = new JsonParser();
        JsonElement actual = parser.parse(jsonStr);

        // print
        System.out.println(actual);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getAsJsonObject().get("name").getAsString()).isEqualTo("simpolor");
    }

    @Test
    public void testMakeJsonObject() {

        // given
        JsonArray hobbies = new JsonArray();
        hobbies.add("soccer");

        JsonObject object = new JsonObject();
        object.addProperty("seq", 1);
        object.addProperty("name", "simpolor");
        object.add("hobbies", hobbies);

        // when
        Gson gson = new Gson();
        String actual = gson.toJson(object);

        // print
        System.out.println(actual);

        // then
        Assertions.assertThat(actual).isNotNull();
    }
}
