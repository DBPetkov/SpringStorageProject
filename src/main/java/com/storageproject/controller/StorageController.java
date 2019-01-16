package com.storageproject.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.storageproject.model.Storage;
import com.storageproject.service.StorageService;

@Controller
public class StorageController {

	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "admin/storage", method = RequestMethod.GET)
	public ModelAndView getStorageItems(@RequestParam(name = "searchName", required = false)String searchName,@RequestParam(name = "category", required = false)String category) {
		List<Storage> storage = 
				( (searchName!=null&&!searchName.isEmpty()) || (category!=null)&&!category.isEmpty() )
				 ? storageService.search(searchName, category) : storageService.getAllItems();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("storage", storage);
		modelAndView.setViewName("admin/storage");
		return modelAndView;
	}

	@RequestMapping(value = "admin/add", method = RequestMethod.GET)
	public ModelAndView addStorageItem() {
		Storage storage = new Storage();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("storage", storage);
		modelAndView.setViewName("admin/add");
		return modelAndView;
	}

	@RequestMapping(value = "admin/add", method = RequestMethod.POST)
	public ModelAndView saveStorageItem(@Valid Storage storage, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Storage itemCodeExists = storageService.findByCode(storage.getProductCode());
		if (itemCodeExists != null) {
			bindingResult.rejectValue("productCode", "error.storage",
					"There is already a product registered with the same code!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/add");
		} else {
			storageService.saveItem(storage);
			modelAndView.addObject("successMessage", "The product has been successfully submited!");
			modelAndView.addObject("storage", new Storage());
			modelAndView.setViewName("admin/add");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "admin/edit", method = RequestMethod.POST)
	public ModelAndView updateStorageItem(@Valid Storage storage, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/edit");
		} else {
			storageService.saveItem(storage);
			modelAndView.addObject("successMessage", "The product has been successfully submited!");
			modelAndView.addObject("storage", new Storage());
			modelAndView.setViewName("admin/edit");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="admin/storage/delete/{id}")
	public String deleteStorageItem(@PathVariable Long id, RedirectAttributes redirectAtt) {
		storageService.delete(id);
		redirectAtt.addFlashAttribute("message", "Item was deleted!");
		return "redirect:/admin/storage";
	}
	
	@RequestMapping(value="admin/storage/edit/{id}")
	public ModelAndView editStorageItem(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("storage", storageService.get(id));
		modelAndView.setViewName("admin/edit");
		return modelAndView;
	}
	

}