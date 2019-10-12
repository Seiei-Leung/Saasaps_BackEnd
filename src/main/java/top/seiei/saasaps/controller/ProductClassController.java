package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.ProductClass;
import top.seiei.saasaps.bean.ProductClassEfficiency;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ProductClassService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/productclass/")
public class ProductClassController {

    @Resource
    private ProductClassService productClassService;

    /**
     * 	获取所有排产品类概括
     * @return 所有排产品类列表
     */
    @RequestMapping("getAllProductClass")
    @ResponseBody
    public ServerResponse getAllProductClass() {
        return productClassService.selectAll();
    }

    /**
     * 获取产品类详情
     * @param productClassId 产品类id
     * @return
     */
    @RequestMapping("getDetailById")
    @ResponseBody
    public ServerResponse getDetailById(Integer productClassId) {
        return productClassService.getDetailById(productClassId);
    }

    /**
     * 删除排产品类
     * @param id 排产品类 ID
     * @return 是否删除成功
     */
    @RequestMapping("deleteProductClass")
    @ResponseBody
    public ServerResponse deleteProductClass(Integer id) {
        return productClassService.deleteProductClass(id);
    }

    /**
     * 删除详情
     * @param id 详情 ID
     * @return
     */
    @RequestMapping("deleteDetail")
    @ResponseBody
    public ServerResponse deleteDetail(Integer id)
    {
        return productClassService.deleteDetail(id);
    }

    /**
     * 更新排产品类
     * @param session session 对象
     * @param productClass 排产品类信息
     * @return 是否更新成功
     */
    @RequestMapping("updateProductClass")
    @ResponseBody
    public ServerResponse updateProductClass(HttpSession session, ProductClass productClass) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productClassService.updateProductClass(user, productClass);
    }

    /**
     * 更新详情
     * @param session session 对象
     * @param productClassEfficiency 详情信息
     * @return
     */
    @RequestMapping("updateDetail")
    @ResponseBody
    public ServerResponse updateDetail(HttpSession session, ProductClassEfficiency productClassEfficiency) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productClassService.updateDetail(user, productClassEfficiency);
    }

    /**
     * 添加排产品类
     * @param session session 对象
     * @param productClass 排产品类信息
     * @return 是否更新成功
     */
    @RequestMapping("insertProductClass")
    @ResponseBody
    public ServerResponse insertProductClass(HttpSession session, ProductClass productClass) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productClassService.insertProductClass(user, productClass);
    }

    /**
     * 添加详情
     * @param session session 对象
     * @param productClassEfficiency 详情信息
     * @return
     */
    @RequestMapping("insertDetail")
    @ResponseBody
    public ServerResponse insertDetail(HttpSession session, ProductClassEfficiency productClassEfficiency) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productClassService.insertDetail(user, productClassEfficiency);
    }

}
