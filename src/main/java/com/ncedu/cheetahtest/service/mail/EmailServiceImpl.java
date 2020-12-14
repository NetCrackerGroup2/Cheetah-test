package com.ncedu.cheetahtest.service.mail;

import com.ncedu.cheetahtest.dao.action.ActionDao;
import com.ncedu.cheetahtest.dao.actscenario.ActScenarioDao;
import com.ncedu.cheetahtest.dao.project.ProjectDao;
import com.ncedu.cheetahtest.dao.testcase.TestCaseDao;
import com.ncedu.cheetahtest.dao.user.UserDao;
import com.ncedu.cheetahtest.entity.action.Action;
import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.mail.SpecificReport;
import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.testcase.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j

public class EmailServiceImpl implements EmailService{

    public static final String NET_CRACKER_USERNAME = "spring.mail.username";

    private final JavaMailSender emailSender;
    private final HtmlMail htmlMail;
    private final Environment environment;
    @Value("${frontend.ulr}")
    private String FRONT_URL;
    private UserDao userDao;
    private TestCaseDao testCaseDao;
    private ProjectDao projectDao;
    private ActScenarioDao actScenarioDao;
    private ActionDao actionDao;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, HtmlMail htmlMail, Environment environment, UserDao userDao,
                            TestCaseDao testCaseDao, ProjectDao projectDao, ActScenarioDao actScenarioDao, ActionDao actionDao) {
        this.emailSender = emailSender;
        this.htmlMail = htmlMail;
        this.environment = environment;
        this.userDao = userDao;
        this.testCaseDao = testCaseDao;
        this.projectDao = projectDao;
        this.actScenarioDao = actScenarioDao;
        this.actionDao = actionDao;
    }

    @Override
    public void sendSimpleMessage(String to, String text, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Objects.requireNonNull(environment.getProperty(NET_CRACKER_USERNAME)));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String text, String subject, String htmlPath) {

        MimeMessage message = emailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(Objects.requireNonNull(environment.getProperty(NET_CRACKER_USERNAME)));
            helper.setTo(to);
            helper.setSubject(subject);

            String htmlString = htmlMail.getHtmlWithStringInside(text, htmlPath)
                    .orElseThrow(FileNotFoundException::new);

            helper.setText("", htmlString);
            emailSender.send(message);

            log.info("Email has been sent to " + to);

        } catch (MessagingException e) {
            log.error(String.format("Couldn't send message: %s", e.getMessage()));
        } catch (FileNotFoundException e) {
            log.error(String.format("Couldn't find html file by given path: %s", e.getMessage()));
        }
    }

    @Override
    public void sendTestCaseReportToAddresses(List<String> emails, int idTestCase, int idProject) {
        String url = FRONT_URL+"/projects/"+idProject+"/test-cases/"+idTestCase;
        String message = "Hi, testcase "+idTestCase+" was completed. \nTo see details, please follow the link:\n"+url;
        log.info(message);
        for ( String email: emails){
            sendSimpleMessage(email,message,"TestCaseInfo");
        }
    }

    @Override
    public void sendGenerateTestCaseReportToAddresses(List<String> emails, int idTestCase, int idProject, String pathHTML) {

        for (String email: emails) {


            MimeMessage message = emailSender.createMimeMessage();


            try {

                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom(Objects.requireNonNull(environment.getProperty(NET_CRACKER_USERNAME)));

                helper.setTo(email);

                List<String> strs = new ArrayList<String>();
                strs.add(userDao.findUserByEmail(email).getName());
                strs.add(testCaseDao.findTestCaseByProjectIdAndTestCaseId(idProject, idProject).getTitle());
                strs.add(projectDao.findByProjectId(idProject).getTitle());

                message.setSubject("One-time test case -> [" + strs.get(1) + "] " +
                        " Project -> [" + strs.get(2) + "]");

                List<ActScenario> actScenarioList = actScenarioDao.getAllByTestCaseId(idTestCase);

                Workbook workbook = new XSSFWorkbook();

                Sheet sheet = workbook.createSheet(strs.get(1));

                sheet.setColumnWidth(2, 5000);

                Row header = sheet.createRow(0);

                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                XSSFFont font = ((XSSFWorkbook) workbook).createFont();
                font.setFontName("Arial");
                font.setFontHeightInPoints((short) 16);
                font.setBold(true);
                headerStyle.setFont(font);

                Cell headerCell = header.createCell(0);
                headerCell.setCellValue("Title");
                headerCell.setCellStyle(headerStyle);

                headerCell = header.createCell(1);
                headerCell.setCellValue("Type");
                headerCell.setCellStyle(headerStyle);

                headerCell = header.createCell(2);
                headerCell.setCellValue("Description");
                headerCell.setCellStyle(headerStyle);

                headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                font.setBold(false);
                headerStyle.setFont(font);

                List<Action> actionList = new ArrayList<>();
                String actions = new String();
                for (ActScenario actScenario : actScenarioList) {
                    actionList.add(actionDao.getActionById(actScenario.getActionId()));
                    header = sheet.createRow(actionList.size());
                    headerCell = header.createCell(0);
                    headerCell.setCellValue(actionList.get(actionList.size() - 1).getTitle());
                    headerCell.setCellStyle(headerStyle);

                    headerCell = header.createCell(1);
                    headerCell.setCellValue(actionList.get(actionList.size() - 1).getType());
                    headerCell.setCellStyle(headerStyle);

                    headerCell = header.createCell(2);
                    headerCell.setCellValue(actionList.get(actionList.size() - 1).getDescription());
                    headerCell.setCellStyle(headerStyle);
                    actions += "> " + actionList.get(actionList.size() - 1).getTitle()
                            + "&emsp;&emsp;&emsp;&emsp;&emsp;" + actionList.get(actionList.size() - 1).getType()
                            + "&emsp;&emsp;&emsp;&emsp;&emsp;" + actionList.get(actionList.size() - 1).getDescription() + "<br>\n";
                }
                strs.add(actions);


                String htmlString = htmlMail.getHtmlWithStrings(strs, pathHTML).orElseThrow(FileNotFoundException::new);


                helper.setText("", htmlString);

                File excelFile = new File("src/main/resources/excel/mail.xls");

                String path = excelFile.getAbsolutePath();

                FileOutputStream outputStream = new FileOutputStream("src/main/resources/excel/mail.xls");

                workbook.write(outputStream);
                workbook.close();

                helper.addAttachment("one-test-case.xlsx", excelFile);


                helper.addInline("topImageId", new File("src/main/resources/image/mail.png"));

                emailSender.send(message);

                log.info("Email has been sent to " + emails);

            } catch (MessagingException e) {
                log.error(String.format("Couldn't send message: %s", e.getMessage()));
            } catch (FileNotFoundException e) {
                log.error(String.format("Couldn't find html file by given path: %s", e.getMessage()));
            } catch (IOException e) {
                log.error(String.format("Couldn't write excel file: %s", e.getMessage()));
            }
        }

    }

    @Override
    public void sendSpecificReport(SpecificReport specificReport, String pathHTML) {

        for (String email : specificReport.getEmails()) {

            MimeMessage message = emailSender.createMimeMessage();
            try {

                List<String> strs = new ArrayList<String>();

                strs.add(userDao.findUserByEmail(email).getName());

                List<Project> projectList = userDao.getProjectsByUserId(userDao.findUserByEmail(email).getId());
                List<TestCase> testCaseList = new ArrayList<>();

                Workbook workbook = new XSSFWorkbook();

                Sheet sheet = workbook.createSheet("Specific");


                sheet.setColumnWidth(0, 7000);
                sheet.setColumnWidth(1, 7000);


                String str = "";
                int i = 0;
                int iRow = 0;
                Row header = sheet.createRow(iRow++);
                CellStyle headerStyleDarkGray = workbook.createCellStyle();
                CellStyle headerStyleGray = workbook.createCellStyle();
                CellStyle headerStyleTan = workbook.createCellStyle();
                Cell headerCell = header.createCell(0);
                headerCell.setCellValue("Cheetah-test");

                XSSFFont font = ((XSSFWorkbook) workbook).createFont();
                font.setFontName("Arial");
                font.setFontHeightInPoints((short) 16);
                font.setBold(true);

                headerStyleDarkGray.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
                headerStyleDarkGray.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyleDarkGray.setFont(font);
                headerStyleGray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                headerStyleGray.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyleGray.setFont(font);
                headerStyleTan.setFillForegroundColor(IndexedColors.TAN.getIndex());
                headerStyleTan.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyleTan.setFont(font);
                headerCell.setCellStyle(headerStyleTan);
                if (specificReport.getSendProjectUse()){

                    for (Project project: projectList){
                        str += "<h2>Project" + project.getTitle() + "</h2>\n";
                        header = sheet.createRow(iRow++);


                        headerCell = header.createCell(0);
                        headerCell.setCellValue(project.getTitle());

                        headerCell.setCellStyle(headerStyleDarkGray);
                        int size = testCaseDao.getAmountActiveElementsByProjectId(project.getId());
                        testCaseList = testCaseDao.
                                getActiveTestCasesPaginatedByProjectId(1, size, project.getId());
                        for (TestCase testCase: testCaseList){

                            header = sheet.createRow(iRow++);
                            headerCell = header.createCell(1);
                            headerCell.setCellValue(testCase.getTitle());

                            headerCell.setCellStyle(headerStyleGray);
                            str += "<p>" + testCase.getTitle() + "</p>\n";
                            i++;
                        }
                    }
                }
                strs.add(str);
                str = "";
                if(specificReport.getSendTestCaseAllDone()) {
                    str += "<h2>All  test cases</h2>\n";
                    header = sheet.createRow(iRow++);
                    headerCell = header.createCell(0);
                    headerCell.setCellValue("All  test cases");
                    headerCell.setCellStyle(headerStyleDarkGray);
                    for (TestCase testCase: testCaseList) {
                        str += "<p>" + testCase.getTitle() + "</p>\n";
                        header = sheet.createRow(iRow++);
                        headerCell = header.createCell(1);
                        headerCell.setCellValue(testCase.getTitle());
                        headerCell.setCellStyle(headerStyleGray);
                        i++;
                    }
                }
                else {
                    if (!specificReport.getSendSelectTestCaseId().isEmpty()) {
                        str += "<h2>Selected completed test cases</h2>\n";
                        header = sheet.createRow(iRow++);
                        headerCell = header.createCell(0);
                        headerCell.setCellValue("Selected completed test cases");
                        headerCell.setCellStyle(headerStyleDarkGray);
                        for(Project project: projectList) {
                            for (TestCase testCase : testCaseList) {
                                if(testCase.getProjectId() == project.getId()) {
                                    str += "<p>" + testCase.getTitle() + "</p>\n";
                                    str += "<p>" + testCase.getTitle() + "</p>\n";
                                    header = sheet.createRow(iRow++);
                                    headerCell = header.createCell(1);
                                    headerCell.setCellValue(testCase.getTitle());
                                    headerCell.setCellStyle(headerStyleGray);
                                    i++;
                                }
                            }
                        }
                    }
                }
                strs.add(str);
                Collections.reverse(strs);
                strs.add("height: " + (new Integer(i * 100).toString()) + "px;");
                Collections.reverse(strs);
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom(Objects.requireNonNull(environment.getProperty(NET_CRACKER_USERNAME)));
                helper.setTo(email);



                String htmlString = htmlMail.getHtmlWithStrings(strs, pathHTML).orElseThrow(FileNotFoundException::new);



                helper.setText("", htmlString);
                helper.addInline("topImageId", new File("src/main/resources/image/mail.png"));

                FileOutputStream outputStream = new FileOutputStream("src/main/resources/excel/mail.xls");

                workbook.write(outputStream);
                workbook.close();

                File excelFile = new File("src/main/resources/excel/mail.xls");

                helper.addAttachment("special.xlsx", excelFile);

                emailSender.send(message);

                log.info("Email has been sent to " + email);

            } catch (MessagingException e) {
                log.error(String.format("Couldn't send message: %s", e.getMessage()));
            } catch (FileNotFoundException e) {
                log.error(String.format("Couldn't find html file by given path: %s", e.getMessage()));
            } catch (IOException e) {
                log.error(String.format("Couldn't write excel file: %s", e.getMessage()));
            }
        }
    }
}
