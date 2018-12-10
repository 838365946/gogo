package com.example.main.service.impl;

import com.example.main.dao.CompanyDao;
import com.example.main.model.Company;
import com.example.main.service.CompanyService;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServiceImpl  implements CompanyService {

    private CompanyDao companyDao;
    @Override
    public Company registered(Company company) {
        Company company1=companyDao.save(company);
        return company1;

    }
}
