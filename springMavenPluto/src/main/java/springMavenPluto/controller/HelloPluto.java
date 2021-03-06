package springMavenPluto.controller;

import javax.portlet.ActionParameters;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.Controller;

public class HelloPluto implements Controller {
	
	public String viewName;
	
	//private ActionParameters actparams;
	
	public void setViewName(String viewName){
		this.viewName=viewName;
	}
	
	public void handleActionRequest(ActionRequest arg0, ActionResponse arg1)
			throws Exception {
		
		//String message=actparams.getValue("message");
		//这里有问题呀。portlet.api  2.0就methodnotfound 3.0就abstracterror.
		ActionParameters ap=arg0.getActionParameters();
		System.out.println("ActionParameters:"+ap.toString());
		String message=ap.getValue("message");
		PortletSession ps=arg0.getPortletSession();
		
		ps.setAttribute("message", message);
	}

	public ModelAndView handleRenderRequest(RenderRequest arg0,
			RenderResponse arg1) throws Exception {
		
		ModelAndView mv=new ModelAndView();
		PortletSession ps=arg0.getPortletSession();
	    String message=(String) ps.getAttribute("message");
	    
	    if (null==message){
	    	message="纯净的pure";
	    	ps.setAttribute("message", message);
	    }
		
		mv.setViewName(viewName);
		mv.addObject("mode", arg0.getPortletMode());
		mv.addObject("message", message);
		arg1.setContentType("text/html;charset=utf-8");
		System.out.println(arg0.getPortletMode());
		return mv;
	}

}
