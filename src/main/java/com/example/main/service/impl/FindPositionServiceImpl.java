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

import java.util.ArrayList;
import java.util.List;

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
        List<Company> companies=new ArrayList<Company>();
        for(int i=0;i<positions.size();i++){
            if(companies.size()>0){
                for (int y=0;y<companies.size();y++){
                    if (positions.get(i).getCompany()!=companies.get(y)){
                        String des=positions.get(i).getCompany().getC_des();
                        String str= String.valueOf(companyIO.ReadDes(des));
                        positions.get(i).getCompany().setC_des(str);
                        companies.add(positions.get(i).getCompany());
                    }
                }}else {
                String des=positions.get(i).getCompany().getC_des();
                String str= String.valueOf(companyIO.ReadDes(des));
                positions.get(i).getCompany().setC_des(str);
                companies.add(positions.get(i).getCompany());
            }
        }



        return  positions;
    }

    @Override
    public Page<Position> QueryByInput(String input,Pageable pageable) {
        return  positionDao.QueryByInput(input,pageable);
    }

}
