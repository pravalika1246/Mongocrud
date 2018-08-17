package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.ContactDAO;
import net.codejava.spring.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller routes accesses to the application to the appropriate hanlder
 * methods.
 * 
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {

	@Autowired
	private ContactDAO contactDAO;

	@RequestMapping(value = "/")
	public ModelAndView listContact(ModelAndView model) throws IOException {
		List<Contact> listContact = contactDAO.list();
		model.addObject("listContact", listContact);
		model.setViewName("home");

		return model;
	}

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		contactDAO.saveOrUpdate(contact);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		// int contactId = Integer.parseInt(request.getParameter("email"));
		String contactEmail = request.getParameter("email");
		contactDAO.delete(contactEmail);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		String contactEmail = request.getParameter("email");
		Contact contact = contactDAO.get(contactEmail);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);
		model.setViewName("update");
		return model;
		
		}
	@RequestMapping(value = "/updateContact", method = RequestMethod.GET)
	public ModelAndView updateContact(@ModelAttribute Contact contact) {
		contactDAO.update(contact);
		return new ModelAndView("redirect:/");
	
	}
}
// @RequestMapping(value = "/editContact", method = RequestMethod.POST)
// public ModelAndView editContact(@ModelAttribute Contact contact) {
// if(contact.getEmail() != null && !contact.getEmail().trim().equals("")) {
// contactDAO.saveOrUpdate(contact);
// } else {
// contactDAO.saveOrUpdate(contact);
// }
// return new ModelAndView("redirect:/");
// }

// @RequestMapping(value = "/editContact", method = RequestMethod.GET)
// public ModelAndView editContact(HttpServletRequest request) {
//// int contactId = Integer.parseInt(request.getParameter("email"));
// String contactEmail=request.getParameter("email");
// Contact contact = contactDAO.get(contactEmail);
// ModelAndView model = new ModelAndView("ContactForm");
// model.addObject("contact", contact);
//
// }
