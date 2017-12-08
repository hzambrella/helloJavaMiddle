package com.hz.springMVC;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**
 * springMVC helloWorld 2017-10-31 ��springMVC-servlet ע�������
 * ��ͨ��"org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"
 * ��interceptors���ԣ� �ᵼ��404
 * 
 * @author 50448
 * 
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
	private String UPLOAD_DIRECTORY="images";
	
	@RequestMapping(method = RequestMethod.GET)
	/**hello world ע���
	 * 
	 * @param model
	 * @return
	 */
	public String printHello(ModelMap model) {
		model.addAttribute("message", "����ע��ʵ�ֵ�helloWorld");

		return "helloIndex";
	}

	/**
	 * խ������ӳ��
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/narrow", method = RequestMethod.GET)
	public String helloUser(ModelMap model) {
		model.addAttribute("message", "խ������ӳ��");

		return "hello";
	}
	
	/**��ͼƬ
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file) throws IOException {
		String fileName=(String)file.getOriginalFilename();
		InputStream input=file.getInputStream();
		byte[]ibyte=IOUtils.toByteArray(input);
		String filePath=request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
		File imgDirectory=new File(filePath);
		if (!imgDirectory.exists()){
			imgDirectory.mkdir();
		}
		
		File imgfile=new File(filePath+File.separator+fileName);
		FileOutputStream fout=new FileOutputStream(imgfile);
		fout.write(ibyte);
		fout.close();
		System.out.println(filePath+File.separator+fileName);
		return request.getContextPath()+" file upload:"+file.getName();
	}

	/**
	 * ����ӳ��ָ��һ��controller,ģ������ ��@PathVariable���ģ������
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/diff", "/different", "/diffe/{userId}" }, method = RequestMethod.GET)
	public String diffUrl(ModelMap model) {
		model.addAttribute("message",
				"����ӳ��ָ��һ��controllerģ������ ��@PathVariable���ģ������");

		return "hello";
	}

	@RequestMapping(value = "/requestHeader", method = RequestMethod.GET)
	public String requestHeader(
			ModelMap model,
			@RequestParam(value = "userId", required = false, defaultValue = "1") String userId,
			@RequestHeader("User-Agent") String userAgent,
			@RequestHeader(value = "Accept") String[] accepts) {
		System.out.println("userId:" + userId);
		StringBuffer message = new StringBuffer();

		message.append("��@RequestHeader���÷���");
		message.append("{" + userAgent + "}");
		System.out.println("accepts.length:" + accepts.length);
		for (String accept : accepts) {
			message.append("(" + accept + ")");
		}
		model.addAttribute("message", message.toString());

		return "hello";
	}

}
