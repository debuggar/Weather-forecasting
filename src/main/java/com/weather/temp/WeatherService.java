package com.weather.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;
	
    public List<Temperature> getAllTemperature(){
    	List<Temperature> weather=new ArrayList<>();
    	weatherRepository.findAll().forEach(weather::add);
    	return weather;
    }
    
    public Temperature getTemperature(String id){
         return weatherRepository.findOne(id);
    }

	public void addTemperature(Temperature temp) {
		weatherRepository.save(temp);	
	}

	public void updateTemperature(String id, Temperature temp) {
		weatherRepository.save(temp);
	}

	public void deleteTemperature(String id) {
		weatherRepository.delete(id);
	}
	
	public void deleteAll(){
		weatherRepository.deleteAll();
	}
}
