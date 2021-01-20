package cn.com.xuxiaowei.passport.test.service.impl;

import cn.com.xuxiaowei.passport.test.entity.Passport;
import cn.com.xuxiaowei.passport.test.mapper.PassportMapper;
import cn.com.xuxiaowei.passport.test.service.IPassportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
@Service
public class PassportServiceImpl extends ServiceImpl<PassportMapper, Passport> implements IPassportService {

    /**
     * 保存
     *
     * @param entity 实体类
     * @return 返回 保存结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean save(Passport entity) {
        boolean save = super.save(entity);
        int i = 1 / entity.getPassportNum();
        return save;
    }

}
