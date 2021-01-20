package cn.com.xuxiaowei.www.test.controller;


import cn.com.xuxiaowei.www.test.entity.Www;
import cn.com.xuxiaowei.www.test.service.IWwwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 网站模块测试表 前端控制器
 * </p>
 *
 * @author 徐晓伟
 * @since 2021-01-19
 */
@RestController
@RequestMapping("/test/www")
public class WwwRestController {

    private IWwwService wwwService;

    @Autowired
    public void setWwwService(IWwwService wwwService) {
        this.wwwService = wwwService;
    }

    /**
     * 测试 参数接收、保存数据
     *
     * @param request  请求
     * @param response 响应
     * @param session  session
     * @param www      网站模块测试表
     * @return 返回 测试结果
     */
    @RequestMapping("/save")
    public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                    @RequestBody Www www) {

        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        map.put("data", data);

        www.setCreateUsername("xxw");
        www.setCreateIp(request.getRemoteHost());

        wwwService.save(www);

        map.put("code", "00000");
        map.put("msg", "保存成功");
        map.put("Www Session ID", session.getId());
        data.put("www", www);

        return map;
    }

}

