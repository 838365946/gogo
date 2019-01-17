package com.example.main.service.impl;

import com.example.main.dao.PositionDao;
import com.example.main.model.Company;
import com.example.main.model.Position;
import com.example.main.service.FindPositionService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/12/6.
 */
@Service("fp")
public class FindPositionServiceImpl implements FindPositionService {


@Autowired
    private PositionDao positionDao;
private CompanyIO companyIO=new CompanyIO();
    @Override
    public List<Position> ShowMess(Pageable pageable) {
        Page<Position> positionses=  positionDao.findAllS(pageable);
                List<Position> positions=positionses.getContent();
        Set<Company> companies=new HashSet<Company>();
        for(int i=0;i<positions.size();i++){
            companies.add(positions.get(i).getCompany());
            positions.get(i).setP_des(String.valueOf(companyIO.ReadDes(positions.get(i).getP_des())));
        }
        System.out.println(companies.toString());
        for(Company company:companies){
            company.setC_des(String.valueOf(companyIO.ReadDes(company.getC_des())));
        }
        return  positions;
    }

    @Override
    public Page<Position> QueryByInput(String input,Pageable pageable) {
        return  positionDao.QueryByInput(input,pageable);
    }

}
