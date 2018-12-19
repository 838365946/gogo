package com.example.main.service.impl;

import com.example.main.dao.PositionDao;
import com.example.main.model.Position;
import com.example.main.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/12/19.
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService{
    @Autowired
    PositionDao positionDao;
    @Override
    public Position addposition(Position position) {
        return positionDao.save(position);
    }
}
