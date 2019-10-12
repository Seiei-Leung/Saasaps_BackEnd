package top.seiei.saasaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/user/")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 	登陆接口
     * @param session session 对象
     * @param params 密码与账号
     * @return 是否登录成功
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(HttpSession session, @RequestBody Map<String, String> params) {
        ServerResponse<User> serverResponse = userService.login(params.get("usercode"), params.get("pwd"));
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
        }
        System.out.println(session.getAttribute(Const.CURRENT_USER));
        return serverResponse;
    }

    /**
     * 	登出接口
     * @param session session 对象
     * @return 是否登出成功
     */
    @RequestMapping(value = "signout", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> signout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createdBySuccessMessage("登出成功");
    }

    /**
     * 	新增用户
     * @param session session 对象
     * @param user 用户资料
     * @return 是否新增成功
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addUser(HttpSession session, @RequestBody User user) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        return userService.addUser(admin, user);
    }

    /**
     * 	删除用户
     * @param session session 对象
     * @param userid 用户 ID
     * @return 是否删除成功
     */
    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse<String> deleteUser(HttpSession session, Integer userid) {
        User admin = (User) session.getAttribute(Const.CURRENT_USER);
        return userService.deleteUser(admin, userid);
    }

    /**
     * 	根据用户组别获取用户列表
     * @param usergroupid 用户组别 ID
     * @return 响应对象
     */
    @RequestMapping("getallbyusergroupid")
    @ResponseBody
    public ServerResponse<List<User>> getAllByuserGroupId(Integer usergroupid) {
        return userService.selectByUserGroupId(usergroupid);
    }

    /**
     *	修改用户资料
     * @param session session 对象
     * @param usermsg 用户资料
     * @return 是否修改成功
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateUser(HttpSession session, @RequestBody User usermsg) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        return userService.updateUser(currentUser, usermsg);
    }
}
