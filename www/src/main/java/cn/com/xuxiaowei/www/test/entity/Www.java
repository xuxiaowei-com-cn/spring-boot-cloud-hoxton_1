package cn.com.xuxiaowei.www.test.entity;

import cn.com.xuxiaowei.mybatis.entity.Entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

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
@TableName("test_www")
public class Www extends Entity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录模块测试表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息
     */
    @TableField("msg")
    private String msg;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建人用户名，不为空
     */
    @TableField("create_username")
    private String createUsername;

    /**
     * 更新人用户名
     */
    @TableField("update_username")
    private String updateUsername;

    /**
     * 创建时的IP，不为空
     */
    @TableField("create_ip")
    private String createIp;

    /**
     * 更新时的IP
     */
    @TableField("update_ip")
    private String updateIp;

    /**
     * 创建时间，MySQL 自动生成
     */
    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 更新时间，MySQL 自动生成
     */
    @TableField("update_date")
    private LocalDateTime updateDate;

    /**
     * 逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


    public static final String ID = "id";

    public static final String MSG = "msg";

    public static final String REMARK = "remark";

    public static final String CREATE_USERNAME = "create_username";

    public static final String UPDATE_USERNAME = "update_username";

    public static final String CREATE_IP = "create_ip";

    public static final String UPDATE_IP = "update_ip";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_DATE = "update_date";

    public static final String DELETED = "deleted";

}
