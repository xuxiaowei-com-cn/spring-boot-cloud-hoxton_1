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
package cn.com.xuxiaowei.passport.entity;

import cn.com.xuxiaowei.mybatis.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("test_passport")
public class TestPassport extends Entity {

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


    public static final String ID = "id";

    public static final String MSG = "msg";

}
