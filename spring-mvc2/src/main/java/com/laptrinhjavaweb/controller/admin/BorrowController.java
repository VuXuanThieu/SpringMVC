package com.laptrinhjavaweb.controller.admin;

import java.util.List;
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

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.dto.BorrowDTO;
import com.laptrinhjavaweb.service.IBookService;
import com.laptrinhjavaweb.service.IBorrowService;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "borrowControllerOfAdmin")
public class BorrowController {
	
	@Autowired
	private IBorrowService borrowService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBookService bookService;
	
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/muon/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, 
								 @RequestParam("limit") int limit, HttpServletRequest request) {
		BorrowDTO model = new BorrowDTO();
		
		model.setPage(page);
		model.setLimit(limit);
		
		ModelAndView mav = new ModelAndView("admin/borrow/list");
		
		Pageable pageable = new PageRequest(page - 1, limit);
		
		model.setListResult(borrowService.findAll(pageable));
		model.setTotalItem(borrowService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/muon/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editBorrow(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("admin/borrow/edit");
		
		BorrowDTO model = new BorrowDTO();
		
		if (id != null) {
			model = borrowService.findById(id);
		}
		
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		
		mav.addObject("users", userService.findAllString());
		mav.addObject("books", bookService.findAllString());
		
		mav.addObject("model", model);
		return mav;
	}
}
