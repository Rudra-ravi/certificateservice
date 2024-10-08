package com.tns.certificateservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {
    
    @Autowired
    private CertificateRepository repository;

    public List<Certificate> getAllCertificates() {
        return repository.findAll();
    }

    public Certificate getCertificate(Long id) {
        Optional<Certificate> cert = repository.findById(id);
        return cert.orElse(null);
    }

    public Certificate createCertificate(Certificate certificate) {
        return repository.save(certificate);
    }

    public Certificate updateCertificate(Long id, Certificate certificate) {
        if (repository.existsById(id)) {
            certificate.setId(id);
            return repository.save(certificate);
        }
        return null;
    }

    public void deleteCertificate(Long id) {
        repository.deleteById(id);
    }

    public List<Certificate> getCertificatesByStudent(String studentName) {
        return repository.findByStudentName(studentName);
    }
}