package com.pods.fclabs.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pods.fclabs.exception.UsuarioExistenteException;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.models.UsuarioResponse;
import com.pods.fclabs.repositories.UsuarioRepository;
import com.pods.fclabs.util.Util;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private Util util;

	@Autowired
	private ValidaCamposObrigatoriosService validaCamposObrigatorios;

	public UsuarioResponse salva(Usuario usuario) throws UsuarioExistenteException {
		try {

			validaCamposObrigatorios.validaCamposObrigatoriosUsuario(usuario);
			usuario.setId(UUID.randomUUID());
			usuario.setDtCriacao(Util.formatarData(new Date()));
			usuario.setDtUltAlteracao(Util.formatarData(new Date()));
			return util.converteUsuarioInResponse(usuarioRepository.save(usuario));

		} catch (UsuarioExistenteException e) {
			throw e;
		}
	}

	public UsuarioResponse atualiza(Usuario usuario) {
		validaCamposObrigatorios.validaIdUsuario(usuario.getId());
		validaCamposObrigatorios.validaCamposObrigatoriosUsuario(usuario);
		usuario.setDtUltAlteracao(Util.formatarData(new Date()));
		return util.converteUsuarioInResponse(usuarioRepository.save(usuario));
	}
	
	public UsuarioResponse findbyidUsuario(UUID id) {
		validaCamposObrigatorios.validaIdUsuario(id);
		return util.converteUsuarioInResponse(usuarioRepository.getById(id));
	}

	public List<UsuarioResponse> findAll() {
		return util.converteListUsuarioInResponse(usuarioRepository.findAll());
	}
	
	public UsuarioResponse remove(UUID id) {
		validaCamposObrigatorios.validaIdUsuario(id);
		usuarioRepository.delete(usuarioRepository.getById(id));
		return util.converteUsuarioInResponse(usuarioRepository.getById(id));
	}
	

}
