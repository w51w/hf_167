package com.spring.biz.store.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.store.StoreDTO;
import com.spring.biz.store.StoreListDTO;
import com.spring.biz.store.StoreMenuListDTO;
import com.spring.biz.store.StoreService;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreDAO storeDAO;

	@Override
	public StoreDTO getStore_clinet(String store_name) {
		return storeDAO.getStore_clinet(store_name);
	}
	
	@Override
	public StoreListDTO getStoreList_client(int code) {
		return storeDAO.getStoreList_client(code);
	}

	@Override
	public StoreMenuListDTO getMenu_client(String store_name) {
		return storeDAO.getMenu_client(store_name);
	}

	

	
}
