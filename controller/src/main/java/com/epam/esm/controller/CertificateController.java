package com.epam.esm.controller;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public ResponseEntity<CertificateDto> createCertificate(CertificateDto certificateDto) {
        return ResponseEntity.status(CREATED).body(certificateService.add(certificateDto));
    }


    @PostMapping
    public CertificateDto create (CertificateDto certificateDto) {
        return certificateService.add(certificateDto);
    }

    @GetMapping
    public List<CertificateDto> getAll() {
        return certificateService.getAll();
    }

    @GetMapping("/{id}")
    public CertificateDto getCertificateById(@PathVariable("id") long id) {
        return certificateService.findById(id);
    }

    @PatchMapping
    public CertificateDto updateCertificate(long id) {
        return certificateService.update(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable("id") long id) {
        certificateService.delete(id);
    }

}
