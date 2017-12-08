package com.hz.springMVC;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.mvc.Controller;  

/**ԭʼ�ģ�����ע���helloWorld
 * 
 * @author 50448
 *
 */
public class HelloBeanMVC implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("helloIndex");
		mv.addObject("message", "������ԭ����bean���hello world");
		return mv;
	}
 
}
