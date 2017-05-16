/**
 * 
 */
package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * <p> Description: 
 * 		商品搜索
 * </p>
 * @author fengda
 * @date 2017年3月7日 下午4:45:53
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_QUERY}")
	private String SEARCH_QUERY;
	/**
	 * 商品搜索，调用taotao-search的服务
	 */
	@Override
	public SearchResult search(String queryString, int page) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("q", queryString);
		param.put("page", page+"");
		
		
		try {
			//调用服务
			String result = HttpClientUtil.doGet(SEARCH_QUERY, param);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
			if(taotaoResult.getStatus() == 200){
				SearchResult searchResult = (SearchResult) taotaoResult.getData();
				return searchResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
