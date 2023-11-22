package com.ejemplospring.Ejemploguia.Controller;

import com.ejemplospring.Ejemploguia.Services.AutorServicio;
import com.ejemplospring.Ejemploguia.exception.MiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/autor", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}) //localhost:8080/autor
public class AutorControlador {
    
    @Autowired
    
    private AutorServicio autorservicio;
    
    @GetMapping("/registrar") //localhost:8080/autor/registrar
    
    public String registrar(){
        return "autor_form.html";
    }  
    
    @PostMapping("/registro")
    
    public String registro(@RequestParam String nombre, ModelMap modelo){
        
        System.out.println("Nombre: "+ nombre);
        try {
            autorservicio.crearAutor(nombre);
            modelo.put("exito", "El autor fue registrado correctamente!");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }
    
}
