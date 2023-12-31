package io.bootify.libreriav2.service;

import io.bootify.libreriav2.domain.Libro;
import io.bootify.libreriav2.domain.Prestamo;
import io.bootify.libreriav2.model.EstadoLibro;
import io.bootify.libreriav2.model.LibroDTO;
import io.bootify.libreriav2.repos.LibroRepository;
import io.bootify.libreriav2.repos.PrestamoRepository;
import io.bootify.libreriav2.util.NotFoundException;
import io.bootify.libreriav2.util.WebUtils;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;

    public LibroService(final LibroRepository libroRepository,
                        final PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<LibroDTO> findAll() {
        final List<Libro> libros = libroRepository.findAll(Sort.by("idLibro"));
        return libros.stream()
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .toList();
    }

    public LibroDTO get(final Long id) {
        return libroRepository.findById(id)
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LibroDTO libroDTO) {
        final Libro libro = new Libro();
        mapToEntity(libroDTO, libro);
        return libroRepository.save(libro).getIdLibro();
    }


    public void update(final Long id, final LibroDTO libroDTO) {
        final Libro libro = libroRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(libroDTO, libro);
        libroRepository.save(libro);
    }

    public void delete(final Long id) {
        libroRepository.deleteById(id);
    }

    private LibroDTO mapToDTO(final Libro libro, final LibroDTO libroDTO) {
        libroDTO.setIdLibro(libro.getIdLibro());
        libroDTO.setTitulo(libro.getTitulo());
        libroDTO.setAutor(libro.getAutor());
        libroDTO.setGenero(libro.getGenero());
        libroDTO.setEstado(libro.getEstado());
        return libroDTO;
    }

    private Libro mapToEntity(final LibroDTO libroDTO, final Libro libro) {
        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        libro.setGenero(libroDTO.getGenero());
        libro.setEstado(libroDTO.getEstado());
        return libro;
    }

    public String getReferencedWarning(final Long id) {
        final Libro libro = libroRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Prestamo libroPrestamo = prestamoRepository.findFirstByLibro(libro);
        if (libroPrestamo != null) {
            return WebUtils.getMessage("libro.prestamo.libro.referenced", libroPrestamo.getId());
        }
        return null;
    }

}
