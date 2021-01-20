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
 * @since 2021-01-20
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
    @TableId(value = "www_id", type = IdType.AUTO)
    private Long wwwId;

    /**
     * 消息
     */
    @TableField("www_msg")
    private String wwwMsg;

    /**
     * 数量
     */
    @TableField("www_num")
    private Integer wwwNum;


    public static final String WWW_ID = "www_id";

    public static final String WWW_MSG = "www_msg";

    public static final String WWW_NUM = "www_num";

}
