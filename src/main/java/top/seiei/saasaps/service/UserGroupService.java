package top.seiei.saasaps.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.bean.UserGroup;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.UserGroupMapper;

import javax.annotation.Resource;
import java.util.*;

@Service("userGroupService")
public class UserGroupService {

    @Resource
    private UserGroupMapper userGroupMapper;

    /**
     * 	获取所有用户组别
     * @return 用户组别列表
     */
    public ServerResponse selectAllUserGroup() {
        List<UserGroup> userGroups = userGroupMapper.selectAll();
        return ServerResponse.createdBySuccess(getLower(userGroups, 0, 0));
    }

    /**
     * 删除用户组别
     * @param id 用户组别 ID
     * @return 是否删除成功
     */
    public ServerResponse delete(Integer id) {
        UserGroup userGroup = userGroupMapper.selectByPrimaryKey(id);
        if (userGroup == null) {
            return ServerResponse.createdByError("该用户组别不存在");
        }
        int result = userGroupMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 新增用户组别
     * @param admin 管理员
     * @param userGroup 用户组别信息
     * @return 是否成功
     */
    public ServerResponse insertUserGroup(User admin, UserGroup userGroup) {
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        if (StringUtils.isBlank(userGroup.getName()) || userGroup.getParentId() == null){
            return ServerResponse.createdByError("参数错误");
        }
        if (userGroupMapper.selectByNameAndParentId(userGroup.getName(), userGroup.getParentId()) != 0) {
            return ServerResponse.createdByError("该组别已存在");
        }
        userGroup.setUpdateUserId(admin.getId());
        userGroup.setCreateTime(new Date());
        userGroup.setUpdateTime(new Date());
        userGroupMapper.insertSelective(userGroup);
        return ServerResponse.createdBySuccess("新增成功", userGroup.getId());
    }


    /**
     * 	迭代循环获取用户组别列表
     * @param syGroups 总的数据源
     * @param id 要查询的节点
     * @param lev 当前是第几层
     * @return
     */
    private List<Map<String, Object>> getLower(List<UserGroup> syGroups, int id, int lev) {
        List<Map<String, Object>> List = new ArrayList<>();
        syGroups.forEach((item) ->{
            if (item.getParentId().equals(id)) {
                int level = lev + 1;
                int nextId = item.getId();
                Map<String, Object> object = new HashMap<>();
                object.put("title", item.getName());
                object.put("expend", true);
                object.put("resource", item);
                object.put("children", getLower(syGroups, nextId, level));
                List.add(object);
            }
        });
        return List;
    }
}
