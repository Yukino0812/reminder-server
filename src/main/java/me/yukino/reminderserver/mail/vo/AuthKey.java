package me.yukino.reminderserver.mail.vo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yukino Yukinoshita
 */

@Entity
@Table(name = "auth_key", schema = "reminder", catalog = "")
public class AuthKey {
    private Integer id;
    private String key;
    private String desc;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "key", nullable = true, length = 64)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "desc", nullable = true, length = 255)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthKey authKey = (AuthKey) o;
        return Objects.equals(id, authKey.id) &&
                Objects.equals(key, authKey.key) &&
                Objects.equals(desc, authKey.desc);
    }
}
