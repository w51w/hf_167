<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
 content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>정보수정</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
 type="text/css">
<link
 href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
 rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
 rel="stylesheet">

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
     <h1 class="h3 mb-4 text-gray-800">매뉴등록</h1>
     <p class="mb-4">메뉴등록</p>

     <div class="card shadow mb-4">
      <div class="card-header py-3">
       <h6 class="m-0 font-weight-bold text-primary">메뉴정보 입력</h6>
      </div>
      <div class="card-body">
       <form action="insertMenu.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name=store_name value="${ adminUser.store_name}">
        <div class="mb-2 dropdown">
         <button class="btn btn-secondary dropdown-toggle" type="button" id="type" value="분류" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">분류</button>
         <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
           <a class="dropdown-item" onclick="enable(0)">메뉴바</a>
           <a class="dropdown-item" onclick="enable(1)">음식</a>
         </div>
        </div>
        <!-- 타입 -->
        <input type="hidden" id="form_type" name="type" value="">
        <input class="form-control-user" id="menubar" name="menubar" type="text" oninput="menubarCheck()" placeholder="메뉴바 이름을 입력하세요" style="width: 400px" />

        <p class="my-2">이미지</p>
        <input class="form-control-user" id="uploadFile" name="uploadFile" type="file" />

        <p class="mb-2" style="padding-top: 20px">메뉴명</p>
        <input class="form-control-user" id="food" name="food" type="text" placeholder="메뉴를 입력하세요" style="width: 500px" />
        <p class="mb-2" style="padding-top: 20px">금액</p>
        <input class="form-control-user" id="food_price" name="food_price" type="number" min="0" style="width: 500px" />
        <p>
         <!-- 버튼 -->
         <input type="button" id="addOpt" class="mt-5 btn btn-primary" onclick="add()" disabled="disabled" value="옵션추가" />
         <!-- 옵션 -->
        <div id="option-group"></div>
        <!-- 히든 -->
        <p class="mb-2 pt-4"></p>
        <input class="form-control-user" id="food_opt" name="food_opt" type="hidden" style="width: 500px" />
        <p>

         <button id="insertMenu" class="mb-4 btn btn-primary" disabled="disabled">메뉴등록</button>
       </form>
      </div>
     </div>
    </div>
    <!-- /.container-fluid -->

   </div>
   <!-- End of Main Content -->

   <!-- Footer -->
   <jsp:include page="module/footer.jsp"></jsp:include>
   <!-- End of Footer -->

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

 <!-- Bootstrap core JavaScript-->
 <script src="vendor/jquery/jquery.min.js"></script>
 <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

 <!-- Core plugin JavaScript-->
 <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

 <!-- Custom scripts for all pages-->
 <script src="js/sb-admin-2.min.js"></script>

 <script>
 var cnt = 0;
		function add() {
			cnt++;
			var optGroup = document.getElementById("option-group");
			var foodDiv = document.createElement("div");
			foodDiv.setAttribute("id", "food" + cnt); //속성 설정
			optGroup.appendChild(foodDiv); //optGruop <-- foodDiv
			foodDiv.innerHTML += "<p class='mb-2'>옵션명 " + cnt + "</p>";
			foodDiv.innerHTML += "<input class='form-control-user' id='food"
					+ cnt
					+ "_opt' name='food"
					+ cnt
					+ "_opt' type='text' onchange='toJson()' placeholder ='옵션을 입력하세요' style='width: 500px'/>";
			foodDiv.innerHTML += "<input type='button' id='removeOpt' class='btn btn-danger' onclick='remove("
					+ cnt + ")' value='옵션삭제'/>";
			foodDiv.innerHTML += "<p class='mb-2 pt-4'>금액</p>";
			foodDiv.innerHTML += "<input class='form-control-user' id='food"
					+ cnt
					+ "_value' name='food"
					+ cnt
					+ "_value' type='number' onchange='toJson()' min='0' style='width: 500px' /> <p>";
		}

		function remove(curIndex) {
			var foodDiv = document.getElementById("food" + curIndex);
			var parent = foodDiv.parentElement; //부모 객체 알아내기
			parent.removeChild(foodDiv); //삭제
			cnt--;

			for (var i = curIndex; i <= cnt; i++) {
				var foodDiv = document.getElementById("food" + String(i + 1)); //삭제 앞의 인덱스 가져오기
				var children = foodDiv.childNodes; //자식노드 배열로 저장
				//<p> 앞에 있는 공백도 #text라는 자식노드로 뜸 !인덱스에 주의
				children[0].innerHTML = "옵션명" + i;
				children[1].setAttribute("id", "food" + i + "_opt");
				children[1].setAttribute("name", "food" + i + "_opt");
				children[2].setAttribute("onclick", "remove(" + i + ")");
				children[4].setAttribute("id", "food" + i + "_value");
				children[4].setAttribute("name", "food" + i + "_value");
				foodDiv.setAttribute("id", "food" + i);
				console.log("회전:" + i);
			}
            toJson();
		}
  
        
		function toJson() {
   
            var data = {};
            
			for(var i =0; i<cnt; i++){
			   var opt = document.getElementById('food'+String(i+1)+'_opt').value;
			   var value = document.getElementById('food'+String(i+1)+'_value').value;
			   data[opt] = value;
            }
			var json = JSON.stringify(data);

			document.getElementById('food_opt').value = json;
		}

		var input = ["uploadFile", "food", "food_price"];
		function enable(type) {
			if (type == 0) {
				document.getElementById("addOpt").disabled = true; //비활성
				var optGroup = document.getElementById("option-group");
    //모든 자식 삭제
                cnt=0;
				while(optGroup.hasChildNodes()){
					optGroup.removeChild(optGroup.firstChild);
			           }
				document.getElementById("form_type").value = 0;
				document.getElementById("type").innerHTML = "메뉴바";
				for (var i = 0; i < input.length; i++) {
					document.getElementById(input[i] + "").disabled = true; //비활성
					document.getElementById(input[i] + "").value = null;
				}
			} else {
				document.getElementById("addOpt").disabled = false; //활성
				document.getElementById("form_type").value = 1;
				document.getElementById("type").innerHTML = "음식";
				for (var i = 0; i < input.length; i++) {
					document.getElementById(input[i] + "").disabled = false; //활성
				}
			}
		}
		function menubarCheck() {
			var menubar = document.getElementById("menubar").value;
			if (menubar != "" && document.getElementById("type").innerHTML != "분류") {
				document.getElementById("insertMenu").disabled = false; //활성
			} else {
				document.getElementById("insertMenu").disabled = true; //비활성
			}
		}
      
	</script>

</body>

</html>
