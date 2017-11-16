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

/**ѧϰ����session
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

	/** ѧϰsession
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ��������� session �Ự���򴴽�һ�� session ����
		HttpServletRequest req=(HttpServletRequest)request;
        HttpSession session = req.getSession(true);
        
        //��ӦsessionId������
        Integer visitCount = new Integer(0);
        // �����ҳ���Ƿ����µķ�����
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
