package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {

	public ItemInfo getItemById(Long itemId);
	
	public String getItemDesc(Long itemId);
	
	public String getItemParam(Long itemId);
}
