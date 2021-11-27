package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/form").setViewName("form");
        registry.addViewController("/appointments").setViewName("appointments");
        registry.addViewController("/editform").setViewName("editform");
    }

    @GetMapping("/")
    public String showMainPage() {
        return "main";
    }

    @GetMapping("/form")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("dentists", dentistVisitService.findAllDentistNames());
        return "form";
    }

    @GetMapping("/appointments")
    public String showVisits(Model model) {
        model.addAttribute("visits", dentistVisitService.findAllVisits());
        return "appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String showEditForm(@PathVariable Long id, DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("dentists", dentistVisitService.findAllDentistNames());
        model.addAttribute("visit", dentistVisitService.findDentistVisitById(id));
        return "editform";
    }

    @PostMapping("/form")
    public String addNewVisit(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        dentistVisitService.addOrUpdateVisit(dentistVisitDTO);
        return "redirect:/";
    }

    @PostMapping("/appointments/edit/{id}")
    public String updateVisit(@PathVariable Long id, @Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editform";
        }

        dentistVisitDTO.setId(id);
        dentistVisitService.addOrUpdateVisit(dentistVisitDTO);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteVisit(@PathVariable Long id) {
        dentistVisitService.deleteVisit(id);
        return "redirect:/appointments";
    }
}
