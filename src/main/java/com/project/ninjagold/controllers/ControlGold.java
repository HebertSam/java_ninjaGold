package com.project.ninjagold.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControlGold{

	public ControlGold(){

	}
	public static String getDateFormat(){
        Date date = new Date();
        SimpleDateFormat dateFM = new SimpleDateFormat("M d yyyy, hh:mm");
        String strDate = dateFM.format(date);
        return strDate;
    }
	
	@RequestMapping("")
	public String index(HttpSession session, Model model){
		if (session.getAttribute("gold") == null){
			session.setAttribute("gold", 0);
		}
		if (session.getAttribute("eventList") == null){
			ArrayList<String> events = new ArrayList<String>();
			session.setAttribute("eventList", events);
		}
		int totalGold = ((int) session.getAttribute("gold"));
		model.addAttribute("gold", totalGold);
		ArrayList<String> events = ((ArrayList<String>) session.getAttribute("eventList"));
		model.addAttribute("events", events);
		return "index";
	}
	@PostMapping("/getgold")
	public String getGold(@RequestParam("building") String building, HttpSession session, Model model){
		// System.out.println(building);
		ArrayList<String> eventList = new ArrayList<String>();
		eventList = ((ArrayList<String>) session.getAttribute("eventList"));
		Random val = new Random();
		int gold = 0;
		int totalGold = ((int) session.getAttribute("gold"));
		if (building.equals("farm")){
			gold = (val.nextInt(20 - 10)+1)+10;
			System.out.println(gold);
			totalGold += gold;
			session.setAttribute("gold", totalGold);
		} else if (building.equals("cave")){
			gold = (val.nextInt(10 - 5) + 1)+5;
			System.out.println(gold);
			totalGold += gold;
			session.setAttribute("gold", totalGold);
		} else if (building.equals("house")){
			gold += (val.nextInt(5 - 2) + 1)+2;
			System.out.println(gold);
			totalGold += gold;
			session.setAttribute("gold", totalGold);
		} else if (building.equals("casino")){
			gold = (val.nextInt(100 - 50) + 1) - 50;
			System.out.println(gold);
			totalGold += gold;
			session.setAttribute("gold", totalGold);
			if (gold < 0){
				eventList.add("You entered a " + building + " and lost " + gold + " gold ");
				return "redirect:/";
			}


		}
		// if (session.getAttribute("eventList") == null){
		// 	session.setAttribute("eventList", eventList);
		// }
		eventList.add("You entered a " + building + " and earned " + gold + " gold ");
		System.out.println(eventList);
		session.setAttribute("eventList", eventList);
		return "redirect:/";
	}
	@RequestMapping("/reset")
	public String reset(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}
