package guava.limit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

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
        Enumeration parameterNames=req.getParameterNames();
        //枚举型 获取每个参数名称  根据参数名称获取值
        while(parameterNames.hasMoreElements()) {
            String paramName = (String)parameterNames.nextElement();
            String parameterValue=req.getParameter(paramName);
            System.out.println(paramName+":"+parameterValue);
        }
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("我接收到了");

    }
}
