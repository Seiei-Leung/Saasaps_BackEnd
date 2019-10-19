package top.seiei.saasaps.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.SummaryOfProductionPlanningDetail;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductionPlanningDetailMapper;
import top.seiei.saasaps.dao.SummaryOfProductionPlanningDetailMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("summayOfProductionPlanningService")
public class SummaryOfProductionPlanningDetailService {

    @Resource
    private SummaryOfProductionPlanningDetailMapper summaryOfProductionPlanningDetailMapper;

    @Resource
    private ProductionPlanningDetailMapper productionPlanningDetailMapper;

    /**
     * 获取所有单号
     * @return
     */
    public ServerResponse getAll(Integer pageIndex, Integer pageSize) {
        // 使用 pageHepler 规则
        // 1、使用 pageStart
        PageHelper.startPage(pageIndex, pageSize);
        List<SummaryOfProductionPlanningDetail> summaryOfProductionPlanningDetailList = summaryOfProductionPlanningDetailMapper.selectAll();
        PageInfo pageResult = new PageInfo(summaryOfProductionPlanningDetailList);
        return ServerResponse.createdBySuccess(pageResult);
    }

    /**
     * 更新数据
     * @param user 用户信息
     * @param id 主键
     * @param clientname 客户名
     * @param season 季节
     * @return
     */
    public ServerResponse update(User user, Integer id, String clientname, String season) {
        SummaryOfProductionPlanningDetail summaryOfProductionPlanningDetail = summaryOfProductionPlanningDetailMapper.selectByPrimaryKey(id);
        if (summaryOfProductionPlanningDetail == null) {
            return ServerResponse.createdByError("没有该相应信息");
        }
        summaryOfProductionPlanningDetail.setUpdateTime(new Date());
        summaryOfProductionPlanningDetail.setClientname(clientname);
        summaryOfProductionPlanningDetail.setSeason(season);
        summaryOfProductionPlanningDetail.setUpdateUserId(user.getId());
        int result = summaryOfProductionPlanningDetailMapper.updateByPrimaryKeySelective(summaryOfProductionPlanningDetail);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 删除走货一览表
     * @param id 主键
     * @return
     */
    public ServerResponse delete(Integer id) {
        SummaryOfProductionPlanningDetail summaryOfProductionPlanningDetail = summaryOfProductionPlanningDetailMapper.selectByPrimaryKey(id);
        if (summaryOfProductionPlanningDetail == null) {
            return ServerResponse.createdByError("没有该相应信息");
        }
        int result = summaryOfProductionPlanningDetailMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        productionPlanningDetailMapper.deleteBySummaryId(id);
        return ServerResponse.createdBySuccessMessage("删除成功");
    }
}
