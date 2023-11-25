package io.bootify.libreriav2.service;

import io.bootify.libreriav2.domain.Lector;
import io.bootify.libreriav2.domain.Libro;
import io.bootify.libreriav2.domain.Prestamo;
import io.bootify.libreriav2.model.PrestamoDTO;
import io.bootify.libreriav2.repos.LectorRepository;
import io.bootify.libreriav2.repos.LibroRepository;
import io.bootify.libreriav2.repos.PrestamoRepository;
import io.bootify.libreriav2.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final LectorRepository lectorRepository;

    public PrestamoService(final PrestamoRepository prestamoRepository,
            final LibroRepository libroRepository, final LectorRepository lectorRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.lectorRepository = lectorRepository;
    }

    public List<PrestamoDTO> findAll() {
        final List<Prestamo> prestamoes = prestamoRepository.findAll(Sort.by("idPrestamo"));
        return prestamoes.stream()
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .toList();
    }

    public PrestamoDTO get(final Long idPrestamo) {
        return prestamoRepository.findById(idPrestamo)
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = new Prestamo();
        mapToEntity(prestamoDTO, prestamo);
        return prestamoRepository.save(prestamo).getIdPrestamo();
    }

    public void update(final Long idPrestamo, final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(NotFoundException::new);
        mapToEntity(prestamoDTO, prestamo);
        prestamoRepository.save(prestamo);
    }

    public void delete(final Long idPrestamo) {
        prestamoRepository.deleteById(idPrestamo);
    }

    private PrestamoDTO mapToDTO(final Prestamo prestamo, final PrestamoDTO prestamoDTO) {
        prestamoDTO.setIdPrestamo(prestamo.getIdPrestamo());
        prestamoDTO.setIdLibro(prestamo.getIdLibro());
        prestamoDTO.setIdLector(prestamo.getIdLector());
        prestamoDTO.setFechaPrestamo(prestamo.getFechaPrestamo());
        prestamoDTO.setFechaDevolucion(prestamo.getFechaDevolucion());
        prestamoDTO.setLibro(prestamo.getLibro() == null ? null : prestamo.getLibro().getIdLibro());
        prestamoDTO.setLector(prestamo.getLector() == null ? null : prestamo.getLector().getIdLector());
        return prestamoDTO;
    }

    private Prestamo mapToEntity(final PrestamoDTO prestamoDTO, final Prestamo prestamo) {
        prestamo.setIdLibro(prestamoDTO.getIdLibro());
        prestamo.setIdLector(prestamoDTO.getIdLector());
        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        final Libro libro = prestamoDTO.getLibro() == null ? null : libroRepository.findById(prestamoDTO.getLibro())
                .orElseThrow(() -> new NotFoundException("libro not found"));
        prestamo.setLibro(libro);
        final Lector lector = prestamoDTO.getLector() == null ? null : lectorRepository.findById(prestamoDTO.getLector())
                .orElseThrow(() -> new NotFoundException("lector not found"));
        prestamo.setLector(lector);
        return prestamo;
    }

    public boolean idLibroExists(final Long idLibro) {
        return prestamoRepository.existsByIdLibro(idLibro);
    }

    public boolean idLectorExists(final Long idLector) {
        return prestamoRepository.existsByIdLector(idLector);
    }

    public boolean libroExists(final Long idLibro) {
        return prestamoRepository.existsByLibroIdLibro(idLibro);
    }

}
