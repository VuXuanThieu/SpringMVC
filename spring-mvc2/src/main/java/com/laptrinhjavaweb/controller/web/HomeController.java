package com.laptrinhjavaweb.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.service.IBookService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private IBookService bookService;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit, HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword) {

		ModelAndView mav = new ModelAndView("web/home");

		BookDTO model = new BookDTO();

		model.setPage(page);
		model.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);

		//0612
		if (keyword == null) {
			model.setListResult(bookService.findAll(pageable));
			model.setTotalItem(bookService.getTotalItem());
		} else {
			model.setListResult(bookService.findAllTitle(pageable, keyword));
			model.setTotalItem(bookService.getTotalItemKeyword(keyword));

			if (bookService.getTotalItemKeyword(keyword) == 0) {
				Map<String, String> message = messageUtil.getMessage("error_system");
				mav.addObject("message", "Không tìm thấy tiêu đề sách phù hợp");
				mav.addObject("alert", message.get("alert"));
			}

		}

		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));

		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}

		mav.addObject("model", model);
		mav.addObject("keyword", keyword);

		return mav;
	}

	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
