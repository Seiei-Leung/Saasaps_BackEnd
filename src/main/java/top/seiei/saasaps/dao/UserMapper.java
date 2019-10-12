package top.seiei.saasaps.dao;

import org.apache.ibatis.annotations.Param;
import top.seiei.saasaps.bean.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectByUserCode(String userCode);

    User selectByUserCodeAndPW(@Param("userCode") String userCode, @Param("pw") String pw);

    List<User> selectByUserGroupId(Integer userGroupId);
}