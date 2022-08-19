package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario>ListarUsuarios();
	public Optional<Usuario>ListUsuariosbyid(Long id);
	public Usuario findOne(Long id);
	public void save(Usuario usuario);
	public void delete(Long id);
	public Usuario findBycc(String term);
	public int countAll();
	
	
	public List<Usuario> tecnauxbiomedico();
}
