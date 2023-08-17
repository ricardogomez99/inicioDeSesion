package inicio.registro.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import inicio.registro.demo.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }

    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo){
        modelo.addAttribute("usuarios",servicio.listarUsuarios());
        return "index";
    }
    
}
