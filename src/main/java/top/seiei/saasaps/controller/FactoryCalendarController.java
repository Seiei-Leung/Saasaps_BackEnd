package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.Festival;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.bean.WorkingDateSetting;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.FactoryCalendarService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/factoryCalendar/")
public class FactoryCalendarController {

    @Resource
    private FactoryCalendarService factoryCalendarService;

    /**
     * 获取工作日历的主要内容
     * @return 工作日历的主要内容
     */
    @RequestMapping("getFactoryCalendarList")
    @ResponseBody
    public ServerResponse getFactoryCalendarList() {
        return factoryCalendarService.getFactoryCalendarList();
    }

    /**
     * 添加工作日历
     * @param session session 对象
     * @param workingDateSetting 添加的工作日历信息
     * @return 是否成功
     */
    @RequestMapping("addFactoryCalendar")
    @ResponseBody
    public ServerResponse addFactoryCalendar(HttpSession session, WorkingDateSetting workingDateSetting) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return factoryCalendarService.addFactoryCalendar(admin, workingDateSetting);
    }

    /**
     * 更新工作日历
     * @param session session 对象
     * @param workingDateSetting 更新的工作日历信息
     * @return 是否成功
     */
    @RequestMapping("updateFactoryCalendar")
    @ResponseBody
    public ServerResponse updateFactoryCalendar(HttpSession session, WorkingDateSetting workingDateSetting) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return factoryCalendarService.updateFactoryCalendar(admin, workingDateSetting);
    }

    /**
     * 删除工作日历
     * @param session session 对象
     * @param id 工作日历的 ID
     * @return 是否成功
     */
    @RequestMapping("deleteFactoryCalendar")
    @ResponseBody
    public ServerResponse deleteFactoryCalendar(HttpSession session, Integer id) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return factoryCalendarService.deleteFactoryCalendar(id);
    }

    /**
     * 获取所有节假日
     * @param id 工作日历的主 ID
     * @return 所有节假日
     */
    @RequestMapping("getFestival")
    @ResponseBody
    public ServerResponse getFestivalById(Integer id) {
        return factoryCalendarService.getFestivalById(id);
    }

    /**
     * 添加节日
     * @param session session 信息
     * @param factoryCalendarId 工厂日历 id
     * @param festivalName 节日名称
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    @RequestMapping("addFestival")
    @ResponseBody
    public ServerResponse addFestival(HttpSession session, Integer factoryCalendarId, String festivalName, Long beginDate, Long endDate) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return factoryCalendarService.addFestival(admin, factoryCalendarId, festivalName, beginDate, endDate);
    }

    /**
     * 修改节假日
     * @param session session 对象
     * @param id 节日 id
     * @param festivalName 节日名称
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    @RequestMapping("updateFestival")
    @ResponseBody
    public ServerResponse updateFestival(HttpSession session, Integer id, String festivalName, Long beginDate, Long endDate) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return factoryCalendarService.updateFestival(admin, id, festivalName, beginDate, endDate);
    }

    /**
     * 删除节日信息
     * @param session session 对象
     * @param id 节日 ID
     * @return 是否成功
     */
    @RequestMapping("deleteFestival")
    @ResponseBody
    public ServerResponse deleteFestival(HttpSession session, Integer id) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return factoryCalendarService.deleteFestival(id);
    }

    /**
     * 排产器获取工厂日历
     * @param yearList 年份列表
     * @return
     */
    @RequestMapping("getFactoryCalendarByYear")
    @ResponseBody
    public ServerResponse getFactoryCalendarByYear(String yearList) {
        List<Integer> years = new ArrayList<>();
        for (String item : yearList.split(",")) {
            years.add(Integer.parseInt(item));
        }
        return factoryCalendarService.getFactoryCalendarByYear(years);
    }
}
