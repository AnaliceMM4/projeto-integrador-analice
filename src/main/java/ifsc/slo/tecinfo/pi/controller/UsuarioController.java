package ifsc.slo.tecinfo.pi.controller;

import ifsc.slo.tecinfo.pi.modelo.Usuario;
import ifsc.slo.tecinfo.pi.security.ImplementsUserDetailsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author analice
 */
@Controller
@RequestMapping("usuario")

public class UsuarioController {
    
    @Autowired
    private ImplementsUserDetailsService usuarioService;

    @GetMapping("/cadastro")
    public String showRegistrationForm(Model model, Usuario usuario) {
        model.addAttribute("usuario", usuario);
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String registerUserAccount(@Valid Usuario usuario, BindingResult result) {

        usuarioService.save(usuario);
        return "redirect:/index";
    }

}
