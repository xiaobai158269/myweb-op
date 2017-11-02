package cmy.myweb.op.controller;

import cmy.base.constants.Constants;
import cmy.base.model.ReturnObject;
import cmy.base.untils.CookieUtils;
import cmy.base.untils.JsonUtil;
import cmy.myweb.op.service.RedisService;
import cmy.user.qo.UserQO;
import cmy.user.service.UserService;
import cmy.user.vo.UserVO;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
public class LoginController {
    private static final String USERTOKEN = "USERTOKEN";
    @Value("${sessionTime}")
    private Long sessionTime;
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/login")
    public String toLoginPage() {
        return "login";
    }


    @RequestMapping("/dologin")
    @ResponseBody
    public String dologin(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        boolean flag = true;
        JSONObject jsonObject=new JSONObject();
        if (StringUtils.isBlank(username)) {
            jsonObject.put("result", false);
            jsonObject.put("msg", "用户名为空");
            flag = false;
        }
        if (StringUtils.isBlank(password)) {
            jsonObject.put("result", false);
            jsonObject.put("msg", "密码为空");
            flag = false;
        }
        if (flag) {
            UserQO userQO = new UserQO();
            userQO.setUsername(username);
            ReturnObject<UserVO> returnObject = userService.getUserName(userQO);
            if (Constants.RETURN_CODE_SUCCESS.equals(returnObject.getReturnCode())) {
                UserVO userVO = returnObject.getData();
                if (userVO != null) {
                    String userPassword = userVO.getPassword();
                    password = DigestUtils.md5DigestAsHex(password.getBytes());
                    if (password.equals(userPassword)) {
                        String uuidToken = USERTOKEN + ":" + UUID.randomUUID();
                        redisService.set(uuidToken, JsonUtil.JSON_Bean2String(userVO), sessionTime);
                        CookieUtils.setCookie(request, response, USERTOKEN, uuidToken);
                        jsonObject.put("result", true);
                    } else {
                        jsonObject.put("result", false);
                        jsonObject.put("msg", "1");
                    }
                }
            }
        }
        return jsonObject.toJSONString();
    }
}
