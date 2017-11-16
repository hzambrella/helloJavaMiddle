package com.hz.sevrlet.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println(this.getClass().getName()+"init");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println(this.getClass().getName()+"destory");
	}

	/** servlet ��ʼѧϰdoGet
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������������
		response.setContentType("text/html;charset=utf-8");
		
		//example1
		//��������������
		//response.getWriter().write("���ǵ�һ��servlet���򡣵�ǰʱ��Ϊ��"+new Date());
		
		//example2
		// ������Ӧ��������
   
        PrintWriter out = response.getWriter();
        String title = "ʹ�� GET ������ȡ������";
        // ��������
        String name =request.getParameter("name");
        if (null!=name){
        	name=new String(name.getBytes("ISO8859-1"),"UTF-8");
        }
        
        //String name=request.getParameter("name");
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body bgcolor=\"#f0f0f0\">\n" +
            "<h1 align=\"center\">" + title + "</h1>\n" +
            "<ul>\n" +
            "  <li><b>վ����</b>��"
            + name + "\n" +
            "  <li><b>��ַ</b>��"
            + request.getParameter("url") + "\n" +
            "</ul>\n" +
            "</body></html>");			
	}

	/**servlet ��ʼѧϰdoPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
