package io.bootify.libreriav2.service;

import io.bootify.libreriav2.domain.Lector;
import io.bootify.libreriav2.domain.Prestamo;
import io.bootify.libreriav2.model.LectorDTO;
import io.bootify.libreriav2.repos.LectorRepository;
import io.bootify.libreriav2.repos.PrestamoRepository;
import io.bootify.libreriav2.util.NotFoundException;
import io.bootify.libreriav2.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LectorService {

    private final LectorRepository lectorRepository;
    private final PrestamoRepository prestamoRepository;

    public LectorService(final LectorRepository lectorRepository,
            final PrestamoRepository prestamoRepository) {
        this.lectorRepository = lectorRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<LectorDTO> findAll() {
        final List<Lector> lectors = lectorRepository.findAll(Sort.by("idLector"));
        return lectors.stream()
                .map(lector -> mapToDTO(lector, new LectorDTO()))
                .toList();
    }

    public LectorDTO get(final Long idLector) {
        return lectorRepository.findById(idLector)
                .map(lector -> mapToDTO(lector, new LectorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LectorDTO lectorDTO) {
        final Lector lector = new Lector();
        mapToEntity(lectorDTO, lector);
        return lectorRepository.save(lector).getIdLector();
    }

    public void update(final Long idLector, final LectorDTO lectorDTO) {
        final Lector lector = lectorRepository.findById(idLector)
                .orElseThrow(NotFoundException::new);
        mapToEntity(lectorDTO, lector);
        lectorRepository.save(lector);
    }

    public void delete(final Long idLector) {
        lectorRepository.deleteById(idLector);
    }

    private LectorDTO mapToDTO(final Lector lector, final LectorDTO lectorDTO) {
        lectorDTO.setIdLector(lector.getIdLector());
        lectorDTO.setNombre(lector.getNombre());
        lectorDTO.setApellido(lector.getApellido());
        lectorDTO.setTelefono(lector.getTelefono());
        return lectorDTO;
    }

    private Lector mapToEntity(final LectorDTO lectorDTO, final Lector lector) {
        lector.setNombre(lectorDTO.getNombre());
        lector.setApellido(lectorDTO.getApellido());
        lector.setTelefono(lectorDTO.getTelefono());
        return lector;
    }

    public String getReferencedWarning(final Long idLector) {
        final Lector lector = lectorRepository.findById(idLector)
                .orElseThrow(NotFoundException::new);
        final Prestamo lectorPrestamo = prestamoRepository.findFirstByLector(lector);
        if (lectorPrestamo != null) {
            return WebUtils.getMessage("lector.prestamo.lector.referenced", lectorPrestamo.getIdPrestamo());
        }
        return null;
    }

}
