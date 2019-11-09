package top.seiei.saasaps.controller;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.ColorSetting;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ColorSettingService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/api/colorSetting/")
public class ColorSettingController {

    @Resource
    private ColorSettingService colorSettingService;

    /**
     * 获取用户的颜色设置
     * @param session session 对象
     * @return
     */
    @RequestMapping("getByUserId")
    @ResponseBody
    public ServerResponse getByUserId(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return colorSettingService.getByUserId(user);
    }

    /**
     * 更新颜色设置
     * @param session session 对象
     * @param params 参数
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update(HttpSession session, @RequestBody Map<String, String> params) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        ColorSetting colorSetting = new ColorSetting();
        colorSetting.setDefaultColor(params.get("defaultColor"));
        colorSetting.setDefaultDelayColor(params.get("defaultDelayColor"));
        colorSetting.setDefaultAdvanceColor(params.get("defaultAdvanceColor"));
        colorSetting.setAdvanceColor(params.get("advanceColor"));
        colorSetting.setDelayColor(params.get("delayColor"));
        colorSetting.setAdvanceDaynum(Integer.parseInt(params.get("advanceDaynum")));
        colorSetting.setDelayDaynum(Integer.parseInt(params.get("delayDaynum")));
        return colorSettingService.update(user, colorSetting);
    }



}
