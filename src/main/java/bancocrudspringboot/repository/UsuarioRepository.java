package bancocrudspringboot.repository;

import bancocrudspringboot.model.Usuario;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
     @Query(value = "select * from usuario where telefone ilike concat('%', :telefone, '%')", nativeQuery = true)
    List<Usuario> findUsuarioByTelefone(@Param("telefone")String telefone);


    Optional<Usuario> findUsuarioById(long id);
    
    List<Usuario> findUsuarioByTelefone(int tefone);

    List<Usuario> findUsuarioBySenha(String Senha);

    @Query(value = "select * from usuario where cidade ilike concat('%', :cidade, '%')", nativeQuery = true)
    List<Usuario> findUsuarioByCidade(@Param("cidade")String cidade);

    
    // MÉTODO PARA CONSULTA EM USUARIO CONTROLLER
    // List<Usuario> findUsuarioByTipo(int int1);
    // Optional<Usuario> findUsuarioById(long long1);

    // @Query(value = "select * from usuario where modelo ilike concat('%', :modelo, '%')", nativeQuery = true)
    // List<Veiculo> findVeiculoByModelo(@Param("modelo")String modelo);

}
