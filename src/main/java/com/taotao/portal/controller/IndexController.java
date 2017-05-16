/**
 * 
 */
package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

/**
 * <p> Description: </p>
 * @author fengda
 * @date 2017年2月23日 下午2:51:53
 */
@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String indexShow(Model model){
		//获取大广告位展示的内容，封装到model中传给前台
		String resultString = contentService.getContentList();
		model.addAttribute("ad1", resultString);
		return "index";
	}
}
