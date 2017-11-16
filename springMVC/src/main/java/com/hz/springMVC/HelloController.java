package com.hz.springMVC;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**springMVC helloWorld 
 * 2017-10-31
 * ��springMVC-servlet ע�������
 *��ͨ��"org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"��interceptors���ԣ�
 * �ᵼ��404
 * 
 * @author 50448
 *
 */
@Controller
@RequestMapping("/hello")
public class HelloController{
	@RequestMapping(method = RequestMethod.GET)
	/**hello world ע���
	 * 
	 * @param model
	 * @return
	 */
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "����ע��ʵ�ֵ�helloWorld");

	      return "hello";
	   }
	
	/**խ������ӳ��
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/narrow",method = RequestMethod.GET)
	   public String helloUser(ModelMap model) {
	      model.addAttribute("message", "խ������ӳ��");

	      return "hello";
	   }
	
	/**����ӳ��ָ��һ��controller,ģ������ ��@PathVariable���ģ������
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/diff","/different","/diffe/{userId}"},method = RequestMethod.GET)
	   public String diffUrl(ModelMap model) {
	      model.addAttribute("message", "����ӳ��ָ��һ��controllerģ������ ��@PathVariable���ģ������");

	      return "hello";
	   }
	

	@RequestMapping(value="/requestHeader",method = RequestMethod.GET)
	   public String requestHeader(ModelMap model,
			   @RequestParam(value="userId",required=false,defaultValue="1")String userId ,
			   @RequestHeader("User-Agent") String userAgent,  
		       @RequestHeader(value="Accept") String[] accepts) {
			System.out.println("userId:"+userId);
			StringBuffer message=new StringBuffer();
		
		   message.append("��@RequestHeader���÷���");
		   message.append("{"+userAgent+"}");
		   System.out.println("accepts.length:"+accepts.length);
		   for ( String accept:accepts){
			   message.append("("+accept+")");
		   }
	      model.addAttribute("message", message.toString());

	      return "hello";
	   }
	
}
