package com.example.spring_auth_server.controller;

import com.example.spring_auth_server.entity.*;
import com.example.spring_auth_server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"questions","index"})
public class QuizController {
    @Autowired
    QuizTypeService quizTypeService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    QuestionService questionService;
    @Autowired
    ChoiceService choiceService;
    @Autowired
    SubmissionService submissionService;
    @Autowired
    ReportService reportService;


    @GetMapping({"/quiz/quiz"})
    public String prepareQuiz(HttpServletRequest req, Model model, @RequestParam(name = "uid") Integer uid,
                              @RequestParam(name = "tid") Integer tid){
        List<Question> questionList = questionService.getRandomQuestion(tid);
        Questionnaire qList = new Questionnaire(Arrays.asList(new UserAnswers[10]));
        for(int i = 0; i< 10; i++){
            UserAnswers temp = new UserAnswers(questionList.get(i),
                    choiceService.getChoiceByQidList(Arrays.asList(questionList.get(i).getQid())),
                    0);
            qList.userAnswers.set(i, temp);
        }
        model.addAttribute("index",0);
        model.addAttribute("questions", qList);
        model.addAttribute("tid", tid);
        model.addAttribute("uid", uid);
        return "/quiz/question";
    }

    @PostMapping({"/quiz/quiz"})
    public String processQuiz(HttpServletRequest req, Model model,
                              @RequestParam(value="input", required=false) Integer input,
                              @RequestParam("action") String action,
                              @RequestParam("index") Integer index,
                              @RequestParam(name = "uid") Integer uid,
                              @RequestParam(name = "tid") Integer tid){
        Questionnaire cur = (Questionnaire) model.getAttribute("questions");
        if(action.equals("next")){
            if(input == null){
                cur.userAnswers.get(index).setAnswer(0);
            }else {
                cur.userAnswers.get(index).setAnswer(input);
            }
            model.addAttribute("questions",cur);
            model.addAttribute("index",index+1);
            model.addAttribute("tid", tid);
            model.addAttribute("uid",uid);
            return "/quiz/question";
        }

        if(action.equals("previous")){
            if(input == null){
                cur.userAnswers.get(index).setAnswer(0);
            }else {
                cur.userAnswers.get(index).setAnswer(input);
            }
            model.addAttribute("questions",cur);
            model.addAttribute("index",index-1);
            model.addAttribute("tid", tid);
            model.addAttribute("uid",uid);
            return "/quiz/question";
        }

        if(input == null){
            cur.userAnswers.get(index).setAnswer(0);
        }else {
            cur.userAnswers.get(index).setAnswer(input);
        }
        int sid = submissionService.getNewId();
        Submission submission = new Submission();
        submission.setSID(sid);
        submission.setUserID(uid);
        submission.setTypeID(tid);
        int count=checkAnswer(cur);
        if(count>=6){
            submission.setPass(true);
        }
        else {
            submission.setPass(false);
        }
        submissionService.createSubmission(submission);
        List<Integer> rc = getRightChoice(cur);
        for(int i = 0;i<10;i++){
            Report report = new Report();
            report.setSid(sid);
            report.setQid(cur.userAnswers.get(i).getQuestion().getQid());
            report.setAid(rc.get(i));
            report.setCid(cur.userAnswers.get(i).getAnswer());
            reportService.createReport(report);
        }

        model.addAttribute("questions",cur);
        model.addAttribute("tid", tid);
        model.addAttribute("uid",uid);
        return "redirect:/quiz/profile?uid="+uid;
    }

    @GetMapping({"/quiz/feedback"})
    public String feedbackPageSetup(HttpServletRequest req, Model model, @RequestParam(name = "uid") Integer uid,
                          @RequestParam(name = "tid") Integer tid){
        model.addAttribute("uid", uid);
        model.addAttribute("tid", tid);
        model.addAttribute("feedback",new Feedback());
        return "/quiz/feedback";
    }

    @PostMapping({"/quiz/feedback"})
    public String feedbackGenerator(HttpServletRequest req,Model model,@ModelAttribute("feedback") @Valid Feedback feedback,
                                    BindingResult result,
                                    @RequestParam(name = "uid") Integer uid,
                                    @RequestParam(name = "tid") Integer tid){
        feedback.setTid(tid);
        feedbackService.createFeedback(feedback);
        System.out.println(feedback.description);
        return "redirect:/quiz/profile?uid="+uid;
    }

    public List<Integer> getRightChoice(Questionnaire q){
        List<Integer> ra = new ArrayList<>();
        for(int i = 0;i<10;i++){
            for(int j=0; j<q.userAnswers.get(i).getChoices().size();j++){
                if(q.userAnswers.get(i).getChoices().get(j).isAnswer()){
                    ra.add(q.userAnswers.get(i).getChoices().get(j).getCid());
                }
            }
        }
        return ra;
    }

    public int checkAnswer(Questionnaire q){
        int count = 0;
        List<Integer> ra = getRightChoice(q);
        for(int i=0; i<10;i++){
            if(q.userAnswers.get(i).getAnswer() == ra.get(i))
                count++;
        }
        return count;
    }
}
