package io.bootify.libreriav2.controller;

import io.bootify.libreriav2.domain.Lector;
import io.bootify.libreriav2.domain.Libro;
import io.bootify.libreriav2.model.PrestamoDTO;
import io.bootify.libreriav2.repos.LectorRepository;
import io.bootify.libreriav2.repos.LibroRepository;
import io.bootify.libreriav2.service.PrestamoService;
import io.bootify.libreriav2.util.CustomCollectors;
import io.bootify.libreriav2.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final LibroRepository libroRepository;
    private final LectorRepository lectorRepository;

    public PrestamoController(final PrestamoService prestamoService,
            final LibroRepository libroRepository, final LectorRepository lectorRepository) {
        this.prestamoService = prestamoService;
        this.libroRepository = libroRepository;
        this.lectorRepository = lectorRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("libroValues", libroRepository.findAll(Sort.by("idLibro"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Libro::getIdLibro, Libro::getAnoPublicacion)));
        model.addAttribute("lectorValues", lectorRepository.findAll(Sort.by("idLector"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Lector::getIdLector, Lector::getNombre)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("prestamoes", prestamoService.findAll());
        return "prestamo/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("prestamo") final PrestamoDTO prestamoDTO) {
        return "prestamo/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("idLibro") && prestamoService.idLibroExists(prestamoDTO.getIdLibro())) {
            bindingResult.rejectValue("idLibro", "Exists.prestamo.idLibro");
        }
        if (!bindingResult.hasFieldErrors("idLector") && prestamoService.idLectorExists(prestamoDTO.getIdLector())) {
            bindingResult.rejectValue("idLector", "Exists.prestamo.idLector");
        }
        if (!bindingResult.hasFieldErrors("libro") && prestamoDTO.getLibro() != null && prestamoService.libroExists(prestamoDTO.getLibro())) {
            bindingResult.rejectValue("libro", "Exists.prestamo.libro");
        }
        if (bindingResult.hasErrors()) {
            return "prestamo/add";
        }
        prestamoService.create(prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.create.success"));
        return "redirect:/prestamos";
    }

    @GetMapping("/edit/{idPrestamo}")
    public String edit(@PathVariable final Long idPrestamo, final Model model) {
        model.addAttribute("prestamo", prestamoService.get(idPrestamo));
        return "prestamo/edit";
    }

    @PostMapping("/edit/{idPrestamo}")
    public String edit(@PathVariable final Long idPrestamo,
            @ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final PrestamoDTO currentPrestamoDTO = prestamoService.get(idPrestamo);
        if (!bindingResult.hasFieldErrors("idLibro") &&
                !prestamoDTO.getIdLibro().equals(currentPrestamoDTO.getIdLibro()) &&
                prestamoService.idLibroExists(prestamoDTO.getIdLibro())) {
            bindingResult.rejectValue("idLibro", "Exists.prestamo.idLibro");
        }
        if (!bindingResult.hasFieldErrors("idLector") &&
                !prestamoDTO.getIdLector().equals(currentPrestamoDTO.getIdLector()) &&
                prestamoService.idLectorExists(prestamoDTO.getIdLector())) {
            bindingResult.rejectValue("idLector", "Exists.prestamo.idLector");
        }
        if (!bindingResult.hasFieldErrors("libro") && prestamoDTO.getLibro() != null &&
                !prestamoDTO.getLibro().equals(currentPrestamoDTO.getLibro()) &&
                prestamoService.libroExists(prestamoDTO.getLibro())) {
            bindingResult.rejectValue("libro", "Exists.prestamo.libro");
        }
        if (bindingResult.hasErrors()) {
            return "prestamo/edit";
        }
        prestamoService.update(idPrestamo, prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.update.success"));
        return "redirect:/prestamos";
    }

    @PostMapping("/delete/{idPrestamo}")
    public String delete(@PathVariable final Long idPrestamo,
            final RedirectAttributes redirectAttributes) {
        prestamoService.delete(idPrestamo);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("prestamo.delete.success"));
        return "redirect:/prestamos";
    }

}
