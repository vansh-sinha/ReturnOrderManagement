package com.returnorder.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import com.returnorder.portal.client.AuthClient;
import com.returnorder.portal.dto.AuthenticationResponseDTO;
import com.returnorder.portal.model.LoginModel;
import com.returnorder.portal.service.LoginService;
import com.returnorder.portal.service.LoginServiceImpl;

@Controller

@Slf4j
public class LoginController {


	@Autowired
	LoginService loginService;



	@GetMapping("/login")
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("model", new LoginModel());
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView performLogin(@Valid @ModelAttribute("model") LoginModel model, BindingResult result,
			HttpServletRequest request) throws FeignException {
		ModelAndView mv = new ModelAndView("login");

		log.info(" ========Before Token generation ======");
		AuthenticationResponseDTO token = null;

		try {
			token = loginService.login(model);
		} catch (Exception e) {
			log.info("Exception");
			mv.addObject("error", "Invalid Credentials");
			return mv;
		}

		request.getSession().setAttribute("token", "Bearer " + token.getJwtAuthToken());
		request.getSession().setAttribute("user", model.getUserName());
		log.info(" ========After Token generation ======");
		log.info(token.getJwtAuthToken());

		return new ModelAndView(new RedirectView("order"));

	}

}
