package com.gt.af.security;

import com.alibaba.fastjson.JSONObject;
import com.gt.common.constant.ReturnCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc 登录限制提醒
 * @author zhukeyan
 * @date 2022/4/7
 */

@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", ReturnCode.accountLoginOther.getCode());
        resultObj.put("msg",ReturnCode.accountLoginOther.getMsg());
        response.getWriter().write(resultObj.toString());
    }
}
