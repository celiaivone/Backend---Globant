package com.ejemplospring.Ejemploguia.Repository;

import com.ejemplospring.Ejemploguia.Entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
    
}
