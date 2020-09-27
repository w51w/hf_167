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
		
		StoreDAO storeDAO = new StoreDAO();
		StoreListDTO storeListDTO = new StoreListDTO();
		
		ArrayList<StoreDTO> storeList = storeDAO.getStoreList_clinet(code);
		storeListDTO.setStoreList(storeList);
		
		return storeListDTO;
		
	}
}
