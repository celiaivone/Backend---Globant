package com.ejemplospring.Ejemploguia.Controller;

import com.ejemplospring.Ejemploguia.Entity.Autor;
import com.ejemplospring.Ejemploguia.Services.AutorServicio;
import com.ejemplospring.Ejemploguia.exception.MiException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/autor") //localhost:8080/autor
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorservicio;
    
    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar(){
        return "autor_form.html";
    }  
       
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        
        try {
            autorservicio.crearAutor(nombre);
            modelo.put("exito", "El autor fue registrado correctamente!");
        } catch (MiException ex) {
            
//            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE,null,ex);
            modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }
      
    @GetMapping("/lista")
    public String listar (ModelMap model) {
        List<Autor> autores = autorservicio.listarAutores();

        // Imprime en la consola
        System.out.println("Autores: " + autores);

        model.addAttribute("autores", autores);
        return "autor_lista";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("autor", autorservicio.getOne(id));
                
        return "autor_modificar.html";  
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
        
        try {
            autorservicio.modificarAutor(id, nombre);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
        }
        
    }
}
