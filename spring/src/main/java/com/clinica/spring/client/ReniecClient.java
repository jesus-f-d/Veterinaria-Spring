package com.clinica.spring.client;

import com.clinica.spring.dto.response.ReniecResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reniec-client", url = "https://api.decolecta.com/v1/reniec")
public interface ReniecClient {

    @GetMapping("/dni")
    ReniecResponse consultarDni(
            @RequestParam("numero") String numero,
            @RequestHeader("Authorization") String authorization
    );
}