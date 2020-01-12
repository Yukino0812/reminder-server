package me.yukino.reminderserver.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Yukino Yukinoshita
 */

@Component
public class RecordFetchScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RecordFetchScheduler.class);

    @Scheduled(cron = "0/5 * * * * ? ")
    public void fetchRecord(){
        logger.info("fetch");
    }

}
