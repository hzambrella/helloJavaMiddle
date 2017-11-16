package com.hz.servlet.error;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �����쳣 Servlet implementation class CommonErrorHandler
 */
public class CommonErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommonErrorHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �洢��javax.servlet.error.status_code���쳣����תΪThrowable
		Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		// ״̬��
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		//������
		String servletName = (String) request
				.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
		}
		// uri
		String requestUri = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String title = "����̳� Error/Exception ��Ϣ";

		String docType = "<!DOCTYPE html>\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n");
		out.println("<h1>����̳��쳣��Ϣʵ����ʾ</h1>");
		if (throwable == null && statusCode == null) {
			out.println("<h2>������Ϣ��ʧ</h2>");
			out.println("�뷵�� <a href=\""
					+ response.encodeURL("http://localhost:8080/HelloServlet")
					+ "\">��ҳ</a>��");
		} else if (statusCode != null) {
			out.println("������� : " + statusCode);
		} else {
			out.println("<h2>������Ϣ</h2>");
			out.println("Servlet Name : " + servletName + "</br></br>");
			out.println("�쳣���� : " + throwable.getClass().getName()
					+ "</br></br>");
			out.println("���� URI: " + requestUri + "<br><br>");
			out.println("�쳣��Ϣ: " + throwable.getMessage());
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
