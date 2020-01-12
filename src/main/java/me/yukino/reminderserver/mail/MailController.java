package me.yukino.reminderserver.mail;

import me.yukino.reminderserver.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yukino Yukinoshita
 */

@RequestMapping("/mail")
@RestController
public class MailController {

    private MailService mailService;

    @Autowired
    public MailController(MailService mailService){
        this.mailService = mailService;
    }

    @RequestMapping("/send")
    public ResponseResult sendMail(@RequestHeader("auth_key") String key,
                                   @RequestParam String address,
                                   @RequestParam String subject,
                                   @RequestParam String content){
        return mailService.sendMail(key, address, subject, content);
    }

}
