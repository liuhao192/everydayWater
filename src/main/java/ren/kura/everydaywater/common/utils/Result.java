package ren.kura.everydaywater.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import lombok.Data;
import ren.kura.everydaywater.common.constant.CommonConstant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>项目名称: everydaywater
 * <p>文件名称: Result.java
 * <p>描述: 返回数据的结果
 * <p>HISTORY: 2020/10/10 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/10 23:13
 */
@Data
public class Result  implements Serializable {

    private boolean success = true;
    /**
     * 操作是否成功
     */
    private Integer code;
    /**
     * 反馈信息
     */
    private String message;
    /**
     * 附加数据
     */
    private Map body = new HashMap();
    /**
     * 时间搓
     */
    private long timestamp = System.currentTimeMillis();


    public Result success(String message) {
        this.message = message;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(CommonConstant.SC_OK_200);
        result.setMessage(msg);
        return result;
    }


    public static Result putDataOk(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(CommonConstant.SC_OK_200);
        result.addElement("data", data);
        return result;
    }

    public static Result putDataOk(String key,Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(CommonConstant.SC_OK_200);
        result.addElement(key, data);
        return result;
    }


    public static Result error(String msg) {
        return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public Result error500(String message) {
        this.message = message;
        this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.disableHtmlEscaping().create();
        JsonElement jsonObject = gson.toJsonTree(this);
        String json = jsonObject.toString();
        return json;
    }

   /**
    *  Result:: addElement
    *  <p>将数据存入返回的结果的map对象中
    *  <p>HISTORY: 2020/10/11 liuha : Created.
    *  @param    key    key
    *  @param    value  数据

    */
    public void addElement(String key, Object value) {
        body.put(key, value);
    }

    /**
     *  Result:: getElement
     *  <p>去返回结果中取值
     *  <p>HISTORY: 2020/10/11 liuha : Created.
     *  @param    key  map中的key
     *  @return   Object  返回map中存储的对象
     */
    public Object getElement(String key) {
        return body.get(key);
    }
}
