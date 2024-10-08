package com.tns.certificateservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CertificateserviceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CertificateService certificateService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllCertificates() {
        // given
        Certificate certificate = new Certificate("John Doe", "Spring Boot", "2023-09-01", "A");
        List<Certificate> allCertificates = Collections.singletonList(certificate);
        given(certificateService.getAllCertificates()).willReturn(allCertificates);

        // when
        ResponseEntity<Certificate[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/certificates", Certificate[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1).contains(certificate);
    }

    @Test
    void testGetCertificateById() {
        // given
        Certificate certificate = new Certificate("John Doe", "Spring Boot", "2023-09-01", "A");
        given(certificateService.getCertificate(1L)).willReturn(certificate);

        // when
        ResponseEntity<Certificate> response = restTemplate.getForEntity("http://localhost:" + port + "/api/certificates/1", Certificate.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(certificate);
    }

    @Test
    void testCreateCertificate() {
        // given
        Certificate certificate = new Certificate("John Doe", "Spring Boot", "2023-09-01", "A");
        given(certificateService.createCertificate(certificate)).willReturn(certificate);

        // when
        ResponseEntity<Certificate> response = restTemplate.postForEntity("http://localhost:" + port + "/api/certificates", certificate, Certificate.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(certificate);
    }

    @Test
    void testUpdateCertificate() {
        // given
        Certificate certificate = new Certificate("John Doe", "Spring Boot", "2023-09-01", "A+");
        given(certificateService.updateCertificate(1L, certificate)).willReturn(certificate);

        // when
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Certificate> entity = new HttpEntity<>(certificate, headers);
        ResponseEntity<Certificate> response = restTemplate.exchange("http://localhost:" + port + "/api/certificates/1", HttpMethod.PUT, entity, Certificate.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(certificate);
    }

    @Test
    void testDeleteCertificate() {
        // given
        Certificate certificate = new Certificate("John Doe", "Spring Boot", "2023-09-01", "A");
        given(certificateService.getCertificate(1L)).willReturn(certificate);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/api/certificates/1", HttpMethod.DELETE, null, Void.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGetCertificatesByStudent() {
        // given
        Certificate certificate = new Certificate("John Doe", "Spring Boot", "2023-09-01", "A");
        List<Certificate> certificates = Collections.singletonList(certificate);
        given(certificateService.getCertificatesByStudent("John Doe")).willReturn(certificates);

        // when
        ResponseEntity<Certificate[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/certificates/student/John Doe", Certificate[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1).contains(certificate);
    }
}
