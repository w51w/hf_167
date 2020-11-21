<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>관리자 페이지</title>

  <!-- Custom fonts for this template -->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">

</head>

<body id="page-top" onload="type2String()">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <jsp:include page="module/sideBar.jsp"></jsp:include>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <jsp:include page="module/topBar.jsp"></jsp:include>
        
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Tables</h1>


          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">주문처리</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered table-striped table-hover" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>주문일시</th>
                      <th>아이디</th>
                      <th>연락처</th>
                      <th>주소</th>
                      <th>상세주소</th>
                      <th>주문메뉴 및 수량</th>
                      <th>주문상태</th>
                      <th>주문처리</th>
                    </tr>
                  </thead>
                  <tfoot>
                  
                  </tfoot>
                  <tbody>
					<c:forEach  items="${ orderList}" var="order">
					  <tr>
					  	<td><c:out value="${order.date_order }"/>
					    <td><c:out value="${order.user_e_mail }"/></td>
					    <td><c:out value="${order.phone }"/></td>
					    <td><c:out value="${order.address }"/></td>
					    <td><c:out value="${order.address_detail }"/></td>
					    <td>
					    	<c:out value="${order.food1 }" escapeXml="false"/>
					    	<c:out value="${order.food2 }" escapeXml="false"/>
					    	<c:out value="${order.food3 }" escapeXml="false"/>
					    	<c:out value="${order.food4 }" escapeXml="false"/>	
					    	<c:out value="${order.food5 }" escapeXml="false"/>
					    </td>
					    <td><c:out value="${order.type }"/></td>
					    <td>
					    <div class="dropdown mb-4">
	                    <button class="btn btn-primary dropdown-toggle" type="button" id="orderPrcess" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                      	주문처리
	                    </button>
	                    <div class="dropdown-menu animated--fade-in" aria-labelledby="dropdownMenuButton">
	                      <a class="dropdown-item" href="updateType.do?seq=${ order.seq}&type=1">배달중</a>
	                      <a class="dropdown-item" href="updateType.do?seq=${ order.seq}&type=2">배달완료</a>
	                      <a class="dropdown-item" href="updateType.do?seq=${ order.seq}&type=3">주문취소</a>
	                    </div>
                  		</div>
                  		</td>
					  </tr>
					</c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <jsp:include page="module/footer.jsp"></jsp:include>

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <jsp:include page="module/logoutModal.jsp"></jsp:include>

<!-- MYSCRIPT -->
  <script>
	function type2String(){
		var element = document.getElementById('dataTable').getElementsByTagName('td');
		for(var i =6; i<element.length;){
			var type = element[i].innerText;
			switch(type){
				case "4":
					element[i].innerText = "리뷰작성완료";
					break;
				case "3":
					element[i].innerText = "주문취소";
					break;
				case "2":
					element[i].innerText = "배달완료";
					break;
				case "1":
					element[i].innerText = "배달중";
					break;
				default:
					element[i].innerText = "주문접수대기";	
					break;
			}
			i = i+8;	
		}
	}

  </script>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>

</body>

</html>