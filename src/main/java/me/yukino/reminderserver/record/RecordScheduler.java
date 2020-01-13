package me.yukino.reminderserver.record;

import me.yukino.reminderserver.mail.MailSender;
import me.yukino.reminderserver.record.vo.Content;
import me.yukino.reminderserver.record.vo.Record;
import me.yukino.reminderserver.record.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Yukino Yukinoshita
 */

@Component
public class RecordScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RecordScheduler.class);
    private ScheduledThreadPoolExecutor executor;
    private RecordDAO recordDAO;

    @Autowired
    public RecordScheduler(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
        this.executor = new ScheduledThreadPoolExecutor(4);
    }

    @Scheduled(cron = "0 0/10 * * * ? ")
    public void fetchRecord() {
        Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
        Timestamp timestampLater = new Timestamp(System.currentTimeMillis() + 11 * 60 * 1000);
        List<Record> records = recordDAO.listContent(timestampNow, timestampLater).stream()
                .filter(content -> !recordDAO.containsRecord(Record.fromContent(content)))
                .peek(content -> {
                    User user = recordDAO.getUserByName(content.getName());
                    pushMailTask(user.getEmail(), content);
                })
                .map(Record::fromContent)
                .collect(Collectors.toList());
        recordDAO.saveFetchedRecord(records);
    }

    public void pushMailTask(String address, Content content) {
        executor.schedule(() -> {
            if (recordDAO.validContent(content)) {
                logger.info("Send a mail to {}", address);
                MailSender.sendMail(address, content.getSubject(), content.getDetails());
            } else {
                logger.info("Drop a record because no longer valid.id={},name={}.", content.getId(), content.getName());
            }
        }, content.getTime().getTime() - System.currentTimeMillis() - 10 * 1000, TimeUnit.MILLISECONDS);
    }

}
