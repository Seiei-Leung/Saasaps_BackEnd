package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.SummaryOfProductionPlanningDetailService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/summaryOfProductionPlanning/")
public class SummaryOfProductionPlanningDetailController {

    @Resource
    private SummaryOfProductionPlanningDetailService summaryOfProductionPlanningDetailService;

    /**
     * 获取走货一览表
     * @param pageIndex 当前页码
     * @param pageSize 页数
     * @return
     */
    @RequestMapping("getAll")
    @ResponseBody
    public ServerResponse getAll(Integer pageIndex, Integer pageSize) {
        return summaryOfProductionPlanningDetailService.getAll(pageIndex, pageSize);
    }

    /**
     * 更新数据
     * @param session session 对象
     * @param id 主键
     * @param clientname 客户名
     * @param season 季节
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ServerResponse update(HttpSession session, Integer id, String clientname, String season) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return summaryOfProductionPlanningDetailService.update(user, id, clientname, season);
    }

    /**
     * 删除走货一览表
     * @param session session 对象
     * @param id 主键
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse delete(HttpSession session, Integer id) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return summaryOfProductionPlanningDetailService.delete(id);
    }

}
