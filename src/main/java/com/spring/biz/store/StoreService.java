package com.spring.biz.store;

public interface StoreService {
	public StoreListDTO getStoreList_client(int code);
	
	public StoreMenuListDTO getMenu_client(String store_name);
}
