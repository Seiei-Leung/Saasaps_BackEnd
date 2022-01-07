package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.ArgumentSetting;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ArgumentSettingService;
import top.seiei.saasaps.service.UserService;
import top.seiei.saasaps.util.DebugUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/argumentSetting/")
public class ArgumentSettingController {

    @Resource
    private UserService userService;

    @Resource
    private ArgumentSettingService argumentSettingService;

    /**
     * 获取参数定义
     * @return
     */
    @RequestMapping("getAll")
    @ResponseBody
    public ServerResponse getAll() {
        return argumentSettingService.getAll();
    }

    /**
     * 更新参数设置
     * @param session session 对象
     * @param argumentSetting 参数设置
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update(HttpSession session, @RequestBody ArgumentSetting argumentSetting) {
        User user = DebugUtil.getUserBySession(session, userService);
        return argumentSettingService.update(user, argumentSetting);
    }

}
