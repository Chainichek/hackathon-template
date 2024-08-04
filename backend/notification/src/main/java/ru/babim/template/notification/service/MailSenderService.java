package ru.babim.template.notification.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.babim.template.notification.dto.notification.NotificationMessage;

@RequiredArgsConstructor
@Service
public class MailSenderService {
    private final JavaMailSender mailSender;
    public void sendMail(@NonNull NotificationMessage message) {

    }
}
