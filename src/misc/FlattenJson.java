package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FlattenJson {

	//TODO : Reset ParentPath every-time we back track to parent after appending its children. then return parent & then again reset for parent above it recurse.
	
	private static final Logger LOGGER = Logger.getLogger(FlattenJson.class.getName());
	private final List<String> keys = new ArrayList<>();

	public Map<String, String> flatten(Map<String, Object> jsonInput) {

		Map<String, String> jsonOutput = new HashMap<>();
		
		for (Map.Entry<String, Object> entry : jsonInput.entrySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(entry.getKey());

			Object value = entry.getValue();
			appendValueToParent(value, sb);
			
			System.out.println(entry.getKey()+","+entry.getValue()+","+ sb);
			
			String key = sb.substring(0, sb.lastIndexOf("."));
			String val = sb.substring(sb.lastIndexOf("."), sb.length());
			jsonOutput.put(key, val);
		}
		
		return jsonOutput;
	}

	@SuppressWarnings("unchecked")
	private void appendValueToParent(Object value, StringBuilder sb) {

		if (value instanceof Map) {
			Map<String, String> subJson = flatten((Map<String, Object>) value);
			for (Map.Entry<String, String> entry : subJson.entrySet()) {
				sb.append(".").append(entry.getKey()).append(".").append(entry.getValue());
			}
		} else if (value instanceof List) {
			List<Object> subList = (List<Object>) value;
			for (int idx = 0; idx < subList.size(); idx++) {
				sb.append(".").append(idx);
				appendValueToParent(subList.get(idx), sb);
				keys.add(sb.toString());
			}
		} else {
			sb.append(".").append(value);
		}
	}

	public static void main(String[] args) throws JsonProcessingException {

		String json = "{\n"
				+ "    \"key1\": {\n"
				+ "        \"key2\": \"value2\",\n"
				+ "        \"key3\": [\"value3\", \"value4\", {\"key5\": \"value5\"}, 6]\n"
				+ "    },\n"
				+ "    \"key4\": \"value4\",\n"
				+ "    \"key6\": [1, 2, 3, 4],\n"
				+ "    \"key7\": {\n"
				+ "        \"key8\": \"value8\",\n"
				+ "        \"key9\": false\n"
				+ "    }\n"
				+ "}";

		ObjectMapper objMapper = new ObjectMapper();
		Map<String, Object> deserialisedJsonData = objMapper.readValue(json, new TypeReference<Map<String, Object>>() {
		});
		FlattenJson fj = new FlattenJson();
		LOGGER.log(Level.INFO, "Converted List {} ", fj.keys);
		Map<String,String> convertedData = fj.flatten(deserialisedJsonData);
		String convertedJson = objMapper.writeValueAsString(convertedData);
		LOGGER.log(Level.INFO, "Converted Json {} ", convertedJson);
	}

}
