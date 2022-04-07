package Controller;

import com.neucorecentra.qaqctool.model.Projects;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ProjectController extends Projects {
    private List<Integer> columnIndex = new ArrayList<>(List.of(0,1,2,3,4,5,6,7,18,19,20,21,27));

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){
                    DataFormatter df = new DataFormatter();
                    String abc = df.formatCellValue(cell);
                    return abc;
                }
                return cell.getNumericCellValue();
        }

        return "";
    }

    private boolean checkRegex(String check, String value){
        boolean isMatch = true;
        switch (check){
            case "digit":
                isMatch = value.matches("[0-9]+");
                break;
            case "letters":
                break;
            case "date":
                String strPattern = "^\\d{1,2}/\\d{1,2}/\\d{2,4}$";
                isMatch = value.matches(strPattern);
                break;

        }
        return isMatch;
    }

    public List<String> readContent(String filePath) throws IOException {
        List<Projects> content = new ArrayList<>();
        List<String> errorResults = new ArrayList<>();

        //region - intialization
        FileInputStream inputStream = new FileInputStream(new File(filePath));

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();
        //endregion

        while(iterator.hasNext()){
            Row nextRow = iterator.next();
            int rowNum = nextRow.getRowNum();

            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Projects prj = new Projects();

            if(rowNum !=0) {
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int col = nextCell.getColumnIndex();

                    if(columnIndex.contains(col)) {
                        String cellValue = (String)getCellValue(nextCell);
                        switch (col) {
                            case 0:
                                if(cellValue.isEmpty() || cellValue.isBlank()){
                                    String error = String.format("%d | A | Please check if nodeId blank. <br>", rowNum);
                                    errorResults.add(error);
                                }
                                else {
                                    if (!checkRegex("digit", nextCell.getStringCellValue())) {
                                        String error = String.format("%d | A | Please check if nodeId is all digit.<br>",rowNum);
                                        errorResults.add(error);
                                    }
                                }
                                break;
                            case 1:
                                if(cellValue.isEmpty() || cellValue.isBlank()) {
                                    String error = String.format(" %d | B | Please check if Organization Name is blank.<br>", rowNum);
                                    errorResults.add(error);
                                }
                                else{
                                    if(cellValue.length()>100){
                                        String error = String.format("%d | B | Please check if Organization Name is less than 100 characters.<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setOrgName(nextCell.getStringCellValue());
                                    }
                                }

                                break;
                            case 2:
                                if(cellValue.isEmpty() || cellValue.isBlank()) {
                                    String error = String.format("%d | C | Please check if Theme is blank.<br>", rowNum);
                                    errorResults.add(error);
                                }
                                else{
                                    if(cellValue.length()>32){
                                        String error = String.format(" %d | C | Please check if theme is less than 32 characters.<br>", rowNum);
                                        errorResults.add(error);
                                       // prj.setTheme(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                            case 3:
                                if(cellValue.isEmpty() || cellValue.isBlank()) {
                                    String error = String.format("%d | D | Please check if Program is blank.<br>", rowNum);
                                    errorResults.add(error);
                                }
                                else{
                                    if(cellValue.length()>45){
                                        String error = String.format(" %d | D | Please check if program is less than 45 characters.<br>", rowNum);
                                        errorResults.add(error);
                                      //  prj.setProgram(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                            case 4:
                                //this field, if left blank is not and error, but a warning should be given nonetheless
                                if(cellValue.isEmpty() || cellValue.isBlank()) {
                                    String error = String.format("%d | E | The Project ID field is empty, please note that this will create a new project. If you are updating an existing project the Project ID is required.<br>", rowNum);
                                    errorResults.add(error);
                                }
                                else{
                                    if(cellValue.length()>8){
                                        String error = String.format(" %d | E | Please check if ProjectID is less than 8 characters.<br>", rowNum);
                                        errorResults.add(error);
                                       // prj.setProjectID(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                            case 5:
                                if(cellValue.isEmpty() || cellValue.isBlank()) {
                                    String error = String.format(" %d | F | Please check if project name is blank.<br>", rowNum);
                                    errorResults.add(error);
                                }
                                else{
                                    if(cellValue.length()>128){
                                        String error = String.format(" %d | F | Please check if project name is less than 128 characters.<br>", rowNum);
                                        errorResults.add(error);
                                       // prj.setProjectName(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                            case 6:
                                if(!cellValue.isEmpty() && !cellValue.isBlank()) {
                                    if(!checkRegex("digit",cellValue) || cellValue.length()>4){
                                        String error = String.format("%d | G | Year should be a 4 digit number.<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setReportingYear(Integer.parseInt(nextCell.getStringCellValue()));
                                    }
                                }
                                break;
                            case 7:
                                //this should be a decimal, we need to first remove any additional characters, convert to decimal.
                                if(!cellValue.isEmpty() && !cellValue.isBlank()) {
                                    cellValue = cellValue.replaceAll(",","");
                                    if(!checkRegex("digit",cellValue) || cellValue.length()>16){
                                        String error = String.format(" %d | H | Please check beneficiaries to date.<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setBenefeciariesToDate(Integer.parseInt(nextCell.getStringCellValue()));
                                    }
                                }
                                break;
                            case 18:
                                if(!cellValue.isEmpty() && !cellValue.isBlank()) {
                                    if(!checkRegex("digit",cellValue) && ((Integer.parseInt(cellValue)>=1 && Integer.parseInt(cellValue)<=100))){
                                        String error = String.format(" %d | S | Value should be a number from 1-100<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setProjectPercentComplete(Integer.parseInt(nextCell.getStringCellValue()));
                                    }
                                }
                                break;
                            case 19:
                                if(!cellValue.isEmpty() && !cellValue.isBlank()) {
                                    if(!checkRegex("date",cellValue) || cellValue.length()>10){
                                        String error = String.format(" %d | T | Incorrect date format.<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setProjectEndDate(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                            case 20:
                                if(!cellValue.isEmpty() && !cellValue.isBlank()) {
                                    if(!checkRegex("date",cellValue) || cellValue.length()>10){
                                        String error = String.format("%d | U | Incorrect date format.<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setProjectEndDate(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                            case 21:
                                if(!cellValue.isEmpty() && !cellValue.isBlank()) {
                                    if(!checkRegex("date",cellValue) || cellValue.length()>10){
                                        String error = String.format("%d | V | Incorrect date format.<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setProjectEndDate(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                            case 27:
                                if(!cellValue.isEmpty() && !cellValue.isBlank()) {
                                    cellValue = cellValue.replaceAll(",","");

                                    if(!checkRegex("digit",cellValue) || cellValue.length()>19){
                                        String error = String.format("%d | AB | Check budget approved.<br>", rowNum);
                                        errorResults.add(error);
                                        //prj.setBudgetApproved(nextCell.getStringCellValue());
                                    }
                                }
                                break;
                        }
                    }
                }
                content.add(prj);
            }

        }

        inputStream.close();

        return errorResults;
    }
}
