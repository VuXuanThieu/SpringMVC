package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.AuthorDTO;
import com.laptrinhjavaweb.service.IAuthorService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "authorControllerOfAdmin")
public class AuthorController {
	
	@Autowired
	private IAuthorService authorService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/quan-tri/tac-gia/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, 
								 @RequestParam("limit") int limit, HttpServletRequest request) {
		AuthorDTO model = new AuthorDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/author/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		model.setListResult(authorService.findAll(pageable));
		model.setTotalItem(authorService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/tac-gia/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editAuthor(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("admin/author/edit");
		
		AuthorDTO model = new AuthorDTO();
		
		if (id != null) {
			model = authorService.findById(id);
		}
		
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		
		mav.addObject("model", model);
		return mav;
	}
}
