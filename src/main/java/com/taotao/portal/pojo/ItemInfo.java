package com.taotao.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taotao.pojo.TbItem;

public class ItemInfo extends TbItem {

	@JsonIgnore		//作用是json序列化时将java bean中的一些属性忽略掉,序列化和反序列化都受影响。
	public String[] getImages() {
		String image = getImage();
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}
}
