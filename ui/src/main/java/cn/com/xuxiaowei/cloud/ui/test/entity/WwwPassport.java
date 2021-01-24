package cn.com.xuxiaowei.cloud.ui.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 网站、登录模块测试表
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-19
 */
@Data
@Accessors(chain = true)
public class WwwPassport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录模块测试表主键
     */
    private Long passportId;

    /**
     * 消息
     */
    private String passportMsg;

    /**
     * 数量
     */
    private Integer passportNum;

    /**
     * 网站模块测试表主键
     */
    private Long wwwId;

    /**
     * 消息
     */
    private String wwwMsg;

    /**
     * 数量
     */
    private Integer wwwNum;

}
