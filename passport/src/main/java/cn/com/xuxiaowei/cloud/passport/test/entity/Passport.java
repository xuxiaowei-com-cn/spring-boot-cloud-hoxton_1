package cn.com.xuxiaowei.cloud.passport.test.entity;

import cn.com.xuxiaowei.cloud.mybatis.entity.Entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录模块测试表
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test_passport")
public class Passport extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录模块测试表主键
     */
    @TableId(value = "passport_id", type = IdType.AUTO)
    private Long passportId;

    /**
     * 消息
     */
    @TableField("passport_msg")
    private String passportMsg;

    /**
     * 数量
     */
    @TableField("passport_num")
    private Integer passportNum;


    public static final String PASSPORT_ID = "passport_id";

    public static final String PASSPORT_MSG = "passport_msg";

    public static final String PASSPORT_NUM = "passport_num";

}
