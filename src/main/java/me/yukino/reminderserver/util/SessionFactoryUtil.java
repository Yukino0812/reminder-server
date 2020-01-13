package me.yukino.reminderserver.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate SessionFactory 单例
 *
 * @author Yukino Yukinoshita
 */

public class SessionFactoryUtil {

    private static volatile SessionFactory sessionFactoryInstance;
    private static volatile SessionFactory contentSessionFactoryInstance;

    public static SessionFactory getSessionFactoryInstance() {
        if (sessionFactoryInstance == null) {
            synchronized (SessionFactoryUtil.class) {
                if (sessionFactoryInstance == null) {
                    Configuration configuration = new Configuration().configure();
                    SessionFactoryUtil.sessionFactoryInstance = configuration.buildSessionFactory();
                }
            }
        }
        return sessionFactoryInstance;
    }

    public static SessionFactory getContentSessionFactoryInstance(){
        if (contentSessionFactoryInstance == null) {
            synchronized (SessionFactoryUtil.class) {
                if (contentSessionFactoryInstance == null) {
                    Configuration configuration = new Configuration().configure("hibernate-content.cfg.xml");
                    SessionFactoryUtil.contentSessionFactoryInstance = configuration.buildSessionFactory();
                }
            }
        }
        return contentSessionFactoryInstance;
    }

}
