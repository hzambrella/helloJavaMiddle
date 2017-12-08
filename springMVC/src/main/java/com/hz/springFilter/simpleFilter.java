package com.hz.springFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**ѧϰspringMVC ������
 * 
 * @author 50448
 *
 */
@Repository
public class SimpleFilter extends HandlerInterceptorAdapter {// �˴�һ��̳�HandlerInterceptorAdapter����������
	//��ʱ
	 private NamedThreadLocal<Long>  startTimeThreadLocal =   
			 new NamedThreadLocal<Long>("StopWatch-StartTime");  
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("===========HandlerInterceptor1 preHandle");
		long beginTime = System.currentTimeMillis();//1����ʼʱ��  
        startTimeThreadLocal.set(beginTime);//�̰߳󶨱�����������ֻ�е�ǰ������߳̿ɼ���
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//System.out.println("===========HandlerInterceptor1 postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("===========HandlerInterceptor1 afterCompletion");
		
		 long endTime = System.currentTimeMillis();//2������ʱ��  
	        long beginTime = startTimeThreadLocal.get();//�õ��̰߳󶨵ľֲ���������ʼʱ�䣩  
	        long consumeTime = endTime - beginTime;//3�����ĵ�ʱ�� 
	    	System.out.println("{"+request.getMethod()+"}"+"["+response.getStatus()+"]"+request.getRequestURI()+"("+consumeTime+"ms)");
	}
}