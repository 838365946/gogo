package com.example.main.service;

import com.example.main.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    Company registered(Company company);
    Page<Company> findall(Pageable pageable);
    Company addcompany(Company company);
    Company CLogin(Company company);

    List<Company> CheckCompany(String status);
    int PassCheck(Company compan,String str);
}
