package com.epam.esm.controller;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.service.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/certificates",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CertificateDto create(@RequestBody CertificateDto certificateDto) {
        return certificateService.create(certificateDto);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<CertificateDto> getAll() {

        return certificateService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CertificateDto getById(@PathVariable("id") Long id) {

        return certificateService.findById(id);
    }

    @PutMapping
    @ResponseStatus(OK)
    public CertificateDto update(@RequestBody CertificateDto certificateDto) {

        return certificateService.update(certificateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable("id") Long id) {

        certificateService.delete(id);
    }
}
