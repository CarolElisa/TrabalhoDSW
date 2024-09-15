package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
	
	Usuario findById(long id);
    
    Usuario findByUsername(String username);

	List<Usuario> findAll();
	
	Usuario save(Usuario usuario);

	void deleteById(Long id);
	
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    public Usuario getUserByUsername(@Param("username") String username);

	@Query("SELECT u FROM Usuario u WHERE u.id NOT IN (SELECT e.usuario.id FROM Empresa e) AND u.id NOT IN (SELECT p.usuario.id FROM Profissional p) AND u.role = 'ROLE_EMPR'")
	List<Usuario> findUsuariosSemEmpresa();

	@Query("SELECT u FROM Usuario u WHERE u.id NOT IN (SELECT p.usuario.id FROM Profissional p) AND u.id NOT IN (SELECT e.usuario.id FROM Empresa e) AND u.role = 'ROLE_PROF'")
	List<Usuario> findUsuariosSemProfissional();
}