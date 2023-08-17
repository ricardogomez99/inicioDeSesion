package inicio.registro.demo.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import inicio.registro.demo.dto.UsuarioRegistroDTO;
import inicio.registro.demo.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService{

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();

    
    
}
