package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.MenuService;
import com.example.demo.domain.Menu;



@Controller
@RequestMapping("/menus")
public class MenuController {

	
	@Autowired
	private MenuService menuService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("menus",menuService.search());
		return "index";
	}
	@GetMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute("menu",menuService.search(id));
		return "show";
	}
	@GetMapping("/new")
	public String newMwnu(@ModelAttribute("menu") Menu menu,Model model) {
		return "new";
	}
	@GetMapping("{id}/edit")
	public String edit(@PathVariable int id,@ModelAttribute("menu")Menu menu,Model model) {
		model.addAttribute("menu",menuService.search(id));
		return "edit";
	}
	@PostMapping
	public String create(@ModelAttribute("menu")@Validated Menu menu,BindingResult result,Model model) {
		if(result.hasErrors()) {
		  return "new";
	    }else {
	      menuService.register(menu);
	      return "redirect:/menus";
		
	}
	
    
}
	@PutMapping("{id}")
	public String update(@PathVariable int id,@ModelAttribute("menu") @Validated Menu menu,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("menu",menu);
			return "edit";
		}else {
			menu.setId(id);
			menuService.update(menu);
			return "redirect:/menus";
		}
	}
	@DeleteMapping("{id}")
	public String delete(@PathVariable int id) {
		menuService.delete(id);
		return "redirect:/menus";
	}
}
