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

import com.laptrinhjavaweb.dto.PublisherDTO;
import com.laptrinhjavaweb.service.IPublisherService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "publisherControllerOfAdmin")
public class PublisherController {
	
	@Autowired
	private IPublisherService publisherService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/quan-tri/nha-xuat-ban/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, 
								 @RequestParam("limit") int limit, HttpServletRequest request) {
		PublisherDTO model = new PublisherDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/publisher/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		model.setListResult(publisherService.findAll(pageable));
		model.setTotalItem(publisherService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/nha-xuat-ban/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editPublisher(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("admin/publisher/edit");
		
		PublisherDTO model = new PublisherDTO();
		
		if (id != null) {
			model = publisherService.findById(id);
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
