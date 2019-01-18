package com.example.main.service;

import com.example.main.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2018/12/6.
 */
public interface FindPositionService {
    Page<Position> ShowMess(Pageable pageable);
Page<Position> QueryByInput(String input, Pageable pageable);
}
