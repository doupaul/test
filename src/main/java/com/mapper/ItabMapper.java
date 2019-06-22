package com.mapper;

import com.entities.Itab;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItabMapper {

    @Select({"SELECT * FROM itab"})
    List<Itab> queryAllItab();
}
