package ru.gb.mall.inventory.mail.message;

public record EmailMessage( String to, String subject, String text) {
}
