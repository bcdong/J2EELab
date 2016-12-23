package nju.course.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Mr.Zero on 2016/12/20.
 */
public class CharSetFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
