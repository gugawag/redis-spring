package com.example.redis.controladores;

import com.example.redis.Usuario;
import com.example.redis.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Cacheable(value = "usuarios", key = "#usuarioId",
            unless="#result.seguidores < 20000")
    @RequestMapping(value="/{usuarioId}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long usuarioId) {
        System.out.println("Pegando usuário com ID " + usuarioId);
        return this.usuarioRepository.findById(usuarioId).orElse(null);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "usuarios", key="#id"),
                    @CacheEvict(value = "totalSeguidores", allEntries = true)
            }
    )
    @DeleteMapping("/{id}")
    public void apagarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

//    @RequestMapping(value="/q/{usuarioId}", method = RequestMethod.GET)
//    public Long getQuantidadeSeguidoresUsuario(@PathVariable Long usuarioId) {
//
//    }

    @Cacheable(value="totalSeguidores")
    @GetMapping("/totalSeguidores")
    public Long getTotalSeguidores() {
        return this.usuarioRepository.findAll().stream()
                .mapToLong(Usuario::getSeguidores).sum();
    }

}
