package com.example;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	    public String blogs() {
	        return "newblog";
	    }

	    @RequestMapping(value = "createnewblog", method = RequestMethod.GET)
	    public String createnewblog(Blog blog) {
	        blogRepo.save(blog);
	        return "redirect:/index";
	    }
	    
	    @RequestMapping(value="/detail/{id}",method= RequestMethod.GET)
		public String detailBlog(@PathVariable("id") int id, Model model) {
			Blog blog=blogRepo.findById(id).get();
		    model.addAttribute("blog",blog);
			return "detail";
		}
	    
	    @RequestMapping(value="/update/{id}",method= RequestMethod.GET)
		public String updateBlog(@PathVariable("id") int id, Model model) {
			Blog blog=blogRepo.findById(id).get();
		    model.addAttribute("blog",blog);
			return "edit";
		}
		
		@RequestMapping("/update/updatedata/{id}")
		public String updateBlogAction(@PathVariable("id") int id , Blog b) {
			Blog blog=blogRepo.findById(id).get();
			blog.setTitle(b.getTitle());
			blog.setContent(b.getContent());
			blogRepo.save(blog);
			return "redirect:/index";
		}
		
		@RequestMapping(value="/delete/{id}",method= RequestMethod.GET)
		public String deleteBlog(@PathVariable("id") Integer id, Model model) {
		    blogRepo.deleteById(id);
			return "redirect:/index";
		}
	    
		
}