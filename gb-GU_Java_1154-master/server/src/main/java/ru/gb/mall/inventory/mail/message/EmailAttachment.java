package ru.gb.mall.inventory.mail.message;

import java.io.InputStream;

public record EmailAttachment(String name, InputStream resource) {
}
