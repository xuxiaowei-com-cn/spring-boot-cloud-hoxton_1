package cn.com.xuxiaowei.ui.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 登录模块测试表
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-19
 */
@Data
@Accessors(chain = true)
public class Passport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录模块测试表主键
     */
    private Long id;

    /**
     * 消息
     */
    private String msg;

}
