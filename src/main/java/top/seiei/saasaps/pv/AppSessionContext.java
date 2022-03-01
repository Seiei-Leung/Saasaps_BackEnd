package top.seiei.saasaps.pv;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 用于存储 Session 的公共变量
 */
public class AppSessionContext {

    // 因为需要定义为其它地方也能访问到的公共变量，所以就需要使用静态修饰符定义为静态变量，在编译时由系统分配一块内存空间，直到程序停止运行才会释放。那么就是说该类的所有对象都会共享这块内存空间
    public static HashMap<String, HttpSession> mapOfSession = new HashMap();

    /**
     * 添加 session
     * @param session session
     */
    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            mapOfSession.put(session.getId(), session);
        }
    }

    /**
     * 移除 session
     * @param session session
     */
    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            mapOfSession.remove(session.getId());
        }
    }
}
