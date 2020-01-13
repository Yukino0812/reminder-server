package me.yukino.reminderserver.record.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Yukino Yukinoshita
 */

@Entity
@IdClass(ContentPK.class)
public class Content implements Serializable {
    private Timestamp id;
    private String name;
    private String subject;
    private String details;
    private Timestamp time;

    @Id
    @Column(name = "id", nullable = false)
    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    @Id
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "subject", nullable = false, length = 200)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "details", nullable = false, length = -1)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subject, details, time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id) &&
                Objects.equals(name, content.name) &&
                Objects.equals(subject, content.subject) &&
                Objects.equals(details, content.details) &&
                Objects.equals(time, content.time);
    }
}
