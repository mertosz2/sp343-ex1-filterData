package com.example.ex1.controller;

import com.example.ex1.model.Covid;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CovidController {
    @GetMapping("/getAll")
    public List<Covid> test(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-by-provinces";

        ParameterizedTypeReference<List<Covid>> responseType = new ParameterizedTypeReference<List<Covid>>() {};
        ResponseEntity<List<Covid>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Covid> covidData = responseEntity.getBody();

        return covidData;

    }

    @GetMapping("/covid/{province}")
    public List<Covid> findByProvince(@PathVariable String province){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-by-provinces";

        ParameterizedTypeReference<List<Covid>> responseType = new ParameterizedTypeReference<List<Covid>>() {};
        ResponseEntity<List<Covid>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Covid> covidData = responseEntity.getBody();

        List<Covid> filteredData = new ArrayList<>();
        for(Covid covid : covidData){
            if(covid.getProvince().equalsIgnoreCase(province)){
                return filteredData;
            }
        }

        return filteredData;
    }
}
