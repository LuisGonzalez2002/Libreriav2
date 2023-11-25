package io.bootify.libreriav2.repos;

import io.bootify.libreriav2.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibroRepository extends JpaRepository<Libro, Long> {

    boolean existsByIdAutor(Long idAutor);

}
