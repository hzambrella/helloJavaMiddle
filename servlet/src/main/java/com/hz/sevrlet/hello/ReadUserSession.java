package com.hz.sevrlet.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**ѧϰ��ȡsession
 * Servlet implementation class ReadUserSession
 */
public class ReadUserSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadUserSession() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		 // ��ȡ session ����ʱ��
        Date createTime = new Date(session.getCreationTime());
        // ��ȡ����ҳ�����һ�η���ʱ��
        Date lastAccessTime = new Date(session.getLastAccessedTime());
         
        //������������ĸ�ʽ  
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    
        String title = "Servlet Session ʵ�� - ����̳�";
     // ������Ӧ��������
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                 "<h2 align=\"center\">Session ��Ϣ</h2>\n" +
                "<table border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "  <th>Session ��Ϣ</th><th>ֵ</th></tr>\n" +
                "<tr>\n" +
                "  <td>id</td>\n" +
                "  <td>" + session.getId() + "</td></tr>\n" +
                "<tr>\n" +
                "  <td>����ʱ��</td>\n" +
                "  <td>" +  df.format(createTime) + 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>������ʱ��</td>\n" +
                "  <td>" + df.format(lastAccessTime) + 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>�û� ID</td>\n" +
                "  <td>" + session.getAttribute("userID")+ 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>��ӦsessionId��������</td>\n" +
                "  <td>" + session.getAttribute("visitCount") + "</td></tr>\n" +
                "</table>\n" +
                "</body></html>"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}

}
