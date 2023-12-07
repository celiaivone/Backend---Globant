package com.ejemplospring.Ejemploguia.Services;

import com.ejemplospring.Ejemploguia.Entity.Editorial;
import com.ejemplospring.Ejemploguia.Repository.EditorialRepositorio;
import com.ejemplospring.Ejemploguia.exception.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional  
    public void crearEditorial(String nombre) throws MiException{
        
        if (nombre==null || nombre.isEmpty()) {
            
            throw new MiException("El nombre de la editorial no puede ser nulo o estar vacío");
            
        }
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
        
    }
    
    public List<Editorial> listarEditoriales(){
        
        List<Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
        
    }
    
    public Editorial getOne(String id){
        
        return editorialRepositorio.getOne(id);
        
    }
    
    public void modificarEditorial(String id, String nombre) throws MiException{
        
        if (nombre==null || nombre.isEmpty()) {
            
            throw new MiException("El nombre de la editorial no puede ser nulo o estar vacío");
            
        }
        
        Optional <Editorial> respuestaEditorial = editorialRepositorio.findById(id);
        
        if (respuestaEditorial.isPresent()) {
            
            Editorial editorial = respuestaEditorial.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
            
        }
        
    }
    
}
