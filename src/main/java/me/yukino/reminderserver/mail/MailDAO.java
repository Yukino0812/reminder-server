package me.yukino.reminderserver.mail;

import me.yukino.reminderserver.mail.vo.AuthKey;
import me.yukino.reminderserver.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Yukino Yukinoshita
 */

@Repository
public class MailDAO {

    public boolean containsKey(String authKey) {
        Session session = SessionFactoryUtil.getSessionFactoryInstance().openSession();
        CriteriaQuery<AuthKey> criteriaQuery = session.getCriteriaBuilder().createQuery(AuthKey.class);
        Root<AuthKey> root = criteriaQuery.from(AuthKey.class);
        criteriaQuery.select(root);
        criteriaQuery.where(session.getCriteriaBuilder().equal(root.get("key"), authKey));
        List<AuthKey> keys = session.createQuery(criteriaQuery).list();

        session.close();
        return keys.size() > 0;
    }

}
