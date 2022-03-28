package com.example;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	 
	 @RequestMapping(value = "newblog", method = RequestMethod.GET)
	    public ModelAndView blogs() {
	        ModelAndView mav = new ModelAndView("newblog");
	        return mav;
	    }

	    @RequestMapping(value = "createnewblog", method = RequestMethod.GET)
	    public ModelAndView createnewblog(Blog blog) {
	        ModelAndView mav = new ModelAndView("redirect:/index");
	        blogRepo.save(blog);
	        return mav;
	    }
	    
	    @RequestMapping(value="/detail/{id}",method= RequestMethod.GET)
		public ModelAndView detailBlog(@PathVariable("id") int id) {
			ModelAndView mav = new ModelAndView();
			Blog blog=blogRepo.findById(id).get();
		    mav.addObject("blog",blog);
			mav.setViewName("detail");
			return mav;
		}
	    
	    @RequestMapping(value="/update/{id}",method= RequestMethod.GET)
		public ModelAndView updateBlog(@PathVariable("id") int id) {
			ModelAndView mav = new ModelAndView();
			Blog blog=blogRepo.findById(id).get();
		    mav.addObject("blog",blog);
			mav.setViewName("edit");
			return mav;
		}
		
		@RequestMapping("/update/updatedata/{id}")
		public ModelAndView updateBlogAction(@PathVariable("id") int id , Blog b) {
			ModelAndView mav = new ModelAndView();
			Blog blog=blogRepo.findById(id).get();
			blog.setTitle(b.getTitle());
			blog.setContent(b.getContent());
			blogRepo.save(blog);
			mav.setViewName("redirect:/index");
			return mav;
		}
		
		@RequestMapping(value="/delete/{id}",method= RequestMethod.GET)
		public ModelAndView deleteBlog(@PathVariable("id") Integer id) {
			ModelAndView mav = new ModelAndView();
		    blogRepo.deleteById(id);
			mav.setViewName("redirect:/index");
			return mav;
		}
	    
		
}