package guava.limit;

import com.google.common.util.concurrent.RateLimiter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/********************************************************************************
 *
 * Title: 限流过滤
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/6/23
 *
 *******************************************************************************/
public class RateLimitFilter  implements Filter  {
    private static RateLimiter rateLimiter=RateLimiter.create(1);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (rateLimiter.tryAcquire()){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            System.out.println("你被限流了！");
            ((HttpServletResponse)servletResponse).setHeader("Content-type", "text/html;charset=UTF-8");
            servletResponse.setCharacterEncoding("utf-8");
            servletResponse.getWriter().write("你被限流了！");
        }

    }

    @Override
    public void destroy() {

    }

}
