package com.yuan.controllers;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yuan.service.RedisKeyPrefix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * @author joryun ON 2017/10/22.
 */
@Controller
@RequestMapping(value = "/api/admin/picCaptcha/")
@Api(tags = "图片验证码模块")
public class PicCaptchaController {

    private Logger logger = LoggerFactory.getLogger(PicCaptchaController.class);

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> operations;

    @Resource(name = "captchaProducer")
    private DefaultKaptcha captchaProducer;

    /**
     * 生成图片验证码
     */
    @RequestMapping(value = "getPicCaptcha", method = RequestMethod.GET)
    @ApiOperation(value = "获取验证码")
    public void getPicCaptcha(@RequestParam String uuid, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        logger.info("----------capText:" + capText + "----------------");
        //放入redis中，寿命为3分钟
        operations.set(RedisKeyPrefix.pictureCaptcha(uuid), capText, 3, TimeUnit.MINUTES);
        //构造输出流
        ServletOutputStream out = response.getOutputStream();
        try {
            BufferedImage bi = captchaProducer.createImage(capText);
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("----------验证码输出错误-----------------");
        } finally {
            out.close();
        }
    }

}
