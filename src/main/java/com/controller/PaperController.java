package com.controller;

import com.entities.BasePage;
import com.entities.Itab;
import com.entities.Paper;
import com.service.ItabService;
import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private ItabService itabService;

    @RequestMapping("/allPaper")
    public String list(Model model, BasePage basePage, HttpServletRequest request) {
        if (basePage.getPageNum()==0){
            basePage.setPageNum(1);
        }
        basePage.setRecordTotal(paperService.queryRecordTotal());
        if (basePage.getRecordTotal()%basePage.getPageSize()==0)
            basePage.setPageTotal(basePage.getRecordTotal()/basePage.getPageSize());
        else
            basePage.setPageTotal(basePage.getRecordTotal()/basePage.getPageSize()+1);
        List<Paper> list = paperService.queryPaperByPage(basePage);
        if(request.getParameter("delMsg")!=null){
            if(request.getParameter("delMsg").equals("0"))
                model.addAttribute("message","删除失败");
            else if(request.getParameter("delMsg").equals("1"))
                model.addAttribute("message","删除成功");
            else if(request.getParameter("delMsg").equals("-1"))
                model.addAttribute("message","请选择需要删除项");
        }
        model.addAttribute("page",basePage);
        model.addAttribute("list", list);
        model.addAttribute("paperName","");
        return "allPaper";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model,Paper paper,BasePage basePage){
        if (basePage.getPageNum()==0){
            basePage.setPageNum(1);
        }
        basePage.setRecordTotal(paperService.queryRecordTotalByName(paper));
        if (basePage.getRecordTotal()%basePage.getPageSize()==0)
            basePage.setPageTotal(basePage.getRecordTotal()/basePage.getPageSize());
        else
            basePage.setPageTotal(basePage.getRecordTotal()/basePage.getPageSize()+1);
        List<Paper> list = paperService.queryPaperByName(paper,basePage);
        model.addAttribute("list",list);
        model.addAttribute("page",basePage);
        model.addAttribute("search_flag",true);
        model.addAttribute("paperName",paper.getPaperName());
        return "allPaper";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchGet(Model model,Paper paper,BasePage basePage){
        if (basePage.getPageNum()==0){
            basePage.setPageNum(1);
        }
        basePage.setRecordTotal(paperService.queryRecordTotalByName(paper));
        if (basePage.getRecordTotal()%basePage.getPageSize()==0)
            basePage.setPageTotal(basePage.getRecordTotal()/basePage.getPageSize());
        else
            basePage.setPageTotal(basePage.getRecordTotal()/basePage.getPageSize()+1);
        List<Paper> list = paperService.queryPaperByName(paper,basePage);
        model.addAttribute("list",list);
        model.addAttribute("page",basePage);
        model.addAttribute("search_flag",true);
        model.addAttribute("paperName",paper.getPaperName());
        return "allPaper";
    }

    @RequestMapping("toAddPaper")
    public String toAddPaper() {
        return "addPaper";
    }

    @RequestMapping("/addPaper")
    public String addPaper(Paper paper) {
        paperService.addPaper(paper);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping("/del/{paperId}")
    public String deletePaper(@PathVariable("paperId") Long id) {
        paperService.deletePaperById(id);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping(value = "/delpush",method = RequestMethod.POST)
    public String deletePush(Model model,String[] ids){
        int msg = 0;
        if (ids!=null&&ids.length > 0) {
            msg =  paperService.deletePush(ids) > 0 ? 1 : 0;
        } else {
            msg = -1;
        }
        model.addAttribute("message",msg);
        return "redirect:/paper/allPaper?delMsg="+msg;
    }

    @RequestMapping("toUpdatePaper")
    public String toUpdatePaper(Model model, Long id) {
        Paper paper = paperService.queryById(id);
        model.addAttribute("paper",paper);
        return "updatePaper";
    }

    @RequestMapping("/updatePaper")
    public String updatePaper(Model model, Paper paper) {
        paperService.updatePaper(paper);
        paper = paperService.queryById(paper.getPaperId());
        model.addAttribute("paper", paper);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping("/itab")
    public String itab(Model model){
        List<Itab> itabs = itabService.queryAllItab();
        model.addAttribute("itab",itabs);
        return "itab";
    }
}
