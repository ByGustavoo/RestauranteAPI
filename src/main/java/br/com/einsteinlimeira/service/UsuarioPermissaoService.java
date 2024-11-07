package br.com.einsteinlimeira.service;

import br.com.einsteinlimeira.exceptions.ResourceNotFoundException;
import br.com.einsteinlimeira.model.dto.UsuarioPermissaoDTO;
import br.com.einsteinlimeira.model.entity.UsuarioPermissao;
import br.com.einsteinlimeira.repository.UsuarioPermissaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioPermissaoService {

    @Autowired
    private UsuarioPermissaoRepository usuarioPermissaoRepository;

    public List<UsuarioPermissaoDTO> getAllUsersPermissions() {
        return usuarioPermissaoRepository.findAll().stream().map(UsuarioPermissaoDTO::new).toList();
    }

    public UsuarioPermissaoDTO getUserPermissionById(Integer id) {
        return usuarioPermissaoRepository.findById(id).map(UsuarioPermissaoDTO::new).orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
    }

    public UsuarioPermissaoDTO saveUserPermission(UsuarioPermissaoDTO usuarioPermissaoDTO) {
        return new UsuarioPermissaoDTO(usuarioPermissaoRepository.save(usuarioPermissaoDTO.toEntity()));
    }

    public UsuarioPermissaoDTO updateUserPermission(Integer id, UsuarioPermissaoDTO usuarioPermissaoDTO) {
        UsuarioPermissao usuarioPermissao = usuarioPermissaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário", id));

        UsuarioPermissao newUsuarioPermissao = usuarioPermissaoDTO.toEntity();
        BeanUtils.copyProperties(newUsuarioPermissao, usuarioPermissao, "id");

        return new UsuarioPermissaoDTO(usuarioPermissaoRepository.save(usuarioPermissao));
    }

    public UsuarioPermissaoDTO deleteUserPermission(Integer id) {
        UsuarioPermissao usuarioPermissao = usuarioPermissaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
        usuarioPermissaoRepository.delete(usuarioPermissao);

        return new UsuarioPermissaoDTO(usuarioPermissao);
    }
}