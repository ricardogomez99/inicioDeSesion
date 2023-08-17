package inicio.registro.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import inicio.registro.demo.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{

    public Usuario findByEmail(String email);

    
    
}
