package ru.gb.mall.inventory.mail;

public class MailSenderException extends RuntimeException {
    public MailSenderException(String message) {
        super(message);
    }

    public MailSenderException(String message, Throwable cause) {
        super(message, cause);
    }
}
