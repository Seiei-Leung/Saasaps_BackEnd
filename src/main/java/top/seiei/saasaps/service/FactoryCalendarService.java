package top.seiei.saasaps.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.jmx.Server;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.seiei.saasaps.bean.Festival;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.bean.WorkingDateSetting;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.FestivalMapper;
import top.seiei.saasaps.dao.WorkingDateSettingMapper;
import top.seiei.saasaps.util.DateUtil;
import top.seiei.saasaps.vo.WorkingDateSettingVO;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("workingDateSettingService")
public class FactoryCalendarService {

    @Resource
    private WorkingDateSettingMapper workingDateSettingMapper;

    @Resource
    private FestivalMapper festivalMapper;

    /**
     * 获取工作日历的主要内容
     * @return 工作日历的主要内容
     */
    public ServerResponse getFactoryCalendarList() {
        List<WorkingDateSetting> workingDateSettingList = workingDateSettingMapper.selectAll();
        if (workingDateSettingList == null) {
            return ServerResponse.createdByError("查无该信息");
        }
        return ServerResponse.createdBySuccess(workingDateSettingList);
    }

    /**
     * 添加工作日历
     * @param admin 操作用户信息
     * @param workingDateSetting 添加的工作日历信息
     * @return 是否成功
     */
    public ServerResponse addFactoryCalendar(User admin, WorkingDateSetting workingDateSetting) {
        if (workingDateSetting.getEffectiveYear() == null) {
            return ServerResponse.createdByError("年度不能为空");
        }
        WorkingDateSetting workingDateSettingTemp = workingDateSettingMapper.selectByYear(workingDateSetting.getEffectiveYear());
        if (workingDateSettingTemp != null) {
            return ServerResponse.createdByError("该年度的工厂日历已经定义");
        }
        workingDateSetting.setUpdateUserId(admin.getId());
        workingDateSetting.setCreateTime(new Date());
        workingDateSetting.setUpdateTime(new Date());
        int result = workingDateSettingMapper.insertSelective(workingDateSetting);
        if (result == 0) {
            return ServerResponse.createdByError("添加失败");
        }
        return ServerResponse.createdBySuccess("添加成功", workingDateSetting.getId());
    }

    /**
     * 更新工作日历
     * @param admin 操作用户信息
     * @param workingDateSetting 更新的工作日历信息
     * @return 是否成功
     */
    public ServerResponse updateFactoryCalendar(User admin, WorkingDateSetting workingDateSetting) {
        if (workingDateSettingMapper.selectByPrimaryKey(workingDateSetting.getId()) == null) {
            return ServerResponse.createdByError("该工作日历不存在");
        }
        if (workingDateSetting.getEffectiveYear() == null) {
            return ServerResponse.createdByError("年度不能为空");
        }
        WorkingDateSetting workingDateSettingTemp = workingDateSettingMapper.selectByYear(workingDateSetting.getEffectiveYear());
        if (workingDateSettingTemp != null && workingDateSettingTemp.getId() != workingDateSetting.getId()) {
            return ServerResponse.createdByError("该年度的工厂日历已经定义");
        }
        workingDateSetting.setUpdateUserId(admin.getId());
        workingDateSetting.setUpdateTime(new Date());
        int result = workingDateSettingMapper.updateByPrimaryKeySelective(workingDateSetting);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 删除工作日历
     * @param id 工作日历的 ID
     * @return 是否成功
     */
    public ServerResponse deleteFactoryCalendar(Integer id) {
        WorkingDateSetting workingDateSetting = workingDateSettingMapper.selectByPrimaryKey(id);
        if (workingDateSetting == null) {
            return ServerResponse.createdByError("查无该日期信息");
        }
        Integer result = workingDateSettingMapper.deleteByPrimaryKey(workingDateSetting.getId());
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        Integer length = festivalMapper.selectByWorkingSettingId(id).size();
        Integer result2 = festivalMapper.deleteByWorkingSettingId(id);
        if (result2 == 0 && length != 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 获取所有节假日
     * @param id 工作日历的主 ID
     * @return 所有节假日
     */
    public ServerResponse getFestivalById(Integer id) {
        List<Festival> festivalList = festivalMapper.selectByWorkingSettingId(id);
        if (festivalList == null) {
            return ServerResponse.createdByError("查无该信息");
        }
        return ServerResponse.createdBySuccess(festivalList);
    }

    /**
     * 添加节日
     * @param user 用户信息
     * @param factoryCalendarId 工厂日历 id
     * @param festivalName 节日名称
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public ServerResponse addFestival(User user, Integer factoryCalendarId, String festivalName, Long beginDate, Long endDate) {
        WorkingDateSetting workingDateSetting = workingDateSettingMapper.selectByPrimaryKey(factoryCalendarId);
        if (workingDateSetting == null) {
            return ServerResponse.createdByError("没有对应的工厂日历信息");
        }
        if (
                StringUtils.isBlank(festivalName) ||
                beginDate == null ||
                endDate == null
        ) {
            return ServerResponse.createdByError("参数错误");
        }
        if (beginDate > endDate) {
            return ServerResponse.createdByError("结束日期不能早于起始日期");
        }
        Festival festival = new Festival();
        festival.setFestivalName(festivalName);
        festival.setBeginDate(DateUtil.zeroSetting(new Date(beginDate)));
        festival.setEndDate(DateUtil.zeroSetting(new Date(endDate)));
        festival.setUpdateUserId(user.getId());
        festival.setCreateTime(new Date());
        festival.setUpdateTime(new Date());
        festival.setFactoryCalendarId(factoryCalendarId);
        int result= festivalMapper.insertSelective(festival);
        if (result == 0) {
            return ServerResponse.createdByError("新增失败");
        }
        return ServerResponse.createdBySuccess("添加成功", festival.getId());
    }

    /**
     * 修改节假日
     * @param user session 对象
     * @param id 节日 id
     * @param festivalName 节日名称
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public ServerResponse updateFestival(User user, Integer id, String festivalName, Long beginDate, Long endDate) {
        if (
                StringUtils.isBlank(festivalName) ||
                        beginDate == null ||
                        endDate == null
        ) {
            return ServerResponse.createdByError("参数错误");
        }
        if (beginDate > endDate) {
            return ServerResponse.createdByError("结束日期不能早于起始日期");
        }
        Festival festival1Temp = festivalMapper.selectByPrimaryKey(id);
        if (festival1Temp == null) {
            return ServerResponse.createdByError("查无该信息");
        }
        festival1Temp.setFestivalName(festivalName);
        festival1Temp.setBeginDate(DateUtil.zeroSetting(new Date(beginDate)));
        festival1Temp.setEndDate(DateUtil.zeroSetting(new Date(endDate)));
        festival1Temp.setUpdateUserId(user.getId());
        festival1Temp.setUpdateTime(new Date());
        int result = festivalMapper.updateByPrimaryKeySelective(festival1Temp);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return  ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 删除节日信息
     * @param id 节日 ID
     * @return 是否成功
     */
    public ServerResponse deleteFestival(Integer id) {
        Festival festival = festivalMapper.selectByPrimaryKey(id);
        if (festival == null) {
            return ServerResponse.createdByError("查无该节日信息");
        }
        Integer result = festivalMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }
}
