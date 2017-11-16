package com.hz.sevrlet.study.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**学习设置session
 * Servlet Filter implementation class sessionFilter
 */
public class sessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public sessionFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/** 学习session
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 如果不存在 session 会话，则创建一个 session 对象
		HttpServletRequest req=(HttpServletRequest)request;
        HttpSession session = req.getSession(true);
        
        //对应sessionId访问数
        Integer visitCount = new Integer(0);
        // 检查网页上是否有新的访问者
        if (session.isNew()){
            String userID = new String("RunoobHz");
            
        	System.out.println("new user regist:"+userID);
             session.setAttribute("userID", userID);
        } else {
             visitCount = (Integer)session.getAttribute("visitCount");
             visitCount = visitCount + 1;
        }
        session.setAttribute("visitCount",  visitCount);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(this.getClass().getName()+" init");
	}

}
