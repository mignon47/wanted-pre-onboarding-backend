package com.test.wantedpreonboardingbackend.company;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Company {

    @Id
    private Long company_id;

    private String company_pass;
    private String company_name;
    private String company_country;
    private String company_area;

}
