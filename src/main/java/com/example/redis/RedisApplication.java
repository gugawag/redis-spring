package com.example.redis;

import com.example.redis.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Override
    public void run(String... strings) {
        //Populating embedded database here
        System.out.println("Inserindo usuários. Quantidade atual {}." +  usuarioRepositorio.count());
        Usuario jose = new Usuario("José", 2001L);
        Usuario whinderson = new Usuario("Whinderson", 29003L);
        Usuario gustavo = new Usuario("Gustavo", 555L);

        usuarioRepositorio.save(jose);
        usuarioRepositorio.save(whinderson);
        usuarioRepositorio.save(gustavo);
        System.out.println("Salvando usuário. Dados: {}." + usuarioRepositorio.findAll());
    }

}
