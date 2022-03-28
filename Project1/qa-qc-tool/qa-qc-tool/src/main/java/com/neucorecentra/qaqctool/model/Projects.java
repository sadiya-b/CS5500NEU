package com.neucorecentra.qaqctool.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Projects {
    private int nodeId;
    private String orgName;
    private String theme;
    private String program;
    private String projectID;
    private String projectName;
    private int reportingYear;
    private double beneficiariesToDate;
    private int projectPercentComplete;
    private String projectStartDate;
    private String projectEndDate;
    private String originalEndDate;
    //column 28 if 1 indexed.. this should be double, but we need to remove extra characters in the check
    private String budgetApproved;

    //region GETTERS - SETTERS
    public void setBenefeciariesToDate(double value) {
        this.beneficiariesToDate = value;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public void setReportingYear(int reportingYear) {
        this.reportingYear = reportingYear;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getProgram() {
        return program;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getReportingYear() {
        return reportingYear;
    }

    public double getBeneficiariesToDate() {
        return beneficiariesToDate;
    }

    public int getProjectPercentComplete() {
        return projectPercentComplete;
    }

    public void setProjectPercentComplete(int projectPercentComplete) {
        this.projectPercentComplete = projectPercentComplete;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getOriginalEndDate() {
        return originalEndDate;
    }

    public void setOriginalEndDate(String originalEndDate) {
        this.originalEndDate = originalEndDate;
    }


    public void setBudgetApproved(String budgetApproved) {
        this.budgetApproved = budgetApproved;
    }

    public void setProgram(String program) {
        this.program = program;
    }
    //endregion
}
