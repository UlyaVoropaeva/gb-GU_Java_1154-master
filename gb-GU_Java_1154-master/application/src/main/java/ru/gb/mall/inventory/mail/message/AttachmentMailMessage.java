package ru.gb.mall.inventory.mail.message;

public record AttachmentMailMessage(
        String from,

        String to,
        String subject,
        String text,
        EmailAttachment attachment
)  {
}