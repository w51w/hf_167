package com.spring.view.store;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.store.StoreDTO;
import com.spring.biz.store.StoreListDTO;
import com.spring.biz.store.impl.StoreDAO;

@RestController // @Controller + @ResponseBody
				// 객체를 반환하기만 하면, 객체데이터는 application/json/형식의 HTTP ResponseBody에 직접 작성된다.
public class StoreController_Client {
	@RequestMapping(value = "/storeList.do")
	public StoreListDTO StoreList(HttpServletRequest request){
		
		String getcode = request.getParameter("code");
		int code = Integer.parseInt(getcode);
		
		//DB에 들어가있는 값 :  resource/store_img/shop_logo.PNG
		//클라이언트 요청 값 : http://192.168.0.6:8080/biz" + / + storeDTO.getStore_img()
		//업로드 경로 : 
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
