package io.bootify.libreriav2.rest;

import io.bootify.libreriav2.model.LectorDTO;
import io.bootify.libreriav2.service.LectorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/lectors", produces = MediaType.APPLICATION_JSON_VALUE)
public class LectorResource {

    private final LectorService lectorService;

    public LectorResource(final LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @GetMapping
    public ResponseEntity<List<LectorDTO>> getAllLectors() {
        return ResponseEntity.ok(lectorService.findAll());
    }

    @GetMapping("/{idLector}")
    public ResponseEntity<LectorDTO> getLector(
            @PathVariable(name = "idLector") final Long idLector) {
        return ResponseEntity.ok(lectorService.get(idLector));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLector(@RequestBody @Valid final LectorDTO lectorDTO) {
        final Long createdIdLector = lectorService.create(lectorDTO);
        return new ResponseEntity<>(createdIdLector, HttpStatus.CREATED);
    }

    @PutMapping("/{idLector}")
    public ResponseEntity<Long> updateLector(@PathVariable(name = "idLector") final Long idLector,
            @RequestBody @Valid final LectorDTO lectorDTO) {
        lectorService.update(idLector, lectorDTO);
        return ResponseEntity.ok(idLector);
    }

    @DeleteMapping("/{idLector}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLector(@PathVariable(name = "idLector") final Long idLector) {
        lectorService.delete(idLector);
        return ResponseEntity.noContent().build();
    }

}
