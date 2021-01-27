package cn.com.xuxiaowei.cloud.utils.http;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 接口响应数据
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private Map<String, Object> data;

}
