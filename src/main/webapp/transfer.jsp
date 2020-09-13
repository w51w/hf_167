<%@page import="com.spring.biz.user.impl.UserDAO"%>
<%@page import="com.spring.biz.common.JDBCUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%--
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	String pwd = request.getParameter("pwd");

	if(email.equals("1234") && pwd.equals("1234")) {
		out.print("참 true");
	} else {
		out.print("거짓 false");
	}

--%>
<%
	request.setCharacterEncoding("UTF-8");
	String e_mail = request.getParameter("e_mail");
	String password = request.getParameter("password");
	
	UserDAO transfer = new UserDAO();
	String returns = transfer.connectionTest(e_mail, password);
	System.out.println(returns);
	out.print(returns);
%>

