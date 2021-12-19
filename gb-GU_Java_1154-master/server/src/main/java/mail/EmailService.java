package mail;

import ru.gb.mall.inventory.mail.message.AttachmentMailMessage;
import ru.gb.mall.inventory.mail.message.EmailMessage;

public interface EmailService {
    void send(EmailMessage message);
    void send(AttachmentMailMessage message);
}
