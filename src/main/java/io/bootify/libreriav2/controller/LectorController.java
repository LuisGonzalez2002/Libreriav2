package io.bootify.libreriav2.controller;

import io.bootify.libreriav2.model.LectorDTO;
import io.bootify.libreriav2.service.LectorService;
import io.bootify.libreriav2.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/lectors")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(final LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("lectors", lectorService.findAll());
        return "lector/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("lector") final LectorDTO lectorDTO) {
        return "lector/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("lector") @Valid final LectorDTO lectorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "lector/add";
        }
        lectorService.create(lectorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("lector.create.success"));
        return "redirect:/lectors";
    }

    @GetMapping("/edit/{idLector}")
    public String edit(@PathVariable final Long idLector, final Model model) {
        model.addAttribute("lector", lectorService.get(idLector));
        return "lector/edit";
    }

    @PostMapping("/edit/{idLector}")
    public String edit(@PathVariable final Long idLector,
            @ModelAttribute("lector") @Valid final LectorDTO lectorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "lector/edit";
        }
        lectorService.update(idLector, lectorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("lector.update.success"));
        return "redirect:/lectors";
    }

    @PostMapping("/delete/{idLector}")
    public String delete(@PathVariable final Long idLector,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = lectorService.getReferencedWarning(idLector);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            lectorService.delete(idLector);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("lector.delete.success"));
        }
        return "redirect:/lectors";
    }

}
