package me.yukino.reminderserver.mail;

import me.yukino.reminderserver.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yukino Yukinoshita
 */

@Service
public class MailService {

    private MailDAO mailDAO;

    @Autowired
    public MailService(MailDAO mailDAO) {
        this.mailDAO = mailDAO;
    }

    public ResponseResult sendMail(String authKey, String address, String subject, String content) {
        if (!mailDAO.containsKey(authKey)) {
            return new ResponseResult(-100, "授权码错误");
        }
        boolean success = MailSender.sendMail(address, subject, content);
        if (success) {
            return new ResponseResult(0, "邮件已发送成功");
        } else {
            return new ResponseResult(-1, "发送邮件异常");
        }
    }

}
