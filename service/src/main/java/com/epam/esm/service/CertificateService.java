package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;

import java.util.List;

public interface CertificateService {

    List<CertificateDto> getAll();

    CertificateDto findById(long id);

    CertificateDto findByName(String name);

    CertificateDto add(CertificateDto certificateDto);

    void delete(long id);

    void delete(String name);

    CertificateDto update(long id);


}
