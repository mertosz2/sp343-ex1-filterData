package com.example.ex1.model;

import lombok.Data;

@Data
public class Covid {

    private int year;
    private int weeknum;
    private String province;
    private int new_case;
    private int total_case;
    private int new_case_excludeabroad;
    private int total_case_excludeabroad;
    private int new_death;
    private int total_death;
    private String update_date;

}
