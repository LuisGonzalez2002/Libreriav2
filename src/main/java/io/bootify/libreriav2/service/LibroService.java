package io.bootify.libreriav2.service;

import io.bootify.libreriav2.domain.Libro;
import io.bootify.libreriav2.domain.Prestamo;
import io.bootify.libreriav2.model.LibroDTO;
import io.bootify.libreriav2.repos.LibroRepository;
import io.bootify.libreriav2.repos.PrestamoRepository;
import io.bootify.libreriav2.util.NotFoundException;
import io.bootify.libreriav2.util.WebUtils;
import java.util.List;
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
        final List<Libro> libroes = libroRepository.findAll(Sort.by("idLibro"));
        return libroes.stream()
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .toList();
    }

    public LibroDTO get(final Long idLibro) {
        return libroRepository.findById(idLibro)
                .map(libro -> mapToDTO(libro, new LibroDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LibroDTO libroDTO) {
        final Libro libro = new Libro();
        mapToEntity(libroDTO, libro);
        return libroRepository.save(libro).getIdLibro();
    }

    public void update(final Long idLibro, final LibroDTO libroDTO) {
        final Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(NotFoundException::new);
        mapToEntity(libroDTO, libro);
        libroRepository.save(libro);
    }

    public void delete(final Long idLibro) {
        libroRepository.deleteById(idLibro);
    }

    private LibroDTO mapToDTO(final Libro libro, final LibroDTO libroDTO) {
        libroDTO.setIdLibro(libro.getIdLibro());
        libroDTO.setIdAutor(libro.getIdAutor());
        libroDTO.setAnoPublicacion(libro.getAnoPublicacion());
        libroDTO.setTitulo(libro.getTitulo());
        libroDTO.setGenero(libro.getGenero());
        libroDTO.setStock(libro.getStock());
        return libroDTO;
    }

    private Libro mapToEntity(final LibroDTO libroDTO, final Libro libro) {
        libro.setIdAutor(libroDTO.getIdAutor());
        libro.setAnoPublicacion(libroDTO.getAnoPublicacion());
        libro.setTitulo(libroDTO.getTitulo());
        libro.setGenero(libroDTO.getGenero());
        libro.setStock(libroDTO.getStock());
        return libro;
    }

    public boolean idAutorExists(final Long idAutor) {
        return libroRepository.existsByIdAutor(idAutor);
    }

    public String getReferencedWarning(final Long idLibro) {
        final Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(NotFoundException::new);
        final Prestamo libroPrestamo = prestamoRepository.findFirstByLibro(libro);
        if (libroPrestamo != null) {
            return WebUtils.getMessage("libro.prestamo.libro.referenced", libroPrestamo.getIdPrestamo());
        }
        return null;
    }

}
