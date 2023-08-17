package inicio.registro.demo.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import inicio.registro.demo.dto.UsuarioRegistroDTO;
import inicio.registro.demo.modelo.Rol;
import inicio.registro.demo.modelo.Usuario;
import inicio.registro.demo.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(), registroDTO.getEmail(), 
           passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepositorio.save(usuario);
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = usuarioRepositorio.findByEmail(username);
       if(usuario== null){
        throw new UsernameNotFoundException("Usuario y password invalidos");
       }
       return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }


    
}
