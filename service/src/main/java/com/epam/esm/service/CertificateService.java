package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;

import java.util.List;

public interface CertificateService {

    List<CertificateDto> getAll();

    CertificateDto findById(Long id);

    CertificateDto create(CertificateDto certificateDto);

    void delete(Long id);

    CertificateDto update(CertificateDto certificateDto);


}

