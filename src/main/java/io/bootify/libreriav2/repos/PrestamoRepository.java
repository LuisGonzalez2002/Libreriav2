package io.bootify.libreriav2.repos;

import io.bootify.libreriav2.domain.Lector;
import io.bootify.libreriav2.domain.Libro;
import io.bootify.libreriav2.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    Prestamo findFirstByLibro(Libro libro);

    Prestamo findFirstByLector(Lector lector);

}
