package mail.message;

import ru.gb.mall.inventory.mail.EmailService;

public record EmailMessage(String from, String to, String subject, String text) {
}
