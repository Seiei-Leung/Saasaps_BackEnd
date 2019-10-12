package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.bo.ProductionPlanningForUpdate;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ProductionPlanningService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/productionplanning/")
public class ProductionPlanningController {

    @Resource
    private ProductionPlanningService productionPlanningService;

    /**
     * 获取用户权限生产线的所有生产计划信息列表
     * @param session session 对象
     * @return 用户权限生产线的所有生产计划信息列表
     */
    @RequestMapping("getallproductionplanning")
    @ResponseBody
    public ServerResponse selectProductionPlanningByUser(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionPlanningService.selectProductionPlanningByUser(user);
    }

    /**
     * 更新排产计划
     * @param session session 对象
     * @return 是否成功
     */
    @RequestMapping("updateproductionplanning")
    @ResponseBody
    public ServerResponse updateProductionPlanning(HttpSession session, @RequestBody Map<String, List<ProductionPlanningForUpdate>> request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        ServerResponse result;
        try {
            result = productionPlanningService.updateProductionPlanning(user, request);
        } catch (Exception e) {
            result = ServerResponse.createdByError(e.getMessage());
        }
        return result;
    }

    /**
     * 减数功能
     * @param session session 对象
     * @param params 参数
     * @return 是否成功
     */
    @RequestMapping("updateqtyfinish")
    @ResponseBody
    public ServerResponse updateQtyFinish(HttpSession session, @RequestBody Map<String, String> params) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        ServerResponse result;
        try {
            result = productionPlanningService.updateQtyFinish(user, params.get("idListString"), params.get("qtyFinishListString"), new Date(Long.parseLong(params.get("endTime"))));
        } catch (Exception e) {
            result = ServerResponse.createdByError(e.getMessage());
        }
        return result;
    }


    @RequestMapping("takeapart")
    @ResponseBody
    public ServerResponse takeApart(HttpSession session, @RequestBody Map<String, String> params) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return null;
    }
}
