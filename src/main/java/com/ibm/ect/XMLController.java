package com.ibm.ect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class XMLController {

	@RequestMapping(value = "/xml")
	public ModelAndView userStaticLogin(Model model, HttpServletRequest request, HttpServletResponse resp) throws Exception {

		model.addAttribute("code", "测试");
		System.out.println("dsdsd");
		return new ModelAndView("test.vm");
	}
}
