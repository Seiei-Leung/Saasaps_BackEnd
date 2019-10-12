package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ProductStyleService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/productstyle/")
public class ProductStyleController {

    @Resource
    private ProductStyleService productStyleService;

    /**
     * 获取所有产品款式
     * @return 所有产品款式
     */
    @RequestMapping("getall")
    @ResponseBody
    public ServerResponse getAll() {
        return productStyleService.getAll();
    }

}
