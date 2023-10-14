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
    private String companyId;

    private String companyPass;
    private String companyName;
    private String companyCountry;
    private String companyArea;

}
