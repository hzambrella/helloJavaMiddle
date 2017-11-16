package com.hz.springMVC;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**springMVC helloWorld 
 * 2017-10-31
 * 在springMVC-servlet 注入过滤器
 *（通过"org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"的interceptors属性）
 * 会导致404
 * 
 * @author 50448
 *
 */
@Controller
@RequestMapping("/hello")
public class HelloController{
	@RequestMapping(method = RequestMethod.GET)
	/**hello world 注解版
	 * 
	 * @param model
	 * @return
	 */
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "这是注解实现的helloWorld");

	      return "hello";
	   }
	
	/**窄化请求映射
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/narrow",method = RequestMethod.GET)
	   public String helloUser(ModelMap model) {
	      model.addAttribute("message", "窄化请求映射");

	      return "hello";
	   }
	
	/**多种映射指向一个controller,模板请求 用@PathVariable获得模板数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/diff","/different","/diffe/{userId}"},method = RequestMethod.GET)
	   public String diffUrl(ModelMap model) {
	      model.addAttribute("message", "多种映射指向一个controller模板请求 用@PathVariable获得模板数据");

	      return "hello";
	   }
	

	@RequestMapping(value="/requestHeader",method = RequestMethod.GET)
	   public String requestHeader(ModelMap model,
			   @RequestParam(value="userId",required=false,defaultValue="1")String userId ,
			   @RequestHeader("User-Agent") String userAgent,  
		       @RequestHeader(value="Accept") String[] accepts) {
			System.out.println("userId:"+userId);
			StringBuffer message=new StringBuffer();
		
		   message.append("【@RequestHeader的用法】");
		   message.append("{"+userAgent+"}");
		   System.out.println("accepts.length:"+accepts.length);
		   for ( String accept:accepts){
			   message.append("("+accept+")");
		   }
	      model.addAttribute("message", message.toString());

	      return "hello";
	   }
	
}
