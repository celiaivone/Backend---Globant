package com.ejemplospring.Ejemploguia.Services;

import com.ejemplospring.Ejemploguia.Entity.Autor;
import com.ejemplospring.Ejemploguia.Repository.AutorRepositorio;
import com.ejemplospring.Ejemploguia.exception.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    //Inyección de dependencias, es decir no necesita ser inicializada
   
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional 
    public void crearAutor(String nombre) throws MiException{
           
        if (nombre==null || nombre.isEmpty()) {
            
            throw new MiException("El nombre del autor no puede ser nulo o estar vacío");
            
        }
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
        
    }
    
    public List<Autor> listarAutores(){
        
        List<Autor> autores = new ArrayList();
        
        autores = autorRepositorio.findAll();
        
        return autores;
        
    }
    
    public void modificarAutor(String id, String nombre) throws MiException{
        
        if (nombre==null || nombre.isEmpty()) {
            
            throw new MiException("El nombre del autor no puede ser nulo o estar vacío");
            
        }
        
        Optional<Autor> respuestaAutor = autorRepositorio.findById(id);
        
        if (respuestaAutor.isPresent()) {
            
            Autor autor = respuestaAutor.get();
            
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        }
        
    }
}
