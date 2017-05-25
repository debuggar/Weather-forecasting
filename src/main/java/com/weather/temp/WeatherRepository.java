package com.weather.temp;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Temperature,String>{

}
