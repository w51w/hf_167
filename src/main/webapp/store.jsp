<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <!-- edge mode, IE 버전 중 가장 최신 모드로 표시 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- 실제 액정 크기로 너비 설정, 1배율, safari 11 이전 브라우저에만 영향을 미침  -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- 페이지에 대한 설명 -->
  <meta name="description" content="">
  <!-- 문서의 저자 -->
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

<body id="page-top">

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
              <h6 class="m-0 font-weight-bold text-primary">점포관리</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>가게이미지</th>
                      <th>가게이름</th>
                      <th>가게 소개</th>
                      <th>가게 위치</th>
                      <th>전화번호</th>
                      <th>배달요금</th>
                      <th>최소주문금액</th>
                    </tr>
                  </thead>
                  <tfoot>

                  </tfoot>
                  <tbody>
					  <tr>				  
           
					  	<td><img style="max-width: 100px; height: auto; alt="이미지가 없습니다." 
					  	src="${store.store_img} " /></td>
					  	<td>${store.name }</td>
					    <td>${store.info }</td>
					    <td>${store.location }</td>
					    <td>${store.tel }</td>
					    <td>${store.delivery_price }</td>
					    <td>${store.least_price }</td>
					  </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
			<button id="updateAdmin" name="updateAdmin" onclick="location.href='storeUpdate.jsp'" class="mb-4 btn btn-primary">정보수정</button>
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