package com.hz.sevrlet.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RefreshService
 */
public class RefreshService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshService() {
        super();
    }

	/** servlet refreshѧϰ
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // ����ˢ���Զ�����ʱ��Ϊ 5 ��
		System.out.println(this.getClass().getName()+"hacker");
        response.setIntHeader("Refresh", 5);
        // ������Ӧ��������
        response.setContentType("text/html;charset=UTF-8");
       
        //ʹ��Ĭ��ʱ�������Ի������һ������  
        Calendar cale = Calendar.getInstance();  
        //��Calendar����ת����Date����  
        Date tasktime=cale.getTime();  
        //������������ĸ�ʽ  
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        //��ʽ�����  
        String nowTime = df.format(tasktime);
        PrintWriter out = response.getWriter();
        String title = "�Զ�ˢ�� Header ���� - ����̳�ʵ��";
        String docType =
        "<!DOCTYPE html>\n";
        out.println(docType +
          "<html>\n" +
          "<head><title>" + title + "</title></head>\n"+
          "<body bgcolor=\"#f0f0f0\">\n" +
          "<h1 align=\"center\">" + title + "</h1>\n" +
          "<p>��ǰʱ���ǣ�" + nowTime + "</p>\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}