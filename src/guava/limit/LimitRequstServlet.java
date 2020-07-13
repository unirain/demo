package guava.limit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/********************************************************************************
 *
 * Title: 限流请求
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/6/23
 *
 *******************************************************************************/
public class LimitRequstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("我来了");
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("我接收到了");

    }
}
