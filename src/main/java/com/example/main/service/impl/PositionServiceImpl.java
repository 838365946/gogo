package com.example.main.service.impl;

import com.example.main.dao.PositionDao;
import com.example.main.model.Company;
import com.example.main.model.Position;
import com.example.main.service.PositionService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService{
    @Autowired
    PositionDao positionDao;
    private CompanyIO companyIO=new CompanyIO();

    @Override
    public Position addposition (Position position) {
        String des =position.getP_des();
        position.setP_des("上传出错");
        Position position1=positionDao.save(position);
        Position position2=null;
        try {
            String path=companyIO.WritePositionDes(des,position1.getP_id());
            position1.setP_des(path);
           position2=positionDao.save(position1);
        } catch (IOException e) {
            position2=null;
        }
        return position2;
    }

    @Override
    public List<Position> QueryByCompany (int cid) throws NullPointerException{
        List<Position> positions=positionDao.QueryByCompany(cid);
        List<Company> companies=new ArrayList<Company>();
        for(int i=0;i<positions.size();i++){
            if(companies.size()>0){
                for (Company company:companies){
                    if (positions.get(i).getCompany()!=company){
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
        return positionDao.QueryByCompany(cid);
    }

    @Override
    public Position QueryById(int p_id) {
        return positionDao.QueryById(p_id);
    }

    @Override
    public int DelPOsition(int pid) {
        return positionDao.DelPosition(pid);
    }
}
