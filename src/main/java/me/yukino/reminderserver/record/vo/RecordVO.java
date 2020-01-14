package me.yukino.reminderserver.record.vo;

/**
 * @author Yukino Yukinoshita
 */

public class RecordVO {

    private String subject;
    private String detail;
    private String time;

    public RecordVO() {
    }

    public RecordVO(String subject, String detail, String time) {
        this.subject = subject;
        this.detail = detail;
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RecordVO{" +
                "subject='" + subject + '\'' +
                ", detail='" + detail + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
