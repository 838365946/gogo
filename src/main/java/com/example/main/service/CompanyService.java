package com.example.main.service;

import com.example.main.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    Company registered(Company company);
    Page<Company> findall(Pageable pageable);
    Company addcompany(Company company);
}
