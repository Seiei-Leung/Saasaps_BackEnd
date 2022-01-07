package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.bean.UserGroup;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.UserGroupService;
import top.seiei.saasaps.service.UserService;
import top.seiei.saasaps.util.DebugUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/usergroup/")
public class UserGroupController {
    @Resource
    private UserService userService;

    @Resource
    private UserGroupService userGroupService;

    /**
     * 	获取所有用户组树状图数据结构
     * @return 用户组别列表
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Map<String, Object>>> getAllUserGroup() {
        return userGroupService.selectAllUserGroup();
    }

    /**
     * 删除用户组别
     * @param session session 对象
     * @param id 用户组别 id
     * @return 是否删除成功
     */
    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse deleteUserGroup(HttpSession session, Integer id) {
        User user = DebugUtil.getUserBySession(session, userService);
        if (user.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        return userGroupService.delete(id);
    }

    /**
     * 添加用户组别
     * @param session session 对象
     * @param userGroup 用户组别信息
     * @return 是否添加成功
     */
    @RequestMapping("insert")
    @ResponseBody
    public ServerResponse insertUserGroup(HttpSession session, UserGroup userGroup) {
        User user = DebugUtil.getUserBySession(session, userService);
        return userGroupService.insertUserGroup(user, userGroup);
    }
}
