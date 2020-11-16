<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<!-- edge mode, IE 버전 중 가장 최신 모드로 표시 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 실제 액정 크기로 너비 설정, 1배율, safari 11 이전 브라우저에만 영향을 미침  -->
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- 페이지에 대한 설명 -->
<meta name="description" content="">
<!-- 문서의 저자 -->
<meta name="author" content="">

<title>관리자 페이지</title>

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
  type="text/css">
<link
  href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
  rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
  rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
  rel="stylesheet">


</head>

<body id="page-top" onresize="resize()">

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
          <h1 class="h3 mb-2 text-gray-800">고객관리</h1>
<!-- first Row -->
          <div class="row ml-0">
            <!-- current condition -->
            <div class="col-xl-5 card shadow">
              <div class="card-header py-3 bg-white row justify-content-between">
                <h6 class="pt-3 font-weight-bold text-primary col-3">현재 조건</h6>
                <button id="copyButton" class="btn border mr-2 col-1"
                  onclick="copyCondition()">▶</button>
              </div>
              <div class="card-body">
                <form id="currentForm">

                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">날짜범위</span>
                    </div>
                    <input type="number" id="curConDay"
                      class="form-control text-right"
                      value="${store.condition_day }"
                      disabled="disabled">
                  </div>

                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">조건 </span>
                    </div>
                    <input type="text" id="curCon"
                      class="form-control text-right"
                      value="${store.condition }" disabled="disabled">
                  </div>

                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">값</span>
                    </div>
                    <input type="number" id="curConVal"
                      class="form-control text-right"
                      value="${store.condition_value }"
                      disabled="disabled">
                  </div>
                </form>
              </div>
            </div>
            <div class="col-xl-1">
              <p>
            </div>
            <div class="col-xl-5 card shadow">
              <form id="updateForm" method="post">
                <div class="card-header py-3 bg-white row justify-content-between">
          <!-- 분기 처리 -->
                  <button type="submit" onclick="javascript: updateForm.action='regularList.do'"
                       class="btn btn-warning mt-1 col-2 ml-2">적용</button>
                  
                  <button type="submit" onclick="javascript: updateForm.action='updateCondition.do'" 
                       class="btn btn-danger mt-1 col-2 mr-2">변경</button>
                </div>
                
                <div class="card-body">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">날짜범위</span>
                    </div>
                    <input type="number" id="updateConDay"
                      name="condition_day"
                      class="form-control text-right">
                  </div>

                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <label class="input-group-text"
                        for="inputGroupSelect01">조건</label>
                    </div>
                    <select class="custom-select" id="updateCon" name="condition">
                      <option selected >Choose...</option>
                      <option value="합산금액">합산금액</option>
                      <option value="주문횟수">주문횟수</option>
                    </select>
                  </div>

                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">값</span>
                    </div>
                    <input type="number" id="updateConVal" name="condition_value" class="form-control text-right" placeholder="할인액 , 분기 추가 가능 , 많아지면 json으로 처리">
                  </div>

                  <input type="hidden" name="name" value="${ store.name}">
                </div>
              </form>
            </div>
          </div>
<!-- second Row -->
          <div class="row ml-0 mt-5">
    <!-- DataTales Example -->
              <div class="card shadow mb-4 col-xl-11">
                <div class="card-header bg-white py-3">
                  <h6 class="m-0 font-weight-bold text-primary">단골 고객 목록</h6>
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
              </div>
            </div>

      </div>
      <!-- End of Main Content -->

      <jsp:include page="module/footer.jsp"></jsp:include>

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top"> <i
    class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <jsp:include page="module/logoutModal.jsp"></jsp:include>

  <!-- MyScript -->
  <script>
			function resize() {
				var Button = document.getElementById("copyButton");
				var b = window.matchMedia("screen and (max-width: 1170px)");
				if (b.matches) {
					Button.innerHTML = "▼";
				} else {
					Button.innerHTML = "▶";
				}
			}
			function copyCondition() {
				var current = document.getElementById("currentForm");
				var update = document.getElementById("updateForm");

				for (var i = 0; i < current.length; i++) {

                    update[i+2].value = current[i].value
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