package com.example.spring_auth_server.service;

import com.example.spring_auth_server.dao.impl.ReportDAO;
import com.example.spring_auth_server.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportDAO reportDAO;

    public List<Report> getReportBySubId(int sid){
        try{
            return reportDAO.getReportBySubId_H(sid);
        }catch (Exception e){
            System.out.println("e");
        }
        return null;
    }

    public void createReport(Report report){
        reportDAO.createReport_H(report);
    }
}
