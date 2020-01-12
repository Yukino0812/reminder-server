package me.yukino.reminderserver.vo;

/**
 * @author Yukino Yukinoshita
 */

public class ResponseResult {

    private Integer code;
    private String msg;
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code) {
        this(code, null);
    }

    public ResponseResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
