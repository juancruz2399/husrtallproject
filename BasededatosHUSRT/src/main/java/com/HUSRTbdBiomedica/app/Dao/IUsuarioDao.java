package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query("SELECT COUNT(u) from Usuario u")
    public int countAll();
	
	@Query("SELECT u FROM Usuario u WHERE u.Cedula=?1")
    public Usuario findByCedula(String cedula);
	
	@Query("SELECT u FROM Usuario u "+
			"WHERE u.Tipo_cargo_usuario=3")
	public List<Usuario> tecnauxbiomedicos();
	
	
}
