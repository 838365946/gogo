package com.example.main.service.impl;

import com.example.main.dao.CompanyDao;
import com.example.main.model.Company;
import com.example.main.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl  implements CompanyService {
@Autowired
    private CompanyDao companyDao;
    @Override
    public Company registered(Company company) {
        Company company1=companyDao.save(company);
        return company1;
    }

    @Override
    public Page<Company> findall(Pageable pageable) {
        Page<Company> companies=companyDao.findAll(pageable);
        return companies;
    }

    @Override
    public Company addcompany(Company company) {
        Company company1=companyDao.save(company);
        return company1;
    }

    @Override
    public Company CLogin(Company company) {
        System.out.println(company.toString());
        return companyDao.CLogin(company.getC_username(),company.getC_password());
    }

    @Override
    public void test(int id) {
        System.out.println(companyDao.findById(id).getDeliveries());
    }

    @Override
    public List<Company> CheckCompany(String status) {
        return companyDao.chenckcompany(status);
    }

    @Override
    public Company PassCheck(Company company,String str) {

        return companyDao.PassCheck(company.getC_id(),str);
    }


}
