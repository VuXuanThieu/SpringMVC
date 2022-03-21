package com.laptrinhjavaweb.controller.admin;

import java.util.ArrayList;
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

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "userControllerOfAdmin")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/nguoi-dung/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
			HttpServletRequest request) {
		UserDTO model = new UserDTO();

		model.setPage(page);
		model.setLimit(limit);

		ModelAndView mav = new ModelAndView("admin/user/list");

		Pageable pageable = new PageRequest(page - 1, limit);

		model.setListResult(userService.findAll(pageable));
		model.setTotalItem(userService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));

		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value = "/quan-tri/nguoi-dung/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editUser(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/user/edit");

		UserDTO model = new UserDTO();

		if (id != null) {
			model = userService.findById(id);
		}

		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}

		List<String> roleNames = new ArrayList<>();
		roleNames.add("Quản trị");
		roleNames.add("Người dùng");
		mav.addObject("roleNames", roleNames);

		mav.addObject("model", model);
		return mav;
	}
}
