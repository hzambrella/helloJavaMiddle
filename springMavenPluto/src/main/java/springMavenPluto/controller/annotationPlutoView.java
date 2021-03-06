package springMavenPluto.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.alibaba.fastjson.JSON;

import springMavenPluto.View.Result;

/**注解版portal
 * 
 * @author 50448
 *
 */
@Controller("annotation")
@RequestMapping("VIEW")
public class annotationPlutoView {
	@Value("${AnnotationPluto.welcomeMessage:none}")
	public String welcomeMessage;
	@Value("${AnnotationPluto.viewPage}")
	public String viewPage;
	@Value("${AnnotationPluto.editPage}")
	public String editPage;
	/*@Value("${AnnotationPluto.helpPage}")
	public String helpPage;*/

	@RenderMapping
	public String view(Model model) {
		System.out.println(welcomeMessage);
		model.addAttribute("welcome_message", welcomeMessage);
		return viewPage;
	}

	/**portal内部页面跳转
	 * 
	 * @param model
	 * @return
	 */
	@RenderMapping(params = "action=edit")
	public String edit(Model model) {
		model.addAttribute("welcome_message", welcomeMessage);
		return editPage;
	}

	/**处理ajax
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @param whatever
	 * @throws IOException 
	 */
	@ResourceMapping("firstAjaxDemo")
	@ResponseBody
	public void serveAjax(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse,
			@RequestParam("ajax_message") String ajaxMessage) throws IOException {
				System.out.println("ajax_message:"+ajaxMessage);
				String new_message=ajaxMessage+"-- ok,serve deal an ajax request";

				Map<String, Object>map= new HashMap<String,Object>();
				map.put("message", new_message);
				Result result=new Result(0, "success", map);
				String jsonResp=JSON.toJSONString(result);
				
				//注意这里json的处理
				PrintWriter write = resourceResponse.getWriter();
				write.write(jsonResp);
				//return "{\"new_message:\""+new_message+"}";
	}
}
