package vn.fis.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends BaseController {
	
	@RequestMapping(value = {"/admin/home"}, method = RequestMethod.GET)
	public String Home(ModelMap model) {
		model.addAttribute("active", "home");
		return "home";
	}
}
