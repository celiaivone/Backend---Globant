package com.ejemplospring.Ejemploguia.Controller;

import com.ejemplospring.Ejemploguia.Entity.Autor;
import com.ejemplospring.Ejemploguia.Entity.Editorial;
import com.ejemplospring.Ejemploguia.Entity.Libro;
import com.ejemplospring.Ejemploguia.Services.AutorServicio;
import com.ejemplospring.Ejemploguia.Services.EditorialServicio;
import com.ejemplospring.Ejemploguia.Services.LibroServicio;
import com.ejemplospring.Ejemploguia.exception.MiException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/libro", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}) //localhost:8080/libro
public class LibroControlador {

    @Autowired
    private LibroServicio libroservicio;

    @Autowired
    private AutorServicio autorservicio;

    @Autowired
    private EditorialServicio editorialservicio;

    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {

        List<Autor> autores = autorservicio.listarAutores();
        List<Editorial> editoriales = editorialservicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";

    }
    

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial, ModelMap modelo) {

        try {
            libroservicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "La editorial fue registrado correctamente!");

        } catch (MiException ex) {

            List<Autor> autores = autorservicio.listarAutores();
            List<Editorial> editoriales = editorialservicio.listarEditoriales();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
//            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE,null,ex);
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar (ModelMap model){
        
        List<Libro> libros = libroservicio.listarLibros();
        System.out.println("Libros: " + libros);
        model.addAttribute("libros", libros);
        return "libro_lista.html";
    }
}
