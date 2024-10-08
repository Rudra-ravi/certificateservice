package com.tns.certificateservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificateController {

    @Autowired
    private CertificateService service;

    @GetMapping
    public List<Certificate> getAllCertificates() {
        return service.getAllCertificates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long id) {
        Certificate cert = service.getCertificate(id);
        if (cert != null) {
            return ResponseEntity.ok(cert);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Certificate createCertificate(@RequestBody Certificate certificate) {
        return service.createCertificate(certificate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable Long id, @RequestBody Certificate certificate) {
        Certificate updatedCert = service.updateCertificate(id, certificate);
        if (updatedCert != null) {
            return ResponseEntity.ok(updatedCert);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        service.deleteCertificate(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentName}")
    public List<Certificate> getCertificatesByStudent(@PathVariable String studentName) {
        return service.getCertificatesByStudent(studentName);
    }
}
