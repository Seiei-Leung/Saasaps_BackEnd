package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ProductionLineRightService;
import top.seiei.saasaps.vo.UpdatePLVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/productionlineright/")
public class ProductionLineRightController {

    @Resource
    private ProductionLineRightService productionLineRightService;

    /**
     * 获取全部生产线树状图结构的数据
     * @param session session 对象
     * @return 全部生产线树状图结构的数据
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getAll(HttpSession session) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return productionLineRightService.getAll();
    }

    /**
     * 根据用户 ID 获取生产线权限
     * @param userid 用户 ID
     * @return 权限列表
     */
    @RequestMapping("getbyuserid")
    @ResponseBody
    public ServerResponse selectByUserId(Integer userid) {
        return productionLineRightService.selectByUserId(userid);
    }

    /**
     * 更新生产线权限
     * @param session session 对象
     * @param updatePLVO 更新生产线权限的 VO
     * @return 是否更新成功
     */
    @RequestMapping("update")
    @ResponseBody
    public ServerResponse update(HttpSession session,@RequestBody UpdatePLVO updatePLVO) {
        System.out.println(updatePLVO.getList().size());
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return productionLineRightService.update(admin, updatePLVO.getUserId(), updatePLVO.getList());
    }
}
