package com.web.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

public class AsyncEmailSender {
    private final MailerService  mailer;

    public AsyncEmailSender(MailerService  mailer) {
        this.mailer = mailer;
    }

    public void sendEmail(String to, String subject, String body) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); ///tạo một dịch vụ thực thi có một luồng duy nhất
        executorService.submit(() -> { //gửi tác vụ được chỉ định để thực thi trong dịch vụ thực thi.
            try {
                mailer.send(to, subject, body);// tác vụ được gửi đi thực hiện
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown(); //đóng dịch vụ thực thi.
    }
}
