package com.spring.biz.store;

public interface StoreService {
	public StoreDTO getStore_clinet(String store_name);
	
	public StoreListDTO getStoreList_client(int code);
	
	public StoreMenuListDTO getMenu_client(String store_name);
}
