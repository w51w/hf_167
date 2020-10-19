package com.spring.view.store;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.store.StoreDTO;
import com.spring.biz.store.StoreListDTO;
import com.spring.biz.store.StoreMenuListDTO;
import com.spring.biz.store.StoreService;

@RestController // @Controller + @ResponseBody
				// 객체를 반환하기만 하면, 객체데이터는 application/json/형식의 HTTP ResponseBody에 직접 작성된다.
public class StoreController_Client {
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value = "/getStore.do")
	public StoreDTO getStore(HttpServletRequest request) {
		String store_name = request.getParameter("store_name");
		return storeService.getStore_clinet(store_name);
	}
	
	@RequestMapping(value = "/storeList.do")
	public StoreListDTO StoreList(HttpServletRequest request){
		String getcode = request.getParameter("code");
		int code = Integer.parseInt(getcode);
				
		return storeService.getStoreList_client(code);
	}
	
	@RequestMapping(value = "/storeMenu.do")
	public StoreMenuListDTO StoreMenu(HttpServletRequest request) {
		String store_name = request.getParameter("store_name");
		
		return storeService.getMenu_client(store_name);
		
	}
}
