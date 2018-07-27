package springMavenPluto.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.portlet.multipart.MultipartActionRequest;

import com.alibaba.fastjson.JSON;

import springMavenPluto.View.Result;
import springMavenPluto.service.mobileBookApi;


@Controller("firstSSMPluto")
@RequestMapping("VIEW")
public class SSMPlutoControllerView {
	private String UPLOAD_DIRECTORY="images";
	@Value("${SSMPluto.viewPage}")
	private String viewPage;

	@Value("${common.failPage}")
	private String failPage;

	@Resource(name = "mobileBookApi")
	private mobileBookApi mba;

	// 由于freemaker不知如何加载jsp标签。
	@RenderMapping
	public String listPageData(RenderRequest request, Model model) {
		// TODO 登陆，分页
		Integer userId = 1;

		Result result = mba.getMobileByUserId(userId);

		if (result.getMap().isEmpty() || result.getCode() != 0) {
			model.addAttribute("fail_message", "data not found");
			return failPage;
		}

		Map map = result.getMap();
		@SuppressWarnings("rawtypes")
		List resultList = (List) map.get("resultList");

		model.addAttribute("resultList", resultList);
		PortletSession ps = request.getPortletSession();
		model.addAttribute("sessionId", ps.getId());
		/*
		 * System.out.println("sessionId:"+ps.getId());
		 * System.out.println(resultList);
		 */
		System.out.println(request.getContextPath()+":==listPageData==");
		return viewPage;
	}

	@RenderMapping(params = "action=mbDetail")
	public String detailPage(RenderRequest request, Model model,
			@RequestParam("id") Integer id) {
		Result result = mba.getMobileById(id);
		Map map = result.getMap();
		model.addAttribute("name", map.get("name"));
		model.addAttribute("number", map.get("number"));
		model.addAttribute("userId", map.get("userId"));
		model.addAttribute("mbId", map.get("id"));
		PortletSession ps = request.getPortletSession();
		model.addAttribute("sessionId", ps.getId());
		/* System.out.println("sessionId:"+ps.getId()); */
		if (result.getMap().isEmpty() || result.getCode() != 0) {
			model.addAttribute("fail_message", "data not found");
			return failPage;
		}

		System.out.println(request.getContextPath()+":==detail==");
		return "ssmDetail";
	}

	/*
	 * @RenderMapping public String listPage(){ return viewPage; }
	 * 
	 * @ResourceMapping("mbListData") public void listPageData(ResourceResponse
	 * resp) throws IOException{ Integer userId=1; Result result
	 * =mba.getMobileByUserId(userId); PrintWriter Pw=resp.getWriter();
	 * Pw.write(JSON.toJSONString(result)); return; }
	 * 
	 * @RenderMapping("mbDetailPage") public String detailPage(Model
	 * model,@RequestParam("mbId")Integer id){ model.addAttribute("mbId",id);
	 * return "detailPage"; }
	 * 
	 * @ResourceMapping("mbDetailData") public void detailData(ResourceResponse
	 * resp,@RequestParam("mbId")Integer id) throws IOException{ Result
	 * result=mba.getMobileById(id); PrintWriter Pw=resp.getWriter();
	 * Pw.write(JSON.toJSONString(result)); return; }
	 */

	@RenderMapping(params = "action=mbAdd")
	public String addPage(RenderRequest request) {
		System.out.println(request.getContextPath()+":==add==");
		return "ssmAdd";
	}

	@ActionMapping(params = "action=call")
	public void defaultAction(ActionRequest request,
            ActionResponse response) {
		
		System.out.println("action");
	}

	@ResourceMapping("mbAdd")
	// @ResponseBody
	public void listAdd(ResourceRequest req, ResourceResponse resp,
			@RequestParam("name") String name,
			@RequestParam("number") String number) throws IOException {
		// TODO 登陆
		System.out.println("listAdd:name:" + name + " number:" + number);
		Integer userId = 1;
		Result result = mba.addMobileByUserId(userId, name, number);
		PrintWriter pw = resp.getWriter();
		pw.write(JSON.toJSONString(result));
		return;
	}

	@ResourceMapping("mbEdit")
	public void listEdit(ResourceRequest req, ResourceResponse resp,
			@RequestParam("mbId") Integer id,
			@RequestParam("name") String name,
			@RequestParam("number") String number) throws IOException {

		Result result = mba.updateMobileById(id, name, number);
		PrintWriter Pw = resp.getWriter();
		Pw.write(JSON.toJSONString(result));
	}

	@ResourceMapping("mbDelete")
	public void listDelete(ResourceRequest req, ResourceResponse resp,
			@RequestParam("mbId") Integer id) throws IOException {
		Result result = mba.removeMobileByUserId(id);
		PrintWriter Pw = resp.getWriter();
		Pw.write(JSON.toJSONString(result));
	}


	@ResourceMapping("mbHeadImgUpload")
	public void mbHeadImgUpload(
			//@RequestParam("file")MultipartFile file,
			ResourceRequest req,
			ResourceResponse resp) throws IOException {
		
		System.out.println("upload:"+req.getContentType());
		String filePath=req.getPortletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
		File path=new File(filePath);
		if (!path.exists()){
			path.mkdir();
		}
		
		byte[] b=IOUtils.toByteArray(req.getPortletInputStream());
		
		File reqTxt=new File(filePath+File.separator+"req.txt");
		FileOutputStream fout=new FileOutputStream(reqTxt);
		fout.write(b);
		fout.close();
		
		Result result=new Result(0,"success",null);
		
		PrintWriter Pw = resp.getWriter();
		Pw.write(JSON.toJSONString(result));
		
	}
}
