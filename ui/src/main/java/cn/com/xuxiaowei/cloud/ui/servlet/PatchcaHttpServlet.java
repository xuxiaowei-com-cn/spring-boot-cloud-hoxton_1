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
package cn.com.xuxiaowei.cloud.ui.servlet;

import cn.com.xuxiaowei.cloud.ui.properties.PatchcaDefaultProperties;
import cn.com.xuxiaowei.cloud.utils.http.ImageUtils;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * 全自动区分计算机和人类的图灵测试
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class PatchcaHttpServlet extends HttpServlet {

    private PatchcaDefaultProperties patchcaDefaultProperties;

    @Autowired
    public void setPatchcaDefaultProperties(PatchcaDefaultProperties patchcaDefaultProperties) {
        this.patchcaDefaultProperties = patchcaDefaultProperties;
    }

    /**
     * 图片验证码服务
     */
    private static final ConfigurableCaptchaService CS = new ConfigurableCaptchaService();

    /**
     * 随机数
     */
    private static final Random RANDOM = new Random();

    @Override
    public void init() {
        /* cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170))); */

        // 设置颜色
        CS.setColorFactory(index -> {

            // 创建一个数组，用于储存 rgb 颜色
            int[] c = new int[3];

            // 在 3 以内的随机数
            int i = RANDOM.nextInt(c.length);

            // 使用两次随机数组合（第一次在0、1、2，第二次在0-71或0-256），
            // 使得rgb颜色的三个数，必有两个分别在0-71和0-256之间，另一个必在0-71或0-256中
            for (int fi = 0; fi < c.length; fi++) {
                if (fi == i) {
                    // 在 71 以内的随机数
                    c[fi] = RANDOM.nextInt(71);
                } else {
                    // 在 256 以内的随机数
                    c[fi] = RANDOM.nextInt(256);
                }
            }

            // 返回颜色
            return new Color(c[0], c[1], c[2]);
        });

        // 随机字符
        RandomWordFactory randomWordFactory = new RandomWordFactory();

        // 字符范围
        // 去掉了容易混淆的 1、0、l、o、I、O
        // 23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ
        randomWordFactory.setCharacters(patchcaDefaultProperties.getCharacters());

        // 字符数量范围
        // 最多3个
        randomWordFactory.setMaxLength(patchcaDefaultProperties.getMaxLength());
        // 最少3个
        randomWordFactory.setMinLength(patchcaDefaultProperties.getMinLength());
        // 设置随机字符
        CS.setWordFactory(randomWordFactory);

        // 图片像素
        CS.setHeight(patchcaDefaultProperties.getHeight());
        CS.setWidth(patchcaDefaultProperties.getWidth());

        // 字体大小范围
        RandomFontFactory randomFontFactory = new RandomFontFactory();

        // 字体最大大小
        randomFontFactory.setMaxSize(patchcaDefaultProperties.getMaxSize());
        // 字体最小大小
        randomFontFactory.setMinSize(patchcaDefaultProperties.getMinSize());

        // 设置 字体大小范围
        CS.setFontFactory(randomFontFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 获取 Session 需要在 getPatchca(req, resp) 之前
        HttpSession session = req.getSession();

        String patchca = getPatchca(resp);

        session.setAttribute(patchcaDefaultProperties.getSessionName(), patchca);

        log.debug("");
        log.debug("图片验证码为：" + patchca);
        log.debug("");

    }

    /**
     * 获取图片验证码对应的字符串
     */
    private String getPatchca(HttpServletResponse resp) throws IOException {
        switch (RANDOM.nextInt(patchcaDefaultProperties.getRandom())) {
            case 0:
                // 摆动波纹
                CS.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 1:
                // 大理石纹波
                CS.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                // 双纹波
                CS.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                // 曲线波纹
                CS.setFilterFactory(new CurvesRippleFilterFactory(CS.getColorFactory()));
                break;
            case 4:
                // 漫反射纹波
                CS.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
            default:
        }

        // 设置图片验证码的响应
        ImageUtils.setResponseHeaders(resp);

        return EncoderHelper.getChallangeAndWriteImage(CS, patchcaDefaultProperties.getFormat(), resp.getOutputStream());
    }

}
