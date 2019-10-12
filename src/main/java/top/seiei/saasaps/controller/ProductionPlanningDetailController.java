package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ProductionPlanningDetailService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/productionplanningdetail/")
public class ProductionPlanningDetailController {

    @Resource
    private ProductionPlanningDetailService productionPlanningDetailService;

    @RequestMapping("getalltodo")
    @ResponseBody
    public ServerResponse getAllToDo() {
        return productionPlanningDetailService.selectAllToDo();
    }

/*    @RequestMapping("getdetail")
    @ResponseBody
    public ServerResponse getDetail(Integer id) {
        return productionPlanningDetailService.selectByProductionPlanningId(id);
    }*/

}
