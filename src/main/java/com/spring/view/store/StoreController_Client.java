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
		
		StoreDAO storeDAO = new StoreDAO();
		StoreListDTO storeListDTO = new StoreListDTO();
		
		ArrayList<StoreDTO> storeList = storeDAO.getStoreList_clinet(code);
		storeListDTO.setStoreList(storeList);
		
		return storeListDTO;
		
	}
}
