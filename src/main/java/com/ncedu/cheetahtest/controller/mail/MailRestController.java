package com.ncedu.cheetahtest.controller.mail;

import com.ncedu.cheetahtest.entity.mail.Email;
import com.ncedu.cheetahtest.entity.mail.GenericResponse;
import com.ncedu.cheetahtest.entity.mail.PasswordDTO;
import com.ncedu.cheetahtest.entity.mail.SpecificReport;
import com.ncedu.cheetahtest.entity.user.ResetToken;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.service.mail.EmailService;
import com.ncedu.cheetahtest.service.security.AuthService;
import com.ncedu.cheetahtest.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "${frontend.ulr}")
public class MailRestController {
    public static final String SUBJECT = "Password reset";
    private static final String HTML_PATH = "src/main/resources/mail/email.html";
    private static final String HTML_GENERATE_ONE_TEST_CASE = "src/main/resources/mail/generate-one-test-case.html";
    private static final String HTML_SPECIFIC_REPORT = "src/main/resources/mail/specific-report.html";

    @Value("${frontend.ulr}/reset-password?token=")
    private String FRONT_URL;

    private final EmailService emailService;
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public MailRestController(EmailService emailService, UserService userService, AuthService authService) {
        this.emailService = emailService;
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<GenericResponse> resetPassword(@RequestBody Email email) {
        User user = userService.findUserByEmail(email.getEmail());
        if (user == null) {
            return new ResponseEntity<>(new GenericResponse("invalid.email"),HttpStatus.BAD_REQUEST);
        }

        String token = UUID.randomUUID().toString();

        userService.createPasswordResetTokenForUser(user, token);
        emailService.sendMessageWithAttachment(email.getEmail(), constructUrl(token), SUBJECT, HTML_PATH);

        return new ResponseEntity<>(new GenericResponse("user.fetched"), HttpStatus.OK);
    }
    @PostMapping("/projects/{idProject}/test-cases/{idTestCase}/send-report")
    public ResponseEntity<GenericResponse> sendMessage(@PathVariable int idTestCase,
                                                        @PathVariable int idProject,
                                                       @RequestBody List<String> emails){
            emailService.sendTestCaseReportToAddresses(emails,idTestCase,idProject);
            return new ResponseEntity<>(new GenericResponse("Success"), HttpStatus.OK);
    }

    @PostMapping("/projects/{idProject}/test-cases/{idTestCase}/send-generate-report")
    public ResponseEntity<GenericResponse> sendGenerateTestCaseMassage(@PathVariable int idTestCase,
                                                                       @PathVariable int idProject,
                                                                       @RequestBody List<String> emails){
        emailService
                .sendGenerateTestCaseReportToAddresses(emails, idTestCase,
                        idProject,HTML_GENERATE_ONE_TEST_CASE);
        return new ResponseEntity<>(new GenericResponse("Success"), HttpStatus.OK);
    }

    @PostMapping("/specific-report")
    public ResponseEntity<GenericResponse> sendSpecificReport(@RequestBody SpecificReport specificReport){

        emailService.sendSpecificReport(specificReport, HTML_SPECIFIC_REPORT);
        return new ResponseEntity<>(new GenericResponse("Success"), HttpStatus.OK);
    }

    @PostMapping("/save-password")
    public ResponseEntity<GenericResponse> changePassword(@RequestBody PasswordDTO passwordDTO) {

        String token = passwordDTO.getToken();

        String result = validatePasswordResetToken(token);
        if (result != null) {
            log.info("Result of validating of reset token: " + result);
            return new ResponseEntity<>(new GenericResponse(result), HttpStatus.BAD_REQUEST);
        }

        boolean isPasswordSame = authService.validatePassword(passwordDTO);
        if (isPasswordSame) {
            log.info("Same password as before");
            return new ResponseEntity<>(new GenericResponse("same.password"), HttpStatus.BAD_REQUEST);
        }

        ResetToken resetToken = userService.findByToken(token);

        if (resetToken != null) {
            authService.changeUserPassword(resetToken, passwordDTO.getPassword());
            userService.makeTokenExpired(resetToken);
            log.info("Password has been successfully reset");
            return new ResponseEntity<>(new GenericResponse("message.resetPasswordSuc"), HttpStatus.OK);
        } else {
            log.info("Reset token doesn't exist");
            return new ResponseEntity<>(new GenericResponse("reset.token.null"), HttpStatus.BAD_REQUEST);
        }
    }

    private String constructUrl(String token) {
        return FRONT_URL + token;
    }

    public String validatePasswordResetToken(String token) {
        final ResetToken passToken = userService.findByToken(token);

        return !isTokenFound(passToken) ? "token.invalid"
                : isTokenExpired(passToken) ? "token.expired"
                : null;

    }

    private boolean isTokenFound(ResetToken token) {
        return token != null;
    }

    private boolean isTokenExpired(ResetToken passToken) {
        Calendar calendarExpiry = Calendar.getInstance();
        calendarExpiry.setTime(passToken.getExpiryDate());

        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(new Date());

        return calendarExpiry.before(calendarCurrent);
    }
}
