package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;

@SuppressWarnings("unchecked")
public interface IEmpresaDAO extends CrudRepository<Empresa, Long> {

    Empresa findById(long id);

    Empresa findByCnpj(String cnpj);
    Empresa findByUsuario(Usuario usuario);
	
    List<Empresa> findAll();

    @Query("SELECT v FROM Vaga v WHERE v.empresa.id = :empresaId")
    List<Vaga> findAllVagaByEmpresa(@Param("empresaId") Long empresaId);

    Empresa save(Empresa empresa);

    void deleteById(Long id);
}