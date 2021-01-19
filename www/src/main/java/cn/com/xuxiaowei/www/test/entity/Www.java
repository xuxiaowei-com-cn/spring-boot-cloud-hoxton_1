package cn.com.xuxiaowei.www.test.entity;

import cn.com.xuxiaowei.mybatis.entity.Entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网站模块测试表
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test_www")
public class Www extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 网站模块测试表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息
     */
    @TableField("msg")
    private String msg;


    public static final String ID = "id";

    public static final String MSG = "msg";

}
