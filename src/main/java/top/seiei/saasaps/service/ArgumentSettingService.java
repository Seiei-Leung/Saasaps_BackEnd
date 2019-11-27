package top.seiei.saasaps.service;

import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.ArgumentSetting;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ArgumentSettingMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArgumentSettingService {

    @Resource
    private ArgumentSettingMapper argumentSettingMapper;

    /**
     * 获取参数设置数据
     * @return
     */
    public ServerResponse getAll() {
        List<ArgumentSetting> argumentSettingList = argumentSettingMapper.getAll();
        if (argumentSettingList.size() != 0) {
            return ServerResponse.createdBySuccess(argumentSettingList.get(0));
        }
        return ServerResponse.createdByError("参数设置没有定义");
    }

    /**
     * 更新参数数据
     * @param user 用户信息
     * @param argumentSetting 参数数据
     * @return
     */
    public ServerResponse update(User user, ArgumentSetting argumentSetting) {
        argumentSetting.setUpdateUserId(user.getId());
        argumentSetting.setUpdateTime(new Date());
        argumentSettingMapper.updateByPrimaryKeySelective(argumentSetting);
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

}
