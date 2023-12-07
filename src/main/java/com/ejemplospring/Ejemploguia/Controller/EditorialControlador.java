package com.ejemplospring.Ejemploguia.Controller;

import com.ejemplospring.Ejemploguia.Entity.Editorial;
import com.ejemplospring.Ejemploguia.Services.EditorialServicio;
import com.ejemplospring.Ejemploguia.exception.MiException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/editorial", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}) //localhost:8080/editorial

public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialservicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "editorial_form.html";
        
    }
      
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        
        try {
            editorialservicio.crearEditorial(nombre);
            modelo.put("exito", "La editorial fue registrado correctamente!");
        } catch (MiException ex) {
            
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE,null,ex);
//            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar (ModelMap model){
        
        List<Editorial> editoriales = editorialservicio.listarEditoriales();
        System.out.println("Editoriales: " + editoriales);
        model.addAttribute("editoriales", editoriales);
        return "editorial_lista.html";
    }
}
