package com.weather.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class Task  {

	
	public List<Temperature> httpcall() {
		
		 StringBuilder responseText = new StringBuilder();   
	     //not registered
		// String url ="http://samples.openweathermap.org/data/2.5/forecast?id=1277333&appid=b1b15e88fa797225412429c1c50c122a1";
	        
		 List<Temperature>  temperature=new ArrayList<>(); 
		 
		 Temperature t=null;
		 
		 //registered key
		String url="http://api.openweathermap.org/data/2.5/forecast?id=1277333&APPID=5904f14dc7ae8bdf91ca5f07290c7132";
	        
		 try
	        {          
	            URL postURL = new URL(url);
	            HttpURLConnection conn = (HttpURLConnection) postURL.openConnection();
	            conn.setDoInput(true);
	            conn.setRequestProperty("Content-Type","json");
	            conn.setRequestMethod("GET");
	            
	            int rcode = conn.getResponseCode();
	            System.out.println(rcode);

	            if (rcode != -1)
	            {
	                BufferedReader istream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                String line = null;
	               
	                while (((line = istream.readLine()) != null))
	                {
	                    responseText.append(line+"\r\n");
	                }
	            } 
	            
	            String result=responseText.toString();
	        
	            JSONParser parser = new JSONParser();
	            
				JSONObject jsonObject = (JSONObject) parser.parse(result);
				
	            JSONArray l=(JSONArray)jsonObject.get("list");
	            
	            for(Object o: l){
					
					JSONObject list=(JSONObject)o;
					
					JSONObject main=(JSONObject)list.get("main");
					
					String dt_txt=(String)list.get("dt_txt");
					
					double temp=(Double)main.get("temp");
				    double temp_min=(Double) main.get("temp_min");
				    double temp_max=(Double)main.get("temp_max");
					
				    temp=temp-(double)273.15;
				    temp_min=temp_min-(double)273.15;
				    temp_max=temp_max-(double)273.15;
				   
				    t=new Temperature(dt_txt,temp_min,temp_max,temp);
				    temperature.add(t);
				}
	          return temperature;
	            
	        }
	        catch (IOException e)
	        {
	        	
	        }
	        catch (Exception e)
	        {    
	        	
	        }	
	    return null;
	}
}
