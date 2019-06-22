package com.service;

import com.entities.BasePage;
import com.entities.Paper;

import java.util.List;

public interface PaperService {
    int addPaper(Paper paper);

    int deletePaperById(long id);

    int deletePush(String[] ids);

    int updatePaper(Paper paper);

    Paper queryById(long id);

    List<Paper> queryAllPaper();

    List<Paper> queryPaperByPage(BasePage basePage);

    int queryRecordTotal();

    List<Paper> queryPaperByName(Paper paper,BasePage basePage);

    int queryRecordTotalByName(Paper paper);
}
