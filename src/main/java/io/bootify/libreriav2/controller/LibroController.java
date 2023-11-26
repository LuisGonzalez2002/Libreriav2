package io.bootify.libreriav2.controller;

import io.bootify.libreriav2.model.EstadoLibro;
import io.bootify.libreriav2.model.LibroDTO;
import io.bootify.libreriav2.service.LibroService;
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
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(final LibroService libroService) {
        this.libroService = libroService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoValues", EstadoLibro.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("libroes", libroService.findAll());
        return "libro/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("libro") final LibroDTO libroDTO) {
        return "libro/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("libro") @Valid final LibroDTO libroDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libro/add";
        }
        libroService.create(libroDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libro.create.success"));
        return "redirect:/libros";
    }

    @GetMapping("/edit/{idLibro}")
    public String edit(@PathVariable final Long idLibro, final Model model) {
        model.addAttribute("libro", libroService.get(idLibro));
        return "libro/edit";
    }

    @PostMapping("/edit/{idLibro}")
    public String edit(@PathVariable final Long idLibro,
                       @ModelAttribute("libro") @Valid final LibroDTO libroDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libro/edit";
        }
        libroService.update(idLibro, libroDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libro.update.success"));
        return "redirect:/libros";
    }

    @PostMapping("/delete/{idLibro}")
    public String delete(@PathVariable final Long idLibro, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = libroService.getReferencedWarning(idLibro);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            libroService.delete(idLibro);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("libro.delete.success"));
        }
        return "redirect:/libros";
    }
}
