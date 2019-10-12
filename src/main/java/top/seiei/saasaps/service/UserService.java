package top.seiei.saasaps.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.UserGroupMapper;
import top.seiei.saasaps.dao.UserMapper;
import top.seiei.saasaps.util.MD5Util;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserGroupMapper userGroupMapper;

    /**
     * 登录接口
     * @param userCode 用户编码
     * @param pw 密码
     * @return 是否登录成功
     */
    public ServerResponse<User> login(String userCode, String pw) {
        if (StringUtils.isBlank(userCode) || StringUtils.isBlank(pw)) {
            return ServerResponse.createdByError("登陆信息错误");
        }
        if (userMapper.selectByUserCode(userCode) == 0) {
            return ServerResponse.createdByError("该用户不存在");
        }
        pw = MD5Util.MD5EncodeUtf8(pw);
        User user =userMapper.selectByUserCodeAndPW(userCode, pw);
        if (user == null) {
            return ServerResponse.createdByError("登陆信息错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createdBySuccess("登陆成功", user);
    }

    /**
     * 添加用户接口
     * @param admin 管理员
     * @param user 添加用户的信息
     * @return 是否添加成功
     */
    public ServerResponse addUser(User admin, User user) {
        if (admin.getRole().compareTo(Const.Role.ROLE_ADMIN) != 0) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        if (StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getUsercode()) || StringUtils.isBlank(user.getUsername()) || user.getUsersGroupId() == null) {
            return ServerResponse.createdByError("用户账号编码，用户密码，用户名称, 用户组别不能为空");
        }
        if (userMapper.selectByUserCode(user.getUsercode()) != 0) {
            return ServerResponse.createdByError("该用户账号编码已存在");
        }
        if (userGroupMapper.selectByPrimaryKey(user.getUsersGroupId()) == null) {
            return ServerResponse.createdByError("该用户组别不存在");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setUpdateUserId(admin.getId());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setId(null);
        int result = userMapper.insertSelective(user);
        if (result == 0) {
            return ServerResponse.createdByError("注册失败");
        }
        return ServerResponse.createdBySuccess("注册成功", user.getId());
    }

    /**
     * 	删除用户
     * @param admin 管理员
     * @param userId 用户 ID
     * @return 是否删除成功
     */
    public ServerResponse deleteUser(User admin, Integer userId) {
        if (admin.getRole() != Const.Role.ROLE_ADMIN) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createdByError("该删除用户不在");
        }
        int result = userMapper.deleteByPrimaryKey(userId);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 根据用户组别获取用户列表
     * @param userGroupId 用户组别 ID
     * @return
     */
    public ServerResponse selectByUserGroupId(Integer userGroupId) {
        List<User> userList = userMapper.selectByUserGroupId(userGroupId);
        return ServerResponse.createdBySuccess(userList);
    }

    /**
     * 修改用户资料
     * @param currentUser 当前操作用户
     * @param usermsg 修改用户信息
     * @return 是否成功
     */
    public ServerResponse updateUser(User currentUser, User usermsg) {
        boolean isAdmin = currentUser.getRole().compareTo(Const.Role.ROLE_ADMIN) == 0;
        User user = userMapper.selectByPrimaryKey(usermsg.getId());
        if (user == null) {
            return ServerResponse.createdByError("该用户不存在");
        }
        if (!isAdmin && currentUser.getId() != usermsg.getId()) {
            return ServerResponse.createdByError("该用户没有权限");
        }
        User updateUser = new User();
        updateUser.setUsername(usermsg.getUsername());
        updateUser.setUsercode(usermsg.getUsercode());
        updateUser.setEmail(usermsg.getEmail());
        updateUser.setPhone(usermsg.getPhone());
        updateUser.setUpdateUserId(0);
        updateUser.setId(usermsg.getId());
        updateUser.setUpdateUserId(currentUser.getId());
        updateUser.setUpdateTime(new Date());

        // 某些信息只能由管理员修改
        if (isAdmin) {
            updateUser.setRole(usermsg.getRole());
            updateUser.setUsersGroupId(usermsg.getUpdateUserId());
        } else {
            updateUser.setRole(currentUser.getRole());
            updateUser.setUsersGroupId(currentUser.getUpdateUserId());
        }
        if (StringUtils.isNotBlank(usermsg.getPassword())){
            updateUser.setPassword(MD5Util.MD5EncodeUtf8(usermsg.getPassword()));
        }
        int result = userMapper.updateByPrimaryKeySelective(updateUser);

        if (result == 0) {
            return ServerResponse.createdByError("修改失败");
        }
        return ServerResponse.createdBySuccessMessage("修改成功");
    }
}
