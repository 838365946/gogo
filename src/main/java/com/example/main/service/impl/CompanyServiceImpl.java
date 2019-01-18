package com.example.main.service.impl;

import com.example.main.dao.CompanyDao;
import com.example.main.model.Company;
import com.example.main.service.CompanyService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl  implements CompanyService {
@Autowired
    private CompanyDao companyDao;
private CompanyIO companyIO=new CompanyIO();
    @Override
    public Company registered(Company company) {
        Company company1=companyDao.save(company);
        return company1;
    }

    @Override
    public Page<Company> findall(Pageable pageable) {
        Page<Company> companies=companyDao.findAll(pageable);
        for(Company c:companies.getContent()){
            c.setC_des(String.valueOf(companyIO.ReadDes(c.getC_des())));
        }
        return companies;
    }

    @Override
    public Company addcompany(Company company) {
        Company company1=new Company();
        try {
           company1 =companyDao.save(company);
        }catch (Exception e){
        company1=null;
        }
        return company1;
    }

    @Override
    public Company CLogin(Company company) {
        Company company1=companyDao.CLogin(company.getC_username(),company.getC_password());
        company1.setC_des(String.valueOf(companyIO.ReadDes(company1.getC_des())));
        return company1;
    }



    @Override
    public List<Company> CheckCompany(String status) {
        return companyDao.chenckcompany(status);
    }

    @Override
    public int PassCheck(Company company,String str) {

        return companyDao.PassCheck(company.getC_id(),str);
    }
    @Override
    public int PassChecka(Company company,String str){
        return companyDao.PassChecka(company.getC_id(),str);
    }

    @Override
    public List<Company> CheckCname(String cname) {
        return companyDao.checkcname(cname);
    }

    @Override
    public List<Company> CheckCusername(String cusername) {
        return companyDao.checkcusername(cusername);
    }
    @Override
    public List<Company> QueryBystate(String state) {
List<Company> companies=companyDao.QueryBystate(state);
for(Company c:companies){
    c.setC_des(String.valueOf(companyIO.ReadDes(c.getC_des())));
}
        return companies;
    }
    @Override
    public Company QueryByCname(String cname) {
Company company=companyDao.QueryByCname(cname);
company.setC_des(String.valueOf(companyIO.ReadDes(company.getC_des())));
        return company;
    }

    @Override
    public Company findByid(int cid) {
        return companyDao.findById(cid);
    }


}
