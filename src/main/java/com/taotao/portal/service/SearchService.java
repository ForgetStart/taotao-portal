/**
 * 
 */
package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * <p> Description: </p>
 * @author fengda
 * @date 2017年3月7日 下午4:44:54
 */
public interface SearchService {

	public SearchResult search(String queryString, int page);
}
