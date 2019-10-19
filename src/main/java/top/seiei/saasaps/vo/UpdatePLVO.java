package top.seiei.saasaps.vo;

import java.util.List;

/**
 * 用于 Post 用户生产线权限
 */
public class UpdatePLVO {
    private Integer userId;
    private List<Integer> list;
    public List<Integer> getList() {
        return list;
    }
    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
