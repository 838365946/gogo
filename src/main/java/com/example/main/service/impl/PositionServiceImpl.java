package com.example.main.service.impl;

import com.example.main.dao.PositionDao;
import com.example.main.model.Position;
import com.example.main.service.PositionService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
        List<Position> positions=  positionDao.QueryByCompany(cid);


        return  positions;
    }

    @Override
    public Position QueryById(int p_id) {
        Position position=positionDao.QueryById(p_id);

        return position;
    }

    @Override
    public int DelPOsition(int pid) {
        return positionDao.DelPosition(pid);
    }
}
