package com.epam.esm.controller;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.service.CertificateService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

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
