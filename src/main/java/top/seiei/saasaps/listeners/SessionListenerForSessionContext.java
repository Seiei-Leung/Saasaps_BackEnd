package top.seiei.saasaps.listeners;

import top.seiei.saasaps.pv.AppSessionContext;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerForSessionContext implements HttpSessionListener {

    /**
     * session 新增的监听逻辑
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        AppSessionContext.addSession(se.getSession());
    }

    /**
     * session 摧毁的监听逻辑
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        AppSessionContext.delSession(se.getSession());
    }
}
