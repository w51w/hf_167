package com.spring.view.store;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.store.StoreDTO;
import com.spring.biz.store.StoreListDTO;
import com.spring.biz.store.impl.StoreDAO;

@RestController // @Controller + @ResponseBody
				// ��ü�� ��ȯ�ϱ⸸ �ϸ�, ��ü�����ʹ� application/json/������ HTTP ResponseBody�� ���� �ۼ��ȴ�.
public class StoreController_Client {
	@RequestMapping(value = "/storeList.do")
	public StoreListDTO StoreList(HttpServletRequest request){
		
		String getcode = request.getParameter("code");
		int code = Integer.parseInt(getcode);
		
		//DB�� ���ִ� �� :  resource/store_img/shop_logo.PNG
		//Ŭ���̾�Ʈ ��û �� : http://192.168.0.6:8080/biz" + / + storeDTO.getStore_img()
		//���ε� ��� : 
		// /C:/android_data/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/hf_167/WEB-INF/classes/com/spring/view/store/
		String uploadDir = this.getClass().getResource("").getPath();
		System.out.println(uploadDir);
		
		// C:/android_data/hf_167/src/main/webapp/resource
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) +
				"hf_167/src/main/webapp/resource";
		System.out.println(uploadDir);
		
		StoreDAO storeDAO = new StoreDAO();
		StoreListDTO storeListDTO = new StoreListDTO();
		
		ArrayList<StoreDTO> storeList = storeDAO.getStoreList_clinet(code);
		storeListDTO.setStoreList(storeList);
		
		return storeListDTO;
		
	}
}
