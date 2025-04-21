package genericUtilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {

	public String toGetDataFromJson(String Key) throws ParseException {
		
		JSONParser parse = new JSONParser();
		Object JsonObj = parse.parse("Path");
		JSONObject jo = (JSONObject)JsonObj;
		String data = (String) jo.get(Key);
		
		return data;
		
	}

}
