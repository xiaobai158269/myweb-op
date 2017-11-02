/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: IndexController.java
 * Author:   admin
 * Date:     2016-03-18 17:37:51
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述
 * <admin><2016-03-18 17:37:51><version><desc>
 */

package cmy.myweb.op.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 主页
 *
 * @author HUQIANBO
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("")
public class IndexController {

    // --------------------------- page ------------------------------------ //
//    @Value("${passport.postUrl}")
//    public String PASSPORT_PAGE; //单点登录页面

    public static final String INDEX_PAGE = "index/index"; //百宝箱首页 -- 有客房订单

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);
        return modelAndView;
    }


}
