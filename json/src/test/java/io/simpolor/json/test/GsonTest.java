package io.simpolor.json.test;

import com.google.gson.*;
import io.simpolor.json.model.StudentRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GsonTest {

    @Test
    public void testObjectToJsonStr() {

        // given
        StudentRequest request = new StudentRequest();
        request.setSeq(1);
        request.setName("simpolor");
        request.setHobbies(Arrays.asList("soccer"));

        // when
        Gson gson = new Gson();
        String actual = gson.toJson(request);

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
        StudentRequest actual = gson.fromJson(jsonStr, StudentRequest.class);

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
