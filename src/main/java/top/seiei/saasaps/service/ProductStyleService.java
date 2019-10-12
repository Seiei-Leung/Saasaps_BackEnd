package top.seiei.saasaps.service;

import org.springframework.stereotype.Service;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductStyleMapper;

import javax.annotation.Resource;

@Service("productStyleService")
public class ProductStyleService {

    @Resource
    private ProductStyleMapper productStyleMapper;

    /**
     * 获取所有产品款式
     * @return 所有产品款式
     */
    public ServerResponse getAll() {
        return ServerResponse.createdBySuccess(productStyleMapper.selectAll());
    }
}
