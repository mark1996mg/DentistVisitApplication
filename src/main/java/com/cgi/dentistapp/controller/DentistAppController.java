package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    private static final String ERROR_MESSAGE = "This time is booked, please choose another time";

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
    public String showRegisterForm(Model model) {
        if (!model.containsAttribute("dentistVisitDTO")) {
            model.addAttribute("dentistVisitDTO", new DentistVisitDTO());
        }
        model.addAttribute("dentists", dentistVisitService.findAllDentistNames());
        return "form";
    }

    @GetMapping("/appointments/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("dentistVisitDTO")) {
            model.addAttribute("dentistVisitDTO", dentistVisitService.findDentistVisitById(id));
        }
        model.addAttribute("dentists", dentistVisitService.findAllDentistNames());
        return "editform";
    }

    @GetMapping("/appointments")
    public String showVisits(Model model) {
        model.addAttribute("visits", dentistVisitService.findAllVisits());
        return "appointments";
    }

    @PostMapping("/form")
    public String addNewVisit(@Valid @ModelAttribute("dentistVisitDTO") DentistVisitDTO dentistVisitDTO,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        return isInputDataValid(dentistVisitDTO, bindingResult, redirectAttributes)
            ? "redirect:/"
            : "redirect:/form";
    }

    @PostMapping("/appointments/edit/{id}")
    public String updateVisit(
        @PathVariable Long id,
        @Valid @ModelAttribute("visit") DentistVisitDTO dentistVisitDTO,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        dentistVisitDTO.setId(id);
        return isInputDataValid(dentistVisitDTO, bindingResult, redirectAttributes)
            ? "redirect:/appointments"
            : String.format("redirect:/appointments/edit/%s", id);
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteVisit(@PathVariable Long id) {
        dentistVisitService.deleteVisit(id);
        return "redirect:/appointments";
    }

    private boolean isInputDataValid(DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dentistVisitDTO", bindingResult);
            redirectAttributes.addFlashAttribute("dentistVisitDTO", dentistVisitDTO);
            return false;
        }
        if (isVisitAlreadyBooked(dentistVisitDTO)) {
            redirectAttributes.addFlashAttribute("dentistVisitDTO", dentistVisitDTO);
            redirectAttributes.addFlashAttribute("errorMessage", ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isVisitAlreadyBooked(DentistVisitDTO dentistVisitDTO) {
        try {
            dentistVisitService.addOrUpdateVisit(dentistVisitDTO);
        } catch (DataIntegrityViolationException e) {
            return true;
        }
        return false;
    }
}
