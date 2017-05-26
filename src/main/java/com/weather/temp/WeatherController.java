package com.weather.temp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping("/weather")
	public List<Temperature> getAllTemperature() throws java.text.ParseException {
		return weatherService.getAllTemperature();
	}
	
	
	@RequestMapping("/weather/{foo}")
	public Temperature getTemperature(@PathVariable("foo") String id){
		return weatherService.getTemperature(id);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/weather") 
	public void addTemperature(@RequestBody Temperature temperature){
		weatherService.addTemperature(temperature);	
	 }
	
	@RequestMapping(method=RequestMethod.PUT, value="/weather/{id}")
	public void updateTemperature(@RequestBody Temperature temperature,@PathVariable String id){
		weatherService.updateTemperature(id, temperature);
	}
	    
	@RequestMapping(method=RequestMethod.DELETE, value="/weather/{foo}")
	public void deleteTemperature(@PathVariable("foo") String id){
		weatherService.deleteTemperature(id);
	}
	
	
	@RequestMapping("/api/{nowDate}")
	public Temperature api(@PathVariable("nowDate") String currDateStr) throws java.text.ParseException{
		DateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date currDate=formatter.parse(currDateStr);
		Date date=null;	
	    Temperature t=null;
	    List<Temperature> list=weatherService.getAllTemperature();
	    Iterator itr=list.iterator(); 
	    while(itr.hasNext()){
	       t=(Temperature)itr.next();	
		   String d=t.getDate();
		   date=formatter.parse(d);
		   if(date.compareTo(currDate)>0){
			   break;
		   }		 
	    }
	  return t;
	}
	
	@Autowired
	private Task task;
	
	private List<Temperature> temperature;
	
	static int count=0;
	
	 @Scheduled(fixedRate = 900000)
	 public void reportCurrentTime() {
		 count++;
		 System.out.println("Called in 2 minutes count= "+count);
		 weatherService.deleteAll();
		 temperature=task.httpcall();
		 Iterator itr=temperature.listIterator();
		 while(itr.hasNext()){
			 weatherService.addTemperature((Temperature)itr.next());
		 }
	 }
	 
}
