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

    private List<Integer> columnIndex = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,19,20,21,22));

    public List<Projects> readContent(String filePath) throws IOException {
        List<Projects> content = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(new File(filePath));

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();

        while(iterator.hasNext()){
            //first row is column headers
            Row nextRow = iterator.next();
            int num = nextRow.getRowNum();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Projects prj = new Projects();

            if(num !=0) {
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int col = nextCell.getColumnIndex();

                    switch (col) {
                        case 0:
                            prj.setNodeId(Integer.parseInt(nextCell.getStringCellValue()));
                            break;
                        case 1:
                            prj.setOrgName(nextCell.getStringCellValue());
                            break;
                        case 2:
                            prj.setTheme(nextCell.getStringCellValue());
                            break;
                        case 3:
                            prj.setProgram(nextCell.getStringCellValue());
                            break;
                        case 4:
                            prj.setProjectID(nextCell.getStringCellValue());
                            break;
                        case 5:
                            prj.setProjectName(nextCell.getStringCellValue());
                            break;
                        case 6:
                            prj.setReportingYear(Integer.parseInt(nextCell.getStringCellValue()));
                            break;
                        case 7:
                            prj.setBenefeciariesToDate(Integer.parseInt(nextCell.getStringCellValue()));
                            break;
                        case 18:
                            prj.setProjectPercentComplete(Integer.parseInt(nextCell.getStringCellValue()));
                            break;
                        case 19:
                        case 20:
                        case 21:
                            prj.setProjectEndDate(nextCell.getStringCellValue());
                            break;
                        case 27:
                            prj.setBudgetApproved(nextCell.getStringCellValue());
                            break;
                    }
                }
                content.add(prj);
            }

        }

        inputStream.close();

        return content;
    }

    //region GETTERS - SETTERS
    private void setBenefeciariesToDate(double value) {
        this.beneficiariesToDate = value;
    }

    private void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    private void setReportingYear(int reportingYear) {
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
