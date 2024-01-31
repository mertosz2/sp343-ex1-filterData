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
import java.util.stream.Collectors;

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
                filteredData.add(covid);
            }
        }

        return filteredData;
    }

    //using stream
    @GetMapping("/covid2/{province}")
    public List<Covid> findByProvince2(@PathVariable String province){
        RestTemplate restTemplate2 = new RestTemplate();
        String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-by-provinces";

        ParameterizedTypeReference<List<Covid>> responseType2 = new ParameterizedTypeReference<List<Covid>>() {};
        ResponseEntity<List<Covid>> responseEntity2 = restTemplate2.exchange(url, HttpMethod.GET, null, responseType2);
        List<Covid> covidData2 = responseEntity2.getBody();

        List<Covid> filteredData2 = covidData2.stream()
                                            .filter(covid -> province.equalsIgnoreCase(covid.getProvince()))
                                            .collect(Collectors.toList());
        return filteredData2;
    }
}
