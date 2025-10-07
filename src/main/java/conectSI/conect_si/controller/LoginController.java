package conectSI.conect_si.controller;

import conectSI.conect_si.model.dto.LoginDTO;
import conectSI.conect_si.repository.UsuarioRepository;
import conectSI.conect_si.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public Boolean login(
            @RequestBody LoginDTO loginDTO
    ){
        return loginService.login(loginDTO);
    }
}
