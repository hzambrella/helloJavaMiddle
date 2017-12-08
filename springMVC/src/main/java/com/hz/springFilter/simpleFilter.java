package com.hz.springFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**学习springMVC 拦截器
 * 
 * @author 50448
 *
 */
@Repository
public class SimpleFilter extends HandlerInterceptorAdapter {// 此处一般继承HandlerInterceptorAdapter适配器即可
	//计时
	 private NamedThreadLocal<Long>  startTimeThreadLocal =   
			 new NamedThreadLocal<Long>("StopWatch-StartTime");  
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("===========HandlerInterceptor1 preHandle");
		long beginTime = System.currentTimeMillis();//1、开始时间  
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
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
		
		 long endTime = System.currentTimeMillis();//2、结束时间  
	        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
	        long consumeTime = endTime - beginTime;//3、消耗的时间 
	    	System.out.println("{"+request.getMethod()+"}"+"["+response.getStatus()+"]"+request.getRequestURI()+"("+consumeTime+"ms)");
	}
}