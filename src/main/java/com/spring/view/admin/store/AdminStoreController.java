package com.spring.view.admin.store;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.biz.admin.store.AdminStoreDTO;
import com.spring.biz.admin.store.AdminStoreService;
import com.spring.biz.admin.user.AdminUserDTO;

@Controller
@SessionAttributes("store") //update에 필요
public class AdminStoreController {
	
	@Autowired
	private AdminStoreService adminStoreService;
	
	@RequestMapping("/getAdminStore.do")
	public String getStore(HttpSession session, AdminStoreDTO vo, Model model) {
		
		AdminUserDTO userVO = (AdminUserDTO)session.getAttribute("adminUser");
		vo.setName(userVO.getStore_name());
		
		AdminStoreDTO list = new AdminStoreDTO();
		list = adminStoreService.getAdminStore(vo);
		model.addAttribute("store", list);
		return "store.jsp";
		
	}
	
	@RequestMapping("/updateStore.do")
	public String updateBoard(AdminStoreDTO vo) throws IllegalStateException, IOException {

		//디렉토리 경로 추출
		String uploadDir = this.getClass().getResource("").getPath();
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) +
				"hf_167/src/main/webapp/resource";
		
		String DBpath = "resource/" + vo.getName() + "/store_img/" + vo.getUploadFile().getOriginalFilename();
		
		//디렉토리 생성
		File Folder = new File(uploadDir +"/" + vo.getName());
		if(!Folder.exists()) { Folder.mkdir(); }
		File subFolder = new File("/"+Folder.getAbsolutePath() + "/store_img" );
		if(subFolder.exists()) {
			if(subFolder.isDirectory()) {
				File[] files = subFolder.listFiles();
				for (int i=0; i<files.length; i++) {
					if(files[i].delete()) {		System.out.println("success");	}
					else {	System.out.println("fail");	}
				}
			}
		}
		else {subFolder.mkdir();}
		
		vo.getUploadFile()
			.transferTo(new File(subFolder.getAbsolutePath() + "/" + vo.getUploadFile().getOriginalFilename()));
		vo.setStore_img(DBpath);
		adminStoreService.updateStore(vo);
		
		return "getAdminStore.do";
	}
	
	@RequestMapping("/regularList.do") 
	public String regularList(AdminStoreDTO vo) {
		//조건 
		//
		return "regular.jsp";
	}
	
	@RequestMapping("/updateCondition.do")
	public String updateCondition(AdminStoreDTO vo) {
		adminStoreService.updateCondition(vo);
		
		return "regularSession.do";
	}
	
	@RequestMapping("/regularSession.do")
	public String regularSession(AdminStoreDTO vo, Model model) {
		AdminStoreDTO list = new AdminStoreDTO();
		list = adminStoreService.getAdminStore(vo);
		model.addAttribute("store", list);
		return "regular.jsp";
	}
}
