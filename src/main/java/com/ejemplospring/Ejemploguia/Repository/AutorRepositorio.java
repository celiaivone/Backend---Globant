
package com.ejemplospring.Ejemploguia.Repository;

import com.ejemplospring.Ejemploguia.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
    
    
}
