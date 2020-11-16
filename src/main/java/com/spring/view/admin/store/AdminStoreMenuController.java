package com.spring.view.admin.store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.admin.store.AdminStoreMenuDTO;
import com.spring.biz.admin.store.AdminStoreService;
import com.spring.biz.admin.user.AdminUserDTO;

@Controller
public class AdminStoreMenuController {

	@Autowired
	private AdminStoreService adminStoreService;

// �޴� ���
	@RequestMapping("/getAdminMenuList.do") // required = store_name
	public String getMenuList(HttpSession session, AdminStoreMenuDTO vo, Model model){

		AdminUserDTO userVO = (AdminUserDTO) session.getAttribute("adminUser");
		vo.setStore_name(userVO.getStore_name());
		List<AdminStoreMenuDTO> list = adminStoreService.getMenuList(vo);
		
		for(int i =0; i< list.size(); i++) {
			if(list.get(i).getFood_opt() != null) {
				String jsonString = list.get(i).getFood_opt();
				JSONObject json = new JSONObject(jsonString);
				Iterator<String> keys = json.keySet().iterator();
				StringBuilder builder = new StringBuilder();
				while(keys.hasNext()) {
					String key = keys.next();
					builder.append(String.format("�ɼ�: %s | ����: %s��<br>", key, json.get(key)));
				}
				list.get(i).setFood_opt(builder.toString());
			}
			else {
				list.get(i).setFood_opt("");
			}
			
		}
		model.addAttribute("menuList", list);
		return "storeMenu.jsp";
	}

// �޴� ���
	@RequestMapping("/insertMenu.do")
	public void insertMenu(HttpServletResponse response, AdminStoreMenuDTO vo) throws IOException {
		if(vo.getType() == 1) {
			if(!vo.getUploadFile().isEmpty()) { //�̹����� �ִٸ�
				String uploadDir = this.getClass().getResource("").getPath();
				uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) 
						+ "hf_167/src/main/webapp/resource";

				// ���丮 ��� ����
				String DBpath = "resource/" + vo.getStore_name() + "/menu_img/" + vo.getUploadFile().getOriginalFilename();

				// ���ٸ� ���丮 ����
				File Folder = new File(uploadDir + "/" + vo.getStore_name());
				if (!Folder.exists()) {	Folder.mkdir();	}
				// ���ٸ� ���丮 ����
				File subFolder = new File("/" + Folder.getAbsolutePath() + "/menu_img");
				if (!subFolder.exists()) {	subFolder.mkdir();	}
				
				// �̹����� �ִٸ�
				if(!vo.getUploadFile().isEmpty()) {
					vo.getUploadFile()
					.transferTo(new File(subFolder.getAbsolutePath() + "/" + vo.getUploadFile().getOriginalFilename()));
				}
				//��ü�� ��� ����
				vo.setFood_img(DBpath);	
			}						
		}
		adminStoreService.insertMenu(vo);
		response.sendRedirect("getAdminMenuList.do");
	}

// �޴� ����
	@RequestMapping("/deleteMenu.do")
	public String deleteMenu(AdminStoreMenuDTO vo) {
		adminStoreService.deleteMenu(vo);
		return "getAdminMenuList.do";
	}
}
