package net.orclmvn;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class MasterController {
	
	
	@Autowired
	private UserService userService;
	

	@RequestMapping("/")
	public String usermain(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "user";
	}
	
	@RequestMapping("/user")
	public String user(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "user";
	}
	
    @RequestMapping("/new")
    public String showNewUser(Model model) {
    	User user = new User();
        model.addAttribute("user", user);
         
        return "useradd";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
         
        return "redirect:/";
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteCSR(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/";       
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("useredit");
        User tbl_user = userService.get(id);
        mav.addObject("tbl_user", tbl_user);
         
        return mav;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateUser(@ModelAttribute("tbl_user") User tbl_user) {
        userService.save(tbl_user);
         
        return "redirect:/";
    }
	

	@ModelAttribute("brands")
	public List<String> initializeSections() {

		List<String> brands = new ArrayList<String>();
		brands.add("PANASONIC");
		brands.add("SAMSUNG");
		brands.add("SANYO");
		return brands;
	}
}

