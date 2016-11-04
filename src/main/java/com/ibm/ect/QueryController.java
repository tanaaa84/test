package com.ibm.ect;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QueryController {

	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(Model model,HttpServletRequest request, ModelMap map) {
		
		System.out.println(request.getParameter("hello"));

		model.addAttribute("hello", request.getParameter("hello"));
		
		return "query.jsp";
	}
	
	
}
