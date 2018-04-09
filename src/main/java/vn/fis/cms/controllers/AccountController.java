package vn.fis.cms.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.fis.cms.domain.User;
import vn.fis.cms.services.AccountService;
import vn.fis.cms.model.AjaxResult;

@Controller
public class AccountController {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = {"/admin/account"}, method = RequestMethod.GET)
	public String Account(ModelMap model) {
		Page<User> pgUser = accountService.GetListUser(0, 2);
		List<User> lstUser = pgUser.getContent();
		model.addAttribute("lstUser", lstUser);
		model.addAttribute("total", pgUser.getTotalElements());
		model.addAttribute("totalPage", pgUser.getTotalPages());
		model.addAttribute("currentpage", 1);
		model.addAttribute("active", "user");
		return "account";
	}
	
	@RequestMapping(value = { "api/admin/account/loaddata" }, method = RequestMethod.POST)
	public @ResponseBody AjaxResult GetListLocation(int pageIndex, int pageSize) {
		AjaxResult result = new AjaxResult();
		try {
			Page<User> pgUser = accountService.GetListUser((pageIndex - 1), pageSize);
			List<User> lstUser = pgUser.getContent();
			if (lstUser == null) {
				result.setResult(false);
				result.setMessage(messageSource.getMessage("E001", null, Locale.getDefault()));
			} else {
				result.setResult(true);
				result.setMessage(messageSource.getMessage("S001", null, Locale.getDefault()));
				result.setResultData(lstUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMessage(messageSource.getMessage("E002", null, Locale.getDefault()));
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = "/account/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Email hoặc mật khẩu không đúng ");
		}
		if (logout != null) {
			model.addObject("msg", "Bạn đã đăng xuất thành công");
		}
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value="/account/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/account/login?logout";
	}
}
