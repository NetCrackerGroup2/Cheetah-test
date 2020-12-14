package com.ncedu.cheetahtest.service.mail;

import com.ncedu.cheetahtest.entity.mail.SpecificReport;

import java.util.List;

public interface EmailService {
    void sendSimpleMessage(String to, String text, String subject);

    void sendMessageWithAttachment(String to, String text, String subject, String htmlPath);

    void sendTestCaseReportToAddresses(List<String> emails,int idTestCase,int idProject);

    void sendGenerateTestCaseReportToAddresses(List<String> emails, int idTestCase, int idProject, String pathHTML);

    void sendSpecificReport(SpecificReport specificReport, String pathHTML);
}
