package me.yukino.reminderserver.record.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Yukino Yukinoshita
 */

@Entity
@IdClass(RecordPK.class)
public class Record implements Serializable {
    private Timestamp createTime;
    private String name;

    public static Record fromContent(Content content){
        Record record = new Record();
        record.setCreateTime(content.getId());
        record.setName(content.getName());
        return record;
    }

    @Id
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Id
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(createTime, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(createTime, record.createTime) &&
                Objects.equals(name, record.name);
    }
}
