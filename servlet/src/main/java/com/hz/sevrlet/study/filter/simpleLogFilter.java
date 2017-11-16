package com.hz.sevrlet.study.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class simpleLogFilter
 */
public class simpleLogFilter implements Filter {

    /**
     * Default constructor. 
     */
    public simpleLogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println(this.getClass().getName()+" destoryed");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//console日志
		//处理前
		StringBuffer consoleLogBuf=new StringBuffer();
		HttpServletRequest HTTPrequest=(HttpServletRequest)request;
		consoleLogBuf.append("("+HTTPrequest.getMethod()+") ");
		consoleLogBuf.append(HTTPrequest.getRequestURL());
		
		//context log
		ServletContext context=request.getServletContext();
		context.log("这是 ServletContext 的log");
		//cookie
		
		//context
		/*
		ServletContext ctx=request.getServletContext();
		String test=(String) ctx.getAttribute("test");
		if (null==test||"".equals(test)){
			System.out.println(this.getClass().getName()+"context test is nill");
			ctx.setAttribute("test", "im test");
		}else{
			System.out.println(this.getClass().getName()+"context test is "+test);
		}
		*/
		//System.out.println(this.getClass().getName()+"getContextPath:__"+ctx.getContextPath());
		
		// 处理中pass the request along the filter chain
		chain.doFilter(request, response);
		//处理后
		HttpServletResponse HTTPresponse=(HttpServletResponse)response;
		consoleLogBuf.append(" ["+HTTPresponse.getStatus()+"]");
		System.out.println(consoleLogBuf.toString());
		// cookie
		Cookie testCookieEncoder=new Cookie("testEncoder", URLEncoder.encode("我是大大小小人", "UTF-8"));
		//Cookie testCookieNoEncoder=new Cookie("testNoEncoder", "tset哈哈");
		HTTPresponse.addCookie(testCookieEncoder);
		//HTTPresponse.addCookie(testCookieNoEncoder);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String testParam=fConfig.getInitParameter("testParam");
		System.out.println(fConfig.getFilterName()+" is init,param:"+testParam);
	}

}
