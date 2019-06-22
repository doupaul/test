package com.service.impl;

import com.entities.Itab;
import com.mapper.ItabMapper;
import com.service.ItabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItabServiceImpl implements ItabService {

    @Autowired
    private ItabMapper itabMapper;

    @Override
    public List<Itab> queryAllItab() {
        return itabMapper.queryAllItab();
    }
}
