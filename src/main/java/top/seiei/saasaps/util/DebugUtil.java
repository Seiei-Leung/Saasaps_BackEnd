package top.seiei.saasaps.util;

import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * debug 用的工具
 */
public class DebugUtil {

    private static Boolean isDebug = true;

    /**
     * 从 session 中获取 User，如果是 Debug 状态则默认为 admin 账户
     * @param session session 对象
     * @param userService
     * @return
     */
    public static User getUserBySession(HttpSession session, UserService userService) {
        User user = null;
        if (!isDebug) {
            user = (User) session.getAttribute(Const.CURRENT_USER);
        } else {
            ServerResponse<User> serverResponse = userService.login("admin", "123456");
            user = serverResponse.getData();
        }
        return user;
    }
}
