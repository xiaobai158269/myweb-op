package cmy.myweb.op.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/30.
 */
public class DispatcherFilter extends OncePerRequestFilter {

    //需要过滤的请求地址
    private static String[] notFilterUrl = new String[]{"/getDataList","/pay"};

    //需要过滤的文件类型
    private static String[] notFilterRes = new String[]{".js", ".css", ".png", ".jpg", ".gif", ".jnlp", ".jar", ".wav",".ico"};


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String index = request.getContextPath() + "/";
        String uri = request.getRequestURI();

        boolean doFilter = true;
        if (uri.equals(index)) {
            doFilter = false;
        }
        if (doFilter) {
            uri = StringUtils.replaceOnce(uri, request.getContextPath(), "");
            for (String s : notFilterUrl) {
                if (StringUtils.startsWith(uri, s)) {
                    doFilter = false;
                    break;
                }
            }
        }

        if (doFilter) {
            uri = StringUtils.substringBeforeLast(uri,";");
            for (String s : notFilterRes) {
                if (StringUtils.endsWith(uri, s)) {
                    doFilter = false;
                    break;
                }
            }
        }

        if (doFilter) {
            response.sendRedirect(index);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
