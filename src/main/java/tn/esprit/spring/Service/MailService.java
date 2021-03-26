package tn.esprit.spring.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
 
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import tn.esprit.spring.dto.cart.CartItemDto;

@Service
public class MailService {

	
	@Autowired
    private JavaMailSender mailSender;
 
    @Autowired
    private Configuration configuration;
 
    public String sendMail( Map<String,Object> model) {
 
        String response;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
          //  ClassPathResource pdf = new ClassPathResource("static/attachment.pdf");
         //   ClassPathResource image = new ClassPathResource("static/asbnotebook.png");
            Template template = configuration.getTemplate("email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setTo("ay.bousselmi@gmail.com");
            helper.setFrom("wwzera05@gmail.com");
            helper.setSubject("Thank you for you purchase");
            helper.setText(html, true);
      //      helper.addInline("asbnotebook", image);
         //   helper.addAttachment("attachment.pdf", pdf);
            mailSender.send(message);
            response = "Email has been sent to :" ;
        } catch (MessagingException | IOException | TemplateException e) {
            response = "Email send failure to :" ;
        }
        return response;
    }
}
