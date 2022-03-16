package cn.compose.admin.base;

import java.io.Serializable;

/**
 * @author hgm
 * @since 2019-02-22 14:29
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -1L;
    protected Object code;
    protected String msg;
    protected T data;

    public Response() {
    }

    public Object getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public Response<T> setCode(Object code) {
        this.code = code;
        return this;
    }

    public Response<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }
}
