
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,String>{
    @Query("SELECT e FROM Editorial e WHERE e.id = :id")
    public Editorial buscarEditorialporId(@Param("id") String Id);
    
    @Modifying
    @Query("UPDATE Editorial e SET e.nombre = :nombre WHERE e.Id = :id")
    void modificarEditorial(@Param("nombre") String nombre, @Param("id") String id);
    
    @Modifying
    @Query("UPDATE Editorial e SET e.alta = 1 WHERE e.id =:id")
    void darAltaEditorial(@Param("id") String id);
    
    @Modifying
    @Query("UPDATE Editorial e SET e.alta = 0 WHERE e.id =:id")
    void darBajaEditorial(@Param("id") String id);
    
    @Query("SELECT e FROM Editorial e WHERE e.alta = true")
     List<Editorial> buscarEditorial();
}
