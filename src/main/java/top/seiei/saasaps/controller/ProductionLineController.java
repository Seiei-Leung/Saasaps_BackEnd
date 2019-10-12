package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.*;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ProductionLineService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/productionline/")
public class ProductionLineController {

    @Resource
    private ProductionLineService productionLineService;

    /**
     * 	获取所有生产线列表
     * @return 所有生产线列表
     */
    @RequestMapping("getall")
    @ResponseBody
    public ServerResponse getAll() {
        return productionLineService.getall();
    }

    /**
     *	 根据用户 ID 获取获取权限生产线
     * @param session session 对象
     * @return 权限生产线
     */
    @RequestMapping("getByUserId")
    @ResponseBody
    public ServerResponse selectAllByUserId(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.selectAllByUserId(user.getId());
    }

    /**
     * 根据生产线 ID 获取生产线详情
     * @param lineid 生产线 ID
     * @return 生产线详情
     */
    @RequestMapping("getdetailbylineid")
    @ResponseBody
    public ServerResponse selectDetailByLineId(Integer lineid) {
        return productionLineService.selectDetailByLineId(lineid);
    }

    /**
     *	根据生产线的 ID 获取生产线的详情
     * @param lineid 生产线的 ID
     * @return 生产线的详情
     */
    @RequestMapping("getByLineId")
    @ResponseBody
    public ServerResponse selectByLineId(Integer lineid) {
        return productionLineService.selectByLineId(lineid);
    }

    /**
     * 更新生产线主体信息
     * @param session session 对象
     * @param productionLine 生产线信息
     * @return 是否更新成功
     */
    @RequestMapping("update")
    @ResponseBody
    public ServerResponse update(HttpSession session, ProductionLine productionLine) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.update(user, productionLine);
    }

    /**
     * 新增生产线
     * @param session session 对象
     * @param productionLine 新增信息
     * @return 是否新增成功
     */
    @RequestMapping("add")
    @ResponseBody
    public ServerResponse add(HttpSession session, ProductionLine productionLine) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.add(user, productionLine);
    }

    /**
     * 删除生产线
     * @param id 生产线 ID
     * @return 是否删除成功
     */
    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse delete(Integer id) {
        return productionLineService.delete(id);
    }

    /**
     * 更新生产线效率信息
     * @param session session 对象
     * @param efficiencyOfLine 更新生产线效率信息
     * @return 是否更新成功
     */
    @RequestMapping("updateEfficiency")
    @ResponseBody
    public ServerResponse updateEfficiency(HttpSession session, EfficiencyOfLine efficiencyOfLine) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.updateEfficiency(user, efficiencyOfLine);
    }

    /**
     * 删除生产线属性效率
     * @param id 该生产线属性效率的 ID
     * @return 是否删除成功
     */
    @RequestMapping("deleteEfficiency")
    @ResponseBody
    public ServerResponse deleteEfficiency(Integer id) {
        return productionLineService.deleteEfficiency(id);
    }

    /**
     * 新增生产线属性效率
     * @param session session 对象
     * @param efficiencyOfLine 更新生产线效率信息
     * @return 是否删除成功
     */
    @RequestMapping("addEfficiency")
    @ResponseBody
    public ServerResponse addEfficiency(HttpSession session, EfficiencyOfLine efficiencyOfLine) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.addEfficiency(user, efficiencyOfLine);
    }

    /**
     * 更新工作时间
     * @param session session 对象
     * @param id 属性 ID
     * @param startTime 属性有效开始时间
     * @param endTime 属性有效结束时间
     * @param workhours 工时
     * @return 是否更新成功
     */
    @RequestMapping("updateWorkhours")
    @ResponseBody
    public ServerResponse updateWorkhours(HttpSession session, Integer id, long startTime, long endTime, Double workhours) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.updateWorkhours(user, id, startTime, endTime, workhours);
    }

    /**
     * 删除工作时间
     * @param id 该生产线工作时间属性的 ID
     * @return 是否删除成功
     */
    @RequestMapping("deleteWorkhours")
    @ResponseBody
    public ServerResponse deleteWorkhours(Integer id) {
        return productionLineService.deleteWorkhours(id);
    }

    /**
     * 新增生产线工作时间属性
     * @param session session 对象
     * @param productionLineId 生产线 Id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param workhours 工作时间
     * @return 是否成功
     */
    @RequestMapping("addWorkhours")
    @ResponseBody
    public ServerResponse addWorkhours(HttpSession session, Integer productionLineId, Long startTime, Long endTime, Double workhours) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.addWorkhours(user, productionLineId, startTime, endTime, workhours);
    }

    /**
     * 更新生产线工作人数属性
     * @param session session 对象
     * @param id 更新工作人数属性 Id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param peopleNum 人数
     * @return 是否更新成功
     */
    @RequestMapping("updatePeopleNum")
    @ResponseBody
    public ServerResponse updatePeopleNum(HttpSession session, Integer id, long startTime, long endTime, Integer peopleNum) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.updatePeopleNum(user, id, startTime, endTime, peopleNum);
    }

    /**
     * 新增生产线工作人数属性
     * @param session session 对象
     * @param productionLineId 生产线 ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param peopleNum 人数
     * @return 是否更新成功
     */
    @RequestMapping("addPeopleNum")
    @ResponseBody
    public ServerResponse addPeopleNum(HttpSession session, Integer productionLineId, Long startTime, Long endTime, Integer peopleNum) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionLineService.addPeopleNum(user, productionLineId, startTime, endTime, peopleNum);
    }

    /**
     * 删除生产线工作人数属性
     * @param id 该生产线工作时间属性的 ID
     * @return 是否删除成功
     */
    @RequestMapping("deletePeopleNum")
    @ResponseBody
    public ServerResponse deletePeopleNum(Integer id) {
        return productionLineService.deletePeopleNum(id);
    }
}
