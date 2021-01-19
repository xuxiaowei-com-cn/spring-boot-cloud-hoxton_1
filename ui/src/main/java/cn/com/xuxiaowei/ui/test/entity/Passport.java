package cn.com.xuxiaowei.ui.test.entity;

import cn.com.xuxiaowei.mybatis.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录模块测试表
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Passport extends Entity {

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
