package com.service.impl;

import com.entities.BasePage;
import com.entities.Paper;
import com.mapper.PaperMapper;
import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;

    public int addPaper(Paper paper) {
        return paperMapper.addPaper(paper);
    }

    public int deletePaperById(long id) {
        return paperMapper.deletePaperById(id);
    }

    public int deletePush(String[] ids) {
        int count=0;
        String idStr="";
        for (String i:ids){
            idStr += i;
            if(count<ids.length-1)
                idStr += ",";
            count++;
        }
        return paperMapper.deletePush(idStr);
    }

    public int updatePaper(Paper paper) {
        return paperMapper.updatePaper(paper);
    }

    public Paper queryById(long id) {
        return paperMapper.queryById(id);
    }

    public List<Paper> queryPaperByPage(BasePage basePage) {
        basePage.setPageBegin((basePage.getPageNum()-1)*basePage.getPageSize());
        return paperMapper.queryPaperByPage(basePage);
    }

    public int queryRecordTotal() {
        return paperMapper.queryRecordTotal();
    }

    public List<Paper> queryAllPaper() {
        return paperMapper.queryAllPaper();
    }

    public List<Paper> queryPaperByName(Paper paper,BasePage basePage){
        basePage.setPageBegin((basePage.getPageNum()-1)*basePage.getPageSize());
        return paperMapper.queryPaperByName(paper.getPaperName(),basePage.getPageBegin(),basePage.getPageSize());
    }

    @Override
    public int queryRecordTotalByName(Paper paper) {
        return paperMapper.queryRecordTotalByName(paper);
    }
}
