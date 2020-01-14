package me.yukino.reminderserver.record;

import cn.hutool.core.util.StrUtil;
import me.yukino.reminderserver.mail.MailDAO;
import me.yukino.reminderserver.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yukino Yukinoshita
 */

@Service
public class RecordService {

    private RecordDAO recordDAO;
    private MailDAO mailDAO;

    @Autowired
    public RecordService(RecordDAO recordDAO, MailDAO mailDAO) {
        this.recordDAO = recordDAO;
        this.mailDAO = mailDAO;
    }

    public ResponseResult listRecord(String authKey, String name) {
        if (!mailDAO.containsKey(authKey)) {
            return new ResponseResult(-100, "授权码错误");
        }
        if (StrUtil.isBlank(name)) {
            return new ResponseResult(-1, "用户名不能为空");
        }
        return new ResponseResult(0, "查询成功", recordDAO.listContent(name));
    }

}
