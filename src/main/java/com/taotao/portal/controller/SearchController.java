/**
 * 
 */
package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * <p> Description: 
 * 		查询商品，商品列表的展示
 * </p>
 * @author fengda
 * @date 2017年3月7日 下午5:02:38
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(Model model,@RequestParam("q")String queryString, @RequestParam(defaultValue="1")Integer page){
		
		if (StringUtils.isNoneBlank(queryString)) {
			try {
				queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		SearchResult searchResult = searchService.search(queryString, page);
		model.addAttribute("page", searchResult.getCurPage());
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("query", queryString);
		return "search";
	}
}
