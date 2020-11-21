<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>관리자 업데이트</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">
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
            <h1 class="h3 mb-4 text-gray-800">점포관리</h1>
            <p class="mb-4">매장위치 및 정보등록</p>

            <div class="card shadow mb-4">
              <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">매장정보 등록</h6>
              </div>
              <div class="card-body">
                <form action="updateStore.do" method="post" enctype="multipart/form-data">
              	  <input type = "hidden" name = name value="${ store.name}">
                  <p class="mb-2">가게주소</p>
                  <input class="form-control-user mb-2" type="text" name="location" style="width: 500px" value="${store.location }"/>
                
                  <p class="mb-2">가게이미지</p>
                  <input class="form-control-user" name="uploadFile" type="file" />
                
                  <p class="mb-2" style="padding-top: 20px">가게소개</p>
                  <textarea class="form-control-user" name="info" style="width: 500px;" rows="8">${store.info } </textarea>
                
                  <p class="mb-2" style="padding-top: 20px">전화번호</p> 
                  <input class="form-control-user" name="tel" type="text" min="0" style="width: 500px" value="${store.tel }" />
                
                  <p class="mb-2" style="padding-top: 20px">배달요금</p> 
                  <input class="form-control-user" name="delivery_price" type="number" min="0" style="width: 500px" value="${store.delivery_price }" />
                
                  <p class="mb-2" style="padding-top: 20px">최소주문금액</p> 
                  <input class="form-control-user" name="least_price" type="number" min="0" style="width: 500px" value="${store.least_price }" />
                  
                  <p class="mb-2" style="padding-top: 20px"></p> 
                  <button type="submit" id="adminUpdate" class="mb-4 btn btn-primary">정보수정</button>
                </form>
              </div>
            </div>
          </div>
          <!-- /.container-fluid -->
			
			
        </div>
        <!-- End of Content-->

	  <!-- Footer -->
      <jsp:include page="module/footer.jsp"></jsp:include>
      <!-- End of Footer -->
      
      
      </div>
      <!-- End of Main Content -->

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

</body>

</html>
