package top.seiei.saasaps.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.DataBoardService;
import top.seiei.saasaps.service.UserService;
import top.seiei.saasaps.util.DebugUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/api/dataBoard/")
public class DataBoardController {

    @Resource
    private UserService userService;

    @Resource
    private DataBoardService dataBoardService;

    /**
     * 获取数据看板数据
     * @return
     */
    @RequestMapping("getAll")
    @ResponseBody
    public ServerResponse getAll(long searchTime) {
        return dataBoardService.getAll(searchTime);
    }

    /**
     * 排产器的 “接单分析”，获取所有生产线的某时间段内的空闲产能
     * @param session session 对象
     * @param searchTimeList 查询时间 “年-月” 列表
     * @return
     */
    @RequestMapping(value = "getAllByTime", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getAllByTime(HttpSession session, @RequestBody List<String> searchTimeList) {
        User user = DebugUtil.getUserBySession(session, userService);
        try {
            return dataBoardService.getAllByTime(user.getId(), searchTimeList);
        } catch (ParseException e) {
            e.printStackTrace();
            return ServerResponse.createdByError("服务器发生错误");
        }
    }
}
