package me.yukino.reminderserver.record.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Yukino Yukinoshita
 */

public class ContentPK implements Serializable {
    private Timestamp id;
    private String name;

    @Column(name = "id", nullable = false)
    @Id
    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 100)
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentPK contentPK = (ContentPK) o;
        return Objects.equals(id, contentPK.id) &&
                Objects.equals(name, contentPK.name);
    }
}
