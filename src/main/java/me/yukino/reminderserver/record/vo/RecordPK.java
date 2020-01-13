package me.yukino.reminderserver.record.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Yukino Yukinoshita
 */

public class RecordPK implements Serializable {
    private Timestamp createTime;
    private String name;

    @Column(name = "create_time", nullable = false)
    @Id
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "name", nullable = false, length = 255)
    @Id
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
        RecordPK recordPK = (RecordPK) o;
        return Objects.equals(createTime, recordPK.createTime) &&
                Objects.equals(name, recordPK.name);
    }
}
