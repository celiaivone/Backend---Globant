
package com.ejemplospring.Ejemploguia.Repository;

import com.ejemplospring.Ejemploguia.Entity.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long>{
    
    @Query("SELECT L FROM Libro L WHERE L.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String titulo);
    
    @Query ("SELECT L FROM Libro L WHERE L.autor.nombre = :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);
    
}
