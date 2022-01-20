package com.example.spring_auth_server.controller;

import com.example.spring_auth_server.domain.AccountDomain;
import com.example.spring_auth_server.entity.*;
import com.example.spring_auth_server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    @Autowired
    QuizTypeService quizTypeService;
    @Autowired
    AccountService accountService;
    @Autowired
    SubmissionService submissionService;
    @Autowired
    ReportService reportService;
    @Autowired
    ChoiceService choiceService;
    @Autowired
    QuestionService questionService;

    public static final int MAX_USER_PER_PAGE = 3;
    public static final int MAX_SUB_PER_PAGE = 4;
    public static final int MAX_QUES_PER_PAGE = 10;

    @GetMapping({"/admin/mainpage"})
    public String admin(HttpServletRequest req, Model model, @RequestParam(name = "adminid") Integer adminid){
        List<QuizType> quizTypeList = quizTypeService.getAllQuizType();

        List<Account> accountList = accountService.getAllAccount();
        model.addAttribute("accounts", accountList);
        model.addAttribute("quiztypes", quizTypeList);
        model.addAttribute("adminid", adminid);
        return "/admin/adminPage";

    }

    @PostMapping({"/admin/mainpage"})
    public String ban(HttpServletRequest req, Model model,
                    @RequestParam(name = "adminid") Integer adminid,
                    @RequestParam(name = "uid") Integer uid,
                    @RequestParam(name = "action") String action){
        if (action.equals("ban")) {
            String group = "ban";
            accountService.updateGroup(uid, group);
            return "redirect:/admin/mainpage?adminid=" + adminid;
        }
        if (action.equals("unban")) {
            String group = "user";
            accountService.updateGroup(uid, group);
            return "redirect:/admin/mainpage?adminid=" + adminid;
        }
        return "redirect:/admin/mainpage?adminid=" + adminid;
    }

    @GetMapping({"/admin/view"})
    public String viewUserQuiz(HttpServletRequest req,Model model,
                               @RequestParam(name = "uid") Integer uid, @RequestParam(name = "adminid") Integer adminid){
        List<Submission> submissionList = submissionService.getSubmissionByID(uid);
        System.out.println(submissionList);
        model.addAttribute("submissions", submissionList);
        model.addAttribute("uid",uid);
        model.addAttribute("adminid", adminid);
        return "/admin/adminView";
    }

    @GetMapping({"/admin/viewdetail"})
    public String viewUserQuizDetail(HttpServletRequest req,Model model,
                                     @RequestParam(name = "uid") Integer uid,
                                     @RequestParam(name = "adminid") Integer adminid,
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
        model.addAttribute("adminid",adminid);
        return "/admin/adminDetail";
    }

    @GetMapping({"/admin/checkquiz"})
    public String check(HttpServletRequest req,Model model,
                                @RequestParam(name = "tid") Integer tid,
                                @RequestParam(name = "adminid") Integer adminid,
                                @RequestParam(name = "index") Integer index){
        List<Question> questionList = questionService.getQuestionByType(tid);
        List<Problem> problemList = new ArrayList<>();
        for(int i=0; i<questionList.size();i++){
            int qid = questionList.get(i).getQid();
            List<Choice> temp = choiceService.getChoiceByQidList(Arrays.asList(qid));
            problemList.add(new Problem(questionList.get(i), temp, choiceService.getRightChoice(qid)));
        }
        int max = (int)Math.ceil((double)problemList.size() / MAX_QUES_PER_PAGE);
        List<Problem> showProblem;
        if(index == max){
            showProblem = problemList.subList(MAX_QUES_PER_PAGE*(index - 1),problemList.size());
        }
        else {
            showProblem = problemList.subList(MAX_QUES_PER_PAGE*(index - 1), MAX_QUES_PER_PAGE*index);
        }
        model.addAttribute("problems", showProblem);
        model.addAttribute("adminid", adminid);
        model.addAttribute("tid", tid);
        model.addAttribute("index",index);
        model.addAttribute("max",max);
        return "/admin/checkQuiz";
    }

    @PostMapping("/admin/checkquiz")
    public String handleCheck(HttpServletRequest req, Model model,
                              @RequestParam(name = "tid") Integer tid,
                              @RequestParam(name = "adminid") Integer adminid,
                              @RequestParam(name = "index") Integer index,
                              @RequestParam(name = "qid", required = false) Integer qid,
                              @RequestParam(value="action", required=false) String action){
        if(action.equals("next")){
            return "redirect:/admin/checkquiz?adminid="+adminid+"&tid="+tid+"&index="+(index+1);
        }

        if(action.equals("previous")){
            return "redirect:/admin/checkquiz?adminid="+adminid+"&tid="+tid+"&index="+(index-1);
        }
        if (action.equals("disable")) {
            String status = "disabled";
            questionService.updateStatus(qid, status);
            return "redirect:/admin/checkquiz?adminid=" + adminid +"&tid="+tid+"&index=" + index;
        }
        if (action.equals("active")) {
            String status = "active";
            questionService.updateStatus(qid, status);
            return "redirect:/admin/checkquiz?adminid=" + adminid+"&tid=" +tid+ "&index=" + index;
        }
        else
            return "errorPage";
    }

    @GetMapping({"/admin/user"})
    public String checkAllUser(HttpServletRequest req, Model model,
                               @RequestParam(name = "adminid") Integer adminid,
                               @RequestParam(name = "index") int index,
                               @RequestParam(name = "sort") String sortMode){
        List<Account> accountList;
        if(sortMode.equals("id")){
            accountList = accountService.getAllAccount().stream().
                    sorted(Comparator.comparingInt(Account::getUserID)).collect(Collectors.toList());
        }
        else if(sortMode.equals("username")){
            accountList = accountService.getAllAccount().stream().
                    sorted(Comparator.comparing(Account::getUsername)).collect(Collectors.toList());
        }
        else{
            accountList = accountService.getAllAccount();
        }
        int max = (int)Math.ceil((double)accountList.size() / MAX_USER_PER_PAGE);
        List<Account> showAccount;
        if(index == max){
            showAccount = accountList.subList(MAX_USER_PER_PAGE*(index - 1),accountList.size());
        }
        else {
            showAccount = accountList.subList(MAX_USER_PER_PAGE*(index - 1), MAX_USER_PER_PAGE*index);
        }
        model.addAttribute("accounts", showAccount);
        model.addAttribute("adminid", adminid);
        model.addAttribute("index", index);
        model.addAttribute("max", max);
        model.addAttribute("sort", sortMode);
        System.out.println(max);
        return "/admin/adminUser";
    }

    @PostMapping("/admin/user")
    public String handleAllUser(HttpServletRequest req, Model model,
                                @RequestParam(name = "adminid") Integer adminid,
                                @RequestParam(name = "index") int index,
                                @RequestParam(name = "sort") String sortMode,
                                @RequestParam(name = "uid", required = false) Integer uid,
                                @RequestParam(value="action", required=false)String action){
        if(action.equals("next")){
            return "redirect:/admin/user?adminid="+adminid+"&index="+(index+1)+"&sort="+sortMode;
        }

        if(action.equals("previous")){
            return "redirect:/admin/user?adminid="+adminid+"&index="+(index-1)+"&sort="+sortMode;
        }
        if (action.equals("ban")) {
            String group = "ban";
            accountService.updateGroup(uid, group);
            return "redirect:/admin/user?adminid=" + adminid + "&index=" + index + "&sort=" + sortMode;
        }
        if (action.equals("unban")) {
            String group = "user";
            accountService.updateGroup(uid, group);
            return "redirect:/admin/user?adminid=" + adminid + "&index=" + index + "&sort=" + sortMode;
        }
        else return "errorPage";
    }

    @GetMapping({"/admin/quiz"})
    public String checkAllQuiz(HttpServletRequest req, Model model,
                               @RequestParam(name = "adminid") Integer adminid){
        List<QuizType> quizTypeList = quizTypeService.getAllQuizType();
        model.addAttribute("quiztypes", quizTypeList);
        model.addAttribute("adminid", adminid);
        return "/admin/adminQuiz";
    }

    @GetMapping({"/admin/result"})
    public String checkAllResult(HttpServletRequest req, Model model,
                               @RequestParam(name = "adminid") Integer adminid,
                               @RequestParam(name = "index") int index,
                               @RequestParam(name = "sort") String sortMode,
                                 @RequestParam(name = "filter",required = false) Integer filter){
        List<Submission> submissionList;
        if(filter == null || filter == 0){
            submissionList = submissionService.getAllSubmission();
        }
        else{
            submissionList = submissionService.getSubmissionByType(filter);
        }
        if(sortMode.equals("sid")){
            submissionList = submissionList.stream().
                    sorted(Comparator.comparingInt(Submission::getSID)).collect(Collectors.toList());
        }
        else if(sortMode.equals("uid")){
            submissionList = submissionList.stream().
                    sorted(Comparator.comparingInt(Submission::getUserID)).collect(Collectors.toList());
        }
        else if(sortMode.equals("tid")){
            submissionList = submissionList.stream().
                    sorted(Comparator.comparingInt(Submission::getTypeID)).collect(Collectors.toList());
        }
        else{
            submissionList = submissionService.getAllSubmission();
        }
        int max = (int)Math.ceil((double)submissionList.size() / MAX_SUB_PER_PAGE);
        List<Submission> showSubmission;
        if(index == max){
            showSubmission = submissionList.subList(MAX_SUB_PER_PAGE*(index - 1),submissionList.size());
        }
        else {
            showSubmission = submissionList.subList(MAX_SUB_PER_PAGE*(index - 1), MAX_SUB_PER_PAGE*index);
        }
        model.addAttribute("submissions", showSubmission);
        model.addAttribute("adminid", adminid);
        model.addAttribute("index", index);
        model.addAttribute("max", max);
        model.addAttribute("sort", sortMode);
        model.addAttribute("filter", filter);
        System.out.println(max);
        return "/admin/adminResult";
    }

    @PostMapping("/admin/result")
    public String handleAllResult(HttpServletRequest req, Model model,
                                @RequestParam(name = "adminid") Integer adminid,
                                @RequestParam(name = "index") int index,
                                @RequestParam(name = "sort") String sortMode,
                                @RequestParam(value="action", required=false)String action,
                                  @RequestParam(name = "filter",required = false)Integer filter){
        if(filter == null){
            filter = 0;
        }
        if(action.equals("next")){
            return "redirect:/admin/result?adminid="+adminid+"&index="+(index+1)+"&sort="+sortMode+"&filter="+filter;
        }

        if(action.equals("previous")){
            return "redirect:/admin/result?adminid="+adminid+"&index="+(index-1)+"&sort="+sortMode+"&filter="+filter;
        }
        else return "errorPage";
    }

    @GetMapping("/admin/modifyproblem")
    public String modifyProblem(HttpServletRequest req, Model model,
                             @RequestParam(name = "adminid") Integer adminid,
                             @RequestParam(name = "tid") int tid,
                             @RequestParam(name = "qid") int qid,
                             @RequestParam(name = "cid0") int cid0,
                             @RequestParam(name = "cid1") int cid1
                             ){
        model.addAttribute("question",new Question());
        model.addAttribute("adminid",adminid);
        model.addAttribute("tid",tid);
        model.addAttribute("qid",qid);
        model.addAttribute("cid0",cid0);
        model.addAttribute("cid1",cid1);
        return "/admin/updateQuestion";
    }

    @PostMapping("/admin/updateQuestion")
    public String modifyQuestion(HttpServletRequest req, Model model,
                               @RequestParam(name = "adminid") Integer adminid,
                               @RequestParam(name = "tid") int tid,
                               @RequestParam(name = "qid") int qid,
                               @RequestParam(name = "cid0") int cid0,
                               @RequestParam(name = "cid1") int cid1,
                               @ModelAttribute("question") @Valid Question question){
        question.setQid(qid);
        question.setTid(tid);
        question.setStatus("active");
        System.out.println(question);
        questionService.updateQuestion(question);
        model.addAttribute("choice",new Choice());
        model.addAttribute("cno", 0);
        model.addAttribute("adminid",adminid);
        model.addAttribute("tid",tid);
        model.addAttribute("qid",qid);
        model.addAttribute("cid0",cid0);
        model.addAttribute("cid1",cid1);
        return "/admin/updateChoice";
    }

    @PostMapping("/admin/updateChoice")
    public String modifyChoice(HttpServletRequest req, Model model,
                                 @RequestParam(name = "adminid") Integer adminid,
                                 @RequestParam(name = "tid") Integer tid,
                                 @RequestParam(name = "qid") Integer qid,
                                 @RequestParam(name = "cid0") Integer cid0,
                                 @RequestParam(name = "cid1") Integer cid1,
                                 @RequestParam(name = "cno") Integer cno,
                                 @ModelAttribute("choice") @Valid Choice choice){
        System.out.println(cno);
        choice.setQid(qid);
        if(cno == 0){
            choice.setCid(cid0);
        }
        else{
            choice.setCid(cid1);
        }
        System.out.println(choice);
        choiceService.updateChoice(choice);
        if(cno==0){
            model.addAttribute("choice",new Choice());
            model.addAttribute("cno", cno+1);
            model.addAttribute("adminid",adminid);
            model.addAttribute("tid",tid);
            model.addAttribute("qid",qid);
            model.addAttribute("cid0",cid0);
            model.addAttribute("cid1",cid1);
            return "/admin/updateChoice";
        }
        else if (cno==1){
            return "redirect:/admin/quiz?adminid="+adminid+"&sort=id";
        }
        else
            return "errorPage";
    }

    @GetMapping("/admin/newproblem")
    public String createProblem(HttpServletRequest req, Model model,
                                @RequestParam(name = "adminid") Integer adminid,
                                @RequestParam(name = "tid") Integer tid){
        model.addAttribute("question", new Question());
        model.addAttribute("adminid", adminid);
        model.addAttribute("tid", tid);
        return "/admin/createQuestion";
    }

    @PostMapping("/admin/createQuestion")
    public String createQues(HttpServletRequest req, Model model,
                             @RequestParam(name = "adminid") Integer adminid,
                             @RequestParam(name = "tid") Integer tid,
                             @ModelAttribute("question") @Valid Question question){
        question.setTid(tid);
        question.setStatus("active");
        System.out.println(question);
        int qid = questionService.createQuestion(question);
        model.addAttribute("question", new Question());
        model.addAttribute("adminid", adminid);
        model.addAttribute("tid", tid);
        model.addAttribute("qid", qid);
        model.addAttribute("choice", new Choice());
        model.addAttribute("cno",0);
        return "/admin/createChoice";
    }

    @PostMapping("/admin/createChoice")
    public String createChoices(HttpServletRequest req, Model model,
                               @RequestParam(name = "adminid") Integer adminid,
                               @RequestParam(name = "tid") Integer tid,
                               @RequestParam(name = "qid") Integer qid,
                               @RequestParam(name = "cno") Integer cno,
                               @ModelAttribute("choice") @Valid Choice choice){
        System.out.println(cno);
        choice.setQid(qid);
        System.out.println(choice);
        choiceService.createChoice(choice);
        if(cno==0){
            model.addAttribute("choice",new Choice());
            model.addAttribute("cno", 1);
            model.addAttribute("adminid",adminid);
            model.addAttribute("tid",tid);
            model.addAttribute("qid",qid);
            return "/admin/createChoice";
        }
        else if (cno==1){
            return "redirect:/admin/checkquiz?adminid="+adminid+"&tid="+tid+"&index=1";
        }
        else
            return "errorPage";
    }
}
