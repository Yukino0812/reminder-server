package me.yukino.reminderserver.record;

import me.yukino.reminderserver.record.vo.Content;
import me.yukino.reminderserver.record.vo.Record;
import me.yukino.reminderserver.record.vo.User;
import me.yukino.reminderserver.util.SessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yukino Yukinoshita
 */

@Repository
public class RecordDAO {

    public List<Content> listContent(Timestamp from, Timestamp to) {
        Session session = SessionFactoryUtil.getContentSessionFactoryInstance().openSession();
        Criteria criteria = session.createCriteria(Content.class);
        List<Content> contents = criteria.list();

        session.close();
        return contents.stream().filter(content -> content.getTime().after(from))
                .filter(content -> content.getTime().before(to))
                .collect(Collectors.toList());
    }

    public User getUserByName(String name) {
        Session session = SessionFactoryUtil.getContentSessionFactoryInstance().openSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(session.getCriteriaBuilder().equal(root.get("name"), name));
        User user = session.createQuery(criteriaQuery).uniqueResult();

        session.close();
        return user;
    }

    public boolean validContent(Content content) {
        Session session = SessionFactoryUtil.getContentSessionFactoryInstance().openSession();
        Criteria criteria = session.createCriteria(Content.class);
        List<Content> contents = criteria.list();

        session.close();
        return contents.stream().anyMatch(
                c -> c.getId().equals(content.getId())
                        && c.getName().equals(content.getName()));
    }

    public boolean containsRecord(Record record) {
        Session session = SessionFactoryUtil.getSessionFactoryInstance().openSession();
        Criteria criteria = session.createCriteria(Record.class);
        List<Record> records = criteria.list();

        session.close();
        return records.stream().anyMatch(
                r -> r.getName().equals(record.getName())
                        && r.getCreateTime().equals(record.getCreateTime()));
    }

    public void saveFetchedRecord(List<Record> records) {
        Session session = SessionFactoryUtil.getSessionFactoryInstance().openSession();
        session.beginTransaction();
        records.forEach(session::save);
        session.getTransaction().commit();
        session.close();
    }

}
