package com.returnorder.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.returnorder.portal.client.AuthClient;
import com.returnorder.portal.client.ComponentClient;
import com.returnorder.portal.dto.PackagingAndDeliveryDTO;
import com.returnorder.portal.dto.PaymentStatusDTO;
import com.returnorder.portal.dto.ValidatingDTO;
import com.returnorder.portal.model.ProcessRequest;
import com.returnorder.portal.model.ProcessResponse;
import com.returnorder.portal.service.ProcessRequestServiceImpl;
import com.returnorder.portal.template.EntityTemplate;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProcessController {

	@Autowired
	private ProcessRequestServiceImpl processRequestServiceImplObj;
	@Autowired
	ComponentClient componentClient;
	@Autowired
	ProcessResponse processResponse;
	@Autowired
	AuthClient authClient;
	@Autowired
	ProcessRequest processRequest;

	@Autowired
	EntityTemplate template;

	RestTemplate restTemplate = new RestTemplate();
	PaymentStatusDTO paymentStatusDTO = new PaymentStatusDTO();

	@GetMapping("/order")
	public ModelAndView showProcessing() {
		ModelAndView mv = new ModelAndView("orderDetails");
		mv.addObject("model", new ProcessRequest());
		return mv;
	}

	@PostMapping("/order")
	public ModelAndView processOrder(@Valid @ModelAttribute("model") ProcessRequest model, BindingResult result,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("orderDetails");
		if (result.hasErrors()) {
			return mv;
		}
		mv.addObject("model", model);
		processRequest = model;
		// call packaging and delivery service to calculate price
		String token = ((String) request.getSession().getAttribute("token")).split(" ")[1];
		String url = "http://localhost:8000/packaging/getPackagingDeliveryCharge/{type}/{count}";
		Map<String, String> map = new HashMap<>();
		map.put("type", model.getComponentType());
		map.put("count", model.getQuantity().toString());
		HttpEntity<String> entity = template.getEntity(token);
		ResponseEntity<PackagingAndDeliveryDTO> r = restTemplate.postForEntity(url, entity,
				PackagingAndDeliveryDTO.class, map);
		mv.addObject("charge", r.getBody().getCharge());
		mv.setViewName("show");
		return mv;
	}

	@PostMapping("/order/confirm")
	public ModelAndView performLogin(HttpServletRequest request) throws FeignException {

		ModelAndView mv = new ModelAndView("orderDetails");
		try {
			ProcessRequest model = processRequest;
			mv.addObject("model", model);
			
			processResponse = processRequestServiceImplObj.processRequestSaveService(model,
					(String) request.getSession().getAttribute("token"));
			
			mv.addObject("response", processResponse);
			paymentStatusDTO = processRequestServiceImplObj.statusDetails(model,
					(String) request.getSession().getAttribute("token"));
			mv.addObject("payment", paymentStatusDTO);
			log.info(model.toString());
			mv.setViewName("cart");
			return mv;

		} catch (Exception e) {
			mv.setViewName("cart");
			return mv;
		}

	}
	
	
	@GetMapping("/track")
	public ModelAndView trackOrderForm() {
		ModelAndView mv=new ModelAndView("track");
		return mv;
	}
	
	@PostMapping("/track")
	public ModelAndView showtrackResult(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("trackResult");
		String id=request.getParameter("id");
		String token=((String) request.getSession().getAttribute("token")).split(" ")[1];
		String result=processRequestServiceImplObj.trackRequest(id, token);
		mv.addObject("result", result);
		return mv;
	}
	
	@GetMapping("/cancel")
	public ModelAndView canceRequestForm() {
		ModelAndView mv=new ModelAndView("cancelOrder");
		return mv;
	}
	
	@PostMapping("/cancel")
	public ModelAndView cancelRequest(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("cancelOrderResult");
		String id=request.getParameter("id");
		String cardNumber = request.getParameter("cardNumber");
		String token=((String) request.getSession().getAttribute("token")).split(" ")[1];
		String result = processRequestServiceImplObj.cancelRequest(id, cardNumber, token);
		mv.addObject("result", result);
		return mv;
	}
}
