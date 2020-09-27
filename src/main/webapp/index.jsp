<%@page import="java.util.*" %>
<%@page import="com.spring.biz.admin.impl.AdminDAO" %>
<%@page import="com.spring.biz.admin.AdminVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="style.css">
</head>



<body>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>

<div id="wrapper">
    <header>
        <div style="text-align: right">
        안녕하세요 <button type="button" class="btn btn-link "><b>로그아웃</b></button>
        </div>
        <div>
            <h1 style="line-height: 100%; font-size:50px; font-weight: bold; color: #ffffff; margin-left: 15px" >배달인천</h1>
        </div>

    </header>

    <nav>
        <div id="nav">
            <div class="navber navbar-default">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav nav-pills nav-stacked">
                            <li role="presentation" class=""><a href="#">점포관리</a></li>
                            <li role="presentation" class="text-white"><a href="#"><span style="color: #0f0f0f">메뉴등록</span></a></li>
                            <li role="presentation"><a href="#"><span style="color: #0f0f0f">주문처리</span></a></li>
                            <li role="presentation"><a href="#"><span style="color: #0f0f0f">주문로그</span></a></li>
                            <li role="presentation"><a href="#"><span style="color: #0f0f0f">고객관리</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </nav>

    <section class="container-fluid">
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h3 class="m-0 font-weight-bold text-black">DataTables Example</h3>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr >
                            <th>가게 소개</th>
                            <th>가게 위치</th>
                            <th>전화번호</th>
                            <th>배달요금</th>
                            <th>최소주문금액</th>
                        </tr>
                        </thead>
						<c:forEach items="${ adminList}" var="admin">
							<tr>
								<td><c:out value="${admin.info }"/></td>
								<td><c:out value="${admin.location }"/></td>
								<td><c:out value="${admin.tel }"/></td>
								<td><c:out value="${admin.delivery_price }"/></td>
								<td><c:out value="${admin.least_price }"/></td>
							</tr>
						</c:forEach>						
                        </tfoot>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>

    <aside></aside>

    <footer>
    </footer>


</div>

</body>
</html>

