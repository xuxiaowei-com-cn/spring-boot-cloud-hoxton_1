/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.cloud.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.UPDATE;

/**
 * 实体类 父类
 * <p>
 * 验证说明（使用 {@link Valid} 激活）：
 * 1、{@link AssertFalse}：必须为 {@link Boolean#FALSE}
 * 2、{@link AssertTrue}：必须为 {@link Boolean#TRUE}
 * 3、{@link DecimalMax}：必须为数字，其值必须小于或等于指定的最大值
 * 4、{@link DecimalMin}：必须为数字，其值必须大于或等于指定的最小值
 * 5、{@link Digits}：必须是被接受范围内的数
 * 6、{@link Email}：必须是一个结构完整的电子邮件地址
 * 7、{@link Future}：必须在未来的瞬间，日期或时间
 * 8、{@link FutureOrPresent}：必须在现在或在将来瞬间，日期或时间
 * 9、{@link Max}：必须是一个数字，其值必须小于或等于指定的最大值
 * 10、{@link Min}：必须是一个数字，其值必须大于或等于指定的最小值
 * 11、{@link Negative}：必须是负数，不为 0
 * 12、{@link NegativeOrZero}：必须时负数或 0
 * 13、{@link NotBlank}：不为 null，必须含有至少一个非空白字符
 * 14、{@link NotEmpty}：不为 null，不为空
 * 15、{@link NotNull}：不为 null
 * 16、{@link Null}：必须为 null
 * 17、{@link Past}：必须是在过去的瞬间，日期或时间
 * 18、{@link PastOrPresent}：必须是在过去或在本瞬间，日期或时间
 * 19、{@link Pattern}：正则表达式
 * 20、{@link Positive}：正数，不为 0
 * 21、{@link PositiveOrZero}：正数或 0
 * 22、{@link Size}：大小必须在指定的边界之间（含）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("remark")
    protected String remark;

    /**
     * 创建人用户名，不为空
     */
    @TableField(value = "create_username", fill = INSERT)
    protected String createUsername;

    /**
     * 创建人用户姓名
     * <p>
     * 数据库不存在此字段，仅用与前端数据展示
     */
    @TableField(value = "create_name", exist = false)
    private String createName;

    /**
     * 更新人用户名
     */
    @TableField(value = "update_username", fill = UPDATE)
    protected String updateUsername;

    /**
     * 更新人用户姓名
     * <p>
     * 数据库不存在此字段，仅用与前端数据展示
     */
    @TableField(value = "update_name", exist = false)
    private String updateName;

    /**
     * 创建时的IP，不为空
     */
    @TableField(value = "create_ip", fill = INSERT)
    protected String createIp;

    /**
     * 更新时的IP
     */
    @TableField(value = "update_ip", fill = UPDATE)
    protected String updateIp;

    /**
     * 创建时间，MySQL 自动生成
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_date")
    protected LocalDateTime createDate;

    /**
     * 搜索时创建时间的开始时间
     * <p>
     * 数据库不存在此字段，仅用于搜索
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "start_create_date", exist = false)
    private LocalDateTime startCreateDate;

    /**
     * 搜索时创建时间的结束时间
     * <p>
     * 数据库不存在此字段，仅用于搜索
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "end_create_date", exist = false)
    private LocalDateTime endCreateDate;

    /**
     * 更新时间，MySQL 自动生成
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_date")
    protected LocalDateTime updateDate;

    /**
     * 搜索时修改时间的开始时间
     * <p>
     * 数据库不存在此字段，仅用于搜索
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "start_update_date", exist = false)
    private LocalDateTime startUpdateDate;

    /**
     * 搜索时修改时间的结束时间
     * <p>
     * 数据库不存在此字段，仅用于搜索
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "end_update_date", exist = false)
    private LocalDateTime endUpdateDate;

    /**
     * 逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。
     */
    @TableField("deleted")
    @TableLogic
    protected Boolean deleted;

    public static final String REMARK = "remark";

    public static final String CREATE_USERNAME = "create_username";

    public static final String UPDATE_USERNAME = "update_username";

    public static final String CREATE_IP = "create_ip";

    public static final String UPDATE_IP = "update_ip";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_DATE = "update_date";

    public static final String DELETED = "deleted";

}
