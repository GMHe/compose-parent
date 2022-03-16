package cn.compose.admin.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class PageLocalWebInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        // PageHelper.clearPage() 内部调用 LOCAL_PAGE.remove()
        PageHelper.clearPage();

    }
}