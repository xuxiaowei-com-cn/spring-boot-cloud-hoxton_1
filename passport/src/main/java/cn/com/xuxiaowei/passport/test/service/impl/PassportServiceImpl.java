package cn.com.xuxiaowei.passport.test.service.impl;

import cn.com.xuxiaowei.passport.test.entity.Passport;
import cn.com.xuxiaowei.passport.test.mapper.PassportMapper;
import cn.com.xuxiaowei.passport.test.service.IPassportService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 登录模块测试表 服务实现类
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-19
 */
@Slf4j
@Service
public class PassportServiceImpl extends ServiceImpl<PassportMapper, Passport> implements IPassportService {

    /**
     * 保存
     *
     * @param entity 实体类
     * @return 返回 保存结果
     */
    @DS("master")
    @Override
    @GlobalTransactional
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean save(Passport entity) {

        log.info("当前 XID: {}", RootContext.getXID());

        boolean save = super.save(entity);
        int i = 1 / entity.getPassportNum();
        return save;
    }

}
