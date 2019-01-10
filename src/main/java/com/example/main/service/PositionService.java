package com.example.main.service;

import com.example.main.model.Position;

import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */
public interface PositionService {
    Position addposition(Position position);
    List<Position> QueryByCompany(int cid);
    Position QueryById(int p_id);
}
