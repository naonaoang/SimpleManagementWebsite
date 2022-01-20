package com.example.spring_auth_server.controller;

import com.example.spring_auth_server.entity.HistoryUnit;
import com.example.spring_auth_server.entity.Choice;
import com.example.spring_auth_server.entity.Question;
import com.example.spring_auth_server.entity.Report;
import com.example.spring_auth_server.service.ChoiceService;
import com.example.spring_auth_server.service.QuestionService;
import com.example.spring_auth_server.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailController {
    @Autowired
    QuestionService questionService;
    @Autowired
    ChoiceService choiceService;
    @Autowired
    ReportService reportService;

    @GetMapping({"/quiz/detail"})
    public String profile(HttpServletRequest req, Model model, @RequestParam(name = "uid") Integer uid,
                          @RequestParam(name = "sid") Integer sid){
        List<HistoryUnit> list = new ArrayList<>();
        List<Report> reportList = reportService.getReportBySubId(sid);
        List<Integer> qidList = new ArrayList<>();
        List<Integer> aidList = new ArrayList<>();
        List<Integer> cidList = new ArrayList<>();
        for (Report r:
             reportList) {
            qidList.add(r.getQid());
            aidList.add(r.getAid());
            cidList.add(r.getCid());
        }
        List<Question> questionList = questionService.getQuestionById(qidList);
        List<Choice> answerList = choiceService.getChoiceByCidList(aidList);
        List<Choice> pickList = choiceService.getChoiceByCidList(cidList);
        List<Choice> choiceList = choiceService.getChoiceByQidList(qidList);

        for(int i = 0; i<10; i++){
            HistoryUnit temp = new HistoryUnit(questionList.get(i), choiceList.subList(2*i, 2*i+2),
                    answerList.get(i), pickList.get(i));
            list.add(temp);
        }
        model.addAttribute("histories",list);
        model.addAttribute("uid",uid);
        model.addAttribute("sid",sid);
        return "/quiz/detail";

    }

}
