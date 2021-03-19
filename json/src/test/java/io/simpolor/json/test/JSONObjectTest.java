package io.simpolor.json.test;

import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class JSONObjectTest {

    @Test
    public void testJsonStrToJSONObject() throws JSONException {

        // given
        String jsonStr =
                "{\n" +
                "    \"seq\": 1,\n" +
                "    \"name\": \"simpolor\",\n" +
                "    \"hobbies\": [\"soccer\"]\n" +
                "}";

        // when
        JSONObject actual = new JSONObject(jsonStr);

        // print
        System.out.println(actual);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.get("seq")).isEqualTo(1);
        Assertions.assertThat(actual.getJSONArray("hobbies").get(0)).isEqualTo("soccer");
    }

    @Test
    public void testJSONObjectToJsonStr() throws JSONException {

        // given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("seq", 1);
        jsonObject.put("name", "simpolor");

        JSONArray hobbies = new JSONArray();
        hobbies.put("soccer");
        jsonObject.put("hobbies", hobbies);

        // when
        String actual = jsonObject.toString();

        // print
        System.out.println(actual);

        // then
        Assertions.assertThat(actual).isNotNull();
    }
}
