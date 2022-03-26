package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class BlogController {
	@Autowired
	BlogRepository blogRepo;
	
	
	 @RequestMapping(value = "index", method = RequestMethod.GET)
	    public String indexBlog(Model model) {
	        model.addAttribute("blogs", blogRepo.findAll());
	        return "index";
	    }
	    
}