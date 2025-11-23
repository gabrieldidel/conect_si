package conectSI.conect_si.controller;

import conectSI.conect_si.services.Firstendpoint;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class FirsEndpointController {
    private final Firstendpoint service;
    @GetMapping("/endpoint")
    public String firstGet(){
        return service.firstGet();
    }

}
