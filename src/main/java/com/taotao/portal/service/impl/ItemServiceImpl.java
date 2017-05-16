package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * 商品信息管理
 * @author fd
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	//商品基本信息
	@Value("${SEARCH_ITEM_BASE_INFO}")
	private String SEARCH_ITEM_BASE_INFO;
	
	//商品描述信息
	@Value("${SEARCH_ITEM_DESC}")
	private String SEARCH_ITEM_DESC;
	
	//商品规格信息
	@Value("${SEARCH_ITEM_PARAM}")
	private String SEARCH_ITEM_PARAM;
	
	/**
	 * 查询商品基本信息
	 */
	@Override
	public ItemInfo getItemById(Long itemId) {
		try {
			//调用taotao-rest获取商品基本信息
			String pojoJson = HttpClientUtil.doGet(REST_BASE_URL + SEARCH_ITEM_BASE_INFO + itemId);
			if(StringUtils.isNotBlank(pojoJson)){
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(pojoJson, ItemInfo.class);
				if(taotaoResult.getStatus() == 200){
					ItemInfo tbItem = (ItemInfo) taotaoResult.getData();
					return tbItem;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 查询商品描述信息
	 */
	@Override
	public String getItemDesc(Long itemId) {
		try {
			String descJson = HttpClientUtil.doGet(REST_BASE_URL + SEARCH_ITEM_DESC + itemId);
			if(StringUtils.isNotBlank(descJson)){
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(descJson, TbItemDesc.class);
				if(taotaoResult.getStatus() == 200){
					TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
					return itemDesc.getItemDesc();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询商品规格参数
	 */
	@Override
	public String getItemParam(Long itemId) {
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + SEARCH_ITEM_PARAM + itemId);
			//把json转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
				String paramData = itemParamItem.getParamData();
				//生成html
				// 把规格参数json数据转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				StringBuffer sb = new StringBuffer();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for(Map m1:jsonList) {
					sb.append("        <tr>\n");
					sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
					sb.append("        </tr>\n");
					List<Map> list2 = (List<Map>) m1.get("params");
					for(Map m2:list2) {
						sb.append("        <tr>\n");
						sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
						sb.append("            <td>"+m2.get("v")+"</td>\n");
						sb.append("        </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				//返回html片段
				return sb.toString();
			}
				 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
