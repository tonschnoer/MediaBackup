package de.kmt.MediaBackup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Config {

	private static String config = "{ \"server\" : \"127.0.0.1\",\"port\" : \"12345\"}";
	
	protected static final Logger logger = LogManager.getLogger();
	
	private static Map<String, String> configmap;
	
	
	public static String getConfigKey(String key) {
		return configmap.getOrDefault(key, "n/a");
	}
	
	public static String readConfig() {
		String result="";
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("E:\\Daten\\config.json"));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		   result = sb.toString();
		} catch (Exception ex) {
			
		} finally {
		    try {
				br.close();
			} catch (Exception e) {
 
				logger.error("Error reading config file:" + e.getMessage());
			}
		}
		return result;
	}
	
	public static void parseConfig() 
			  throws JsonParseException, JsonMappingException, IOException {
		
		logger.info("Read config()");
				config = readConfig();
			    configmap = getJsonAsMap(config);
			    
			    for (Map.Entry<String, String> entry : configmap.entrySet()) {
			        logger.info("read config: " + entry.getKey() + "=" + entry.getValue());
			    }
			}
	
	public static HashMap<String, String> getJsonAsMap(String json)
	{
	    try
	    {
	        ObjectMapper mapper = new ObjectMapper();
	        TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
	        HashMap<String, String> result = mapper.readValue(json, typeRef);

	        return result;
	    }
	    catch (Exception e)
	    {
	        throw new RuntimeException("Couldnt parse json:" + json, e);
	    }
	}
}
