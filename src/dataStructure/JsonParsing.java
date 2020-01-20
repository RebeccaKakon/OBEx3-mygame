package dataStructure;

//import org.codehaus.jackson.JsonParser;

//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
/**
 * help us to read info fron json
 * 
 *
 */

public class JsonParsing {

	static JsonParser parser = new JsonParser();

	public static HashMap<String, Object> createHashMapFromJsonString(String json) {

		JsonObject object = null;
		try {
			object = (JsonObject) parser.parse(json);
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		Set<Map.Entry<String, JsonElement>> set = object.entrySet();
		Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
		HashMap<String, Object> map = new HashMap<String, Object>();

		while (iterator.hasNext()) {

			Map.Entry<String, JsonElement> entry = iterator.next();
			String key = entry.getKey();
			JsonElement value = entry.getValue();

			if (null != value) {
				if (!value.isJsonPrimitive()) {
					if (value.isJsonObject()) {

						map.put(key, createHashMapFromJsonString(value.toString()));
					} else if (value.isJsonArray() && value.toString().contains(":")) {

						List<HashMap<String, Object>> list = new ArrayList<>();
						JsonArray array = value.getAsJsonArray();
						if (null != array) {
							for (JsonElement element : array) {
								list.add(createHashMapFromJsonString(element.toString()));
							}
							map.put(key, list);
						}
					} else if (value.isJsonArray() && !value.toString().contains(":")) {
						map.put(key, value.getAsJsonArray());
					}
				} else {
					map.put(key, value.getAsString());
				}
			}
		}
		return map;
	}
	public static void main(String[] args) {
		HashMap<String, Object> hmap = createHashMapFromJsonString("{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":4.16,\"pressure\":1022,\"humidity\":93,\"temp_min\":2.78,\"temp_max\":6.11},\"visibility\":8000,\"wind\":{\"speed\":3.6,\"deg\":90},\"clouds\":{\"all\":9},\"dt\":1575112876,\"sys\":{\"type\":1,\"id\":1414,\"country\":\"GB\",\"sunrise\":1575099726,\"sunset\":1575129384},\"timezone\":0,\"id\":2643743,\"name\":\"London\",\"cod\":200}");
		System.out.println(hmap);
		//parse("example.json");
	}

}

