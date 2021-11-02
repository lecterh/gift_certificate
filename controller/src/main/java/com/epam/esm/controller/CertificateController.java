package com.epam.esm.controller;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.service.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * The class that represents an API for basic operations with the application
 *
 * @author Aleksey Narkevich
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/certificates",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {

    private final CertificateService certificateService;

    /**
     * Create new {@link CertificateDto} object and returns an {@link CertificateDto} object
     *
     * @param certificateDto - data for creating new {@link CertificateDto} object
     * @return created {@link CertificateDto} object
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public CertificateDto create(@RequestBody CertificateDto certificateDto) {
        return certificateService.create(certificateDto);
    }

    /**
     * Get all Certificates from database
     *
     * @return List of {@link CertificateDto} all tCertificates
     */
    @GetMapping
    @ResponseStatus(OK)
    public List<CertificateDto> getAll() {

        return certificateService.getAll();
    }

    /**
     * Return an object {@link CertificateDto} Certificates
     *
     * @param id - id of {@link CertificateDto} that has retrieved from database
     * @return {@link CertificateDto} an object CertificateDto from database by id
     */
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CertificateDto getById(@PathVariable("id") Long id) {

        return certificateService.findById(id);
    }

    /**
     * Return updated {@link CertificateDto} an object CertificateDto
     *
     * @param certificateDto - new data for updating an {@link CertificateDto} object to be updated
     * @return {@link CertificateDto} contain an updated object
     */
    @PutMapping
    @ResponseStatus(OK)
    public CertificateDto update(@RequestBody CertificateDto certificateDto) {

        return certificateService.update(certificateDto);
    }

    /**
     * Method for delete {@link CertificateDto} from database
     *
     * @param id - id of {@link CertificateDto} that has to be deleted from database.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable("id") Long id) {

        certificateService.delete(id);
    }
}
