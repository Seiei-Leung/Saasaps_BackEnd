package top.seiei.saasaps.service;

import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.ColorSetting;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ColorSettingMapper;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ColorSettingService {

    @Resource
    private ColorSettingMapper colorSettingMapper;

    /**
     * 根据用户ID 获取颜色样式
     * @param user 用户信息
     * @return
     */
    public ServerResponse getByUserId(User user) {
        ColorSetting colorSetting = colorSettingMapper.selectByUserId(user.getId());
        if (colorSetting == null) {
            colorSetting = colorSettingMapper.selectDefault();
        }
        return ServerResponse.createdBySuccess(colorSetting);
    }

    /**
     * 更新用户的颜色设置
     * @param user 用户信息
     * @param colorSetting 颜色设置
     * @return
     */
    public ServerResponse update(User user, ColorSetting colorSetting) {
        Date now = new Date();
        ColorSetting colorSettingTemp = colorSettingMapper.selectByUserId(user.getId());
        // 新增颜色设置
        if (colorSettingTemp == null) {
            colorSetting.setUpdateTime(now);
            colorSetting.setCreateTime(now);
            colorSetting.setUpdateUserId(user.getId());
            colorSetting.setUserId(user.getId());
            colorSettingMapper.insertSelective(colorSetting);
            return ServerResponse.createdBySuccess("更新成功");
        }
        colorSetting.setId(colorSettingTemp.getId());
        colorSetting.setUpdateUserId(user.getId());
        colorSetting.setUpdateTime(now);
        int result = colorSettingMapper.updateByPrimaryKeySelective(colorSetting);
        return ServerResponse.createdBySuccessMessage("更新成功");

    }
}
