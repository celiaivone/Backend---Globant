package com.ejemplospring.Ejemploguia.Services;

import com.ejemplospring.Ejemploguia.Entity.Autor;
import com.ejemplospring.Ejemploguia.Entity.Editorial;
import com.ejemplospring.Ejemploguia.Entity.Libro;
import com.ejemplospring.Ejemploguia.Repository.AutorRepositorio;
import com.ejemplospring.Ejemploguia.Repository.EditorialRepositorio;
import com.ejemplospring.Ejemploguia.Repository.LibroRepositorio;
import com.ejemplospring.Ejemploguia.exception.MiException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    
    //Inyección de dependencias, es decir no necesita ser inicializada
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional 
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
                
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        
        
        
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
    }
    
    public List<Libro> listarLibros(){
        
        List<Libro> libros = new ArrayList();
        
        libros = libroRepositorio.findAll();
        
        return libros;
        
    }
    
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idAutor);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if (respuestaAutor.isPresent()) {
            
            autor = respuestaAutor.get();
            
        }
        
        if (respuestaEditorial.isPresent()) {
            
            editorial = respuestaEditorial.get();
            
        }
        
        if (respuestaLibro.isPresent()) {
            
            Libro libro = respuestaLibro.get();
            
            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            
            libroRepositorio.save(libro);
            
        }
        
    }
    
    public Libro getOne(Long isbn){
        
        return libroRepositorio.getOne(isbn);
        
    }
    
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
                if (isbn == null) {
            
            throw new MiException("El isbn no puede ser nulo");
            
        }
        if (titulo.isEmpty() || titulo == null) {
            
            throw new MiException("El título no puede estar vacío, ni ser nulo");
            
        }
        if (ejemplares == null) {
            
            throw new MiException("El número de ejemplares no puede ser nulo");
            
        }
        if (idAutor.isEmpty() || idAutor == null) {
            
            throw new MiException("El id del autor no puede estar vacío, ni ser nulo");
            
        }
        if (idEditorial.isEmpty() || idEditorial == null) {
            
            throw new MiException("El id del editorial no puede estar vacío, ni ser nulo");
            
        }        
    }
        
}
