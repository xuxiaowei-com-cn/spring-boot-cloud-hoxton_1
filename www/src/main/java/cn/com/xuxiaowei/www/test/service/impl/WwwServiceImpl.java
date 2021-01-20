package cn.com.xuxiaowei.www.test.service.impl;

import cn.com.xuxiaowei.www.test.entity.Www;
import cn.com.xuxiaowei.www.test.mapper.WwwMapper;
import cn.com.xuxiaowei.www.test.service.IWwwService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
@Service
public class WwwServiceImpl extends ServiceImpl<WwwMapper, Www> implements IWwwService {

    /**
     * 保存
     *
     * @param entity 实体类
     * @return 返回 保存结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean save(Www entity) {
        boolean save = super.save(entity);
        int i = 1 / entity.getWwwNum();
        return save;
    }

}
