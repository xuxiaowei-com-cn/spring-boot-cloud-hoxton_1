package cn.com.xuxiaowei.www.test.service.impl;

import cn.com.xuxiaowei.www.test.entity.Www;
import cn.com.xuxiaowei.www.test.mapper.WwwMapper;
import cn.com.xuxiaowei.www.test.service.IWwwService;
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
 * 网站模块测试表 服务实现类
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-19
 */
@Slf4j
@Service
public class WwwServiceImpl extends ServiceImpl<WwwMapper, Www> implements IWwwService {

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
    public boolean save(Www entity) {

        log.info("当前 XID: {}", RootContext.getXID());

        boolean save = super.save(entity);
        int i = 1 / entity.getWwwNum();
        return save;
    }

}
