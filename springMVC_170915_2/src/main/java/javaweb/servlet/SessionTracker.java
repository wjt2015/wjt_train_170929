/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.servlet;

import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jintao.wang Date: 17-9-20 Time: 下午8:15
 */
public class SessionTracker extends HttpServlet {

    private static int visitorId;

    private static final String VISIT_COUNT_STRING = "visitCount";

    private static final String VISIT_ID_STRING = "visitId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet() start:");
        getSessionInfo(req, resp);
        System.out.println("doGet() finish!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost() start:");
        getSessionInfo(req, resp);
        System.out.println("doPost() finish!!");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        final int i = visitorId = 1;
    }

    private void trackSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);
        String title = "菜鸟教程";
        int visitCount = 0;

        if (httpSession == null) {
            httpSession = req.getSession(true);
            httpSession.setMaxInactiveInterval(100);
            httpSession.setAttribute(VISIT_COUNT_STRING, new Integer(0));
            httpSession.setAttribute(VISIT_ID_STRING, new Integer(visitorId++));
        }

        DateTime createTime = new DateTime(httpSession.getCreationTime());
        DateTime lastAccessTime = new DateTime(httpSession.getLastAccessedTime());

        if (httpSession.getAttribute(VISIT_COUNT_STRING) == null) {
            httpSession.setAttribute(VISIT_COUNT_STRING, new Integer(0));
        }
        if (httpSession.getAttribute(VISIT_ID_STRING) == null) {
            httpSession.setAttribute(VISIT_ID_STRING, new Integer(visitorId++));
        }

        visitCount = (Integer) httpSession.getAttribute(VISIT_COUNT_STRING);
        visitCount++;
        visitorId = (Integer) httpSession.getAttribute(VISIT_ID_STRING);
        httpSession.setAttribute(VISIT_COUNT_STRING, new Integer(visitCount));
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        String pattern = "yyyy-MM-dd HH-mm-ss";
        String docType = "<!DOCTYPE html>\n";
        printWriter.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
                + "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
                + "<h2 align=\"center\">Session 信息</h2>\n" + "<table border=\"1\" align=\"center\">\n"
                + "<tr bgcolor=\"#949494\">\n" + "  <th>Session 信息</th><th>值</th></tr>\n" + "<tr>\n"
                + "  <td>sessionId</td>\n" + "  <td>" + httpSession.getId() + "</td></tr>\n" + "<tr>\n"
                + "  <td>创建时间</td>\n" + "  <td>" + createTime.toString(pattern) + "  </td></tr>\n" + "<tr>\n"
                + "  <td>最后访问时间</td>\n" + "  <td>" + lastAccessTime.toString(pattern) + "  </td></tr>\n" + "<tr>\n"
                + "  <td>visitorId</td>\n" + "  <td>" + visitorId + "  </td></tr>\n" + "<tr>\n" + "  <td>访问统计：</td>\n"
                + "  <td>" + visitCount + "</td></tr>\n" + "</table>\n" + "</body></html>");

    }

    private void getSessionInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);
        String title = "菜鸟教程";
        int visitCount = 0;
        Integer t = 0;

        if (httpSession == null) {
            httpSession = req.getSession(true);
            httpSession.setMaxInactiveInterval(100);
            httpSession.setAttribute("visitCount", new Integer(0));
        }

        DateTime createTime = new DateTime(httpSession.getCreationTime());
        DateTime lastAccessTime = new DateTime(httpSession.getLastAccessedTime());

        if (httpSession.getAttribute("visitCount") == null) {
            httpSession.setAttribute("visitCount", new Integer(0));
        }
        if (httpSession.getAttribute("visitorId") == null) {
            httpSession.setAttribute("visitorId", new Integer(visitorId++));
        }

        if (httpSession.isNew()) {
            httpSession.setAttribute("visitorId", new Integer(visitorId++));
        } else {
            visitCount = (Integer) httpSession.getAttribute("visitCount");
            visitCount++;
            visitorId = (Integer) httpSession.getAttribute("visitorId");
        }
        httpSession.setAttribute("visitCount", new Integer(visitCount));
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        String pattern = "yyyy-MM-dd HH-mm-ss";
        String docType = "<!DOCTYPE html>\n";
        printWriter.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
                + "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
                + "<h2 align=\"center\">Session 信息</h2>\n" + "<table border=\"1\" align=\"center\">\n"
                + "<tr bgcolor=\"#949494\">\n" + "  <th>Session 信息</th><th>值</th></tr>\n" + "<tr>\n"
                + "  <td>sessionId</td>\n" + "  <td>" + httpSession.getId() + "</td></tr>\n" + "<tr>\n"
                + "  <td>创建时间</td>\n" + "  <td>" + createTime.toString(pattern) + "  </td></tr>\n" + "<tr>\n"
                + "  <td>最后访问时间</td>\n" + "  <td>" + lastAccessTime.toString(pattern) + "  </td></tr>\n" + "<tr>\n"
                + "  <td>visitorId</td>\n" + "  <td>" + visitorId + "  </td></tr>\n" + "<tr>\n" + "  <td>访问统计：</td>\n"
                + "  <td>" + visitCount + "</td></tr>\n" + "</table>\n" + "</body></html>");

    }
}
