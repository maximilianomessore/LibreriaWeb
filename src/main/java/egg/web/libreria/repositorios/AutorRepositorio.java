package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Autor;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor,String>{
    @Modifying
    @Query("SELECT a FROM Autor a WHERE a.id = :id")
    public List<Autor> buscarAutorPorId(@Param("id") String id);
    @Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre WHERE a.id = :id")
    void modificarAutor(@Param("nombre") String nombre, @Param("id") String id);
    
    @Modifying
    @Query("UPDATE Autor a SET a.alta = true WHERE a.nombre = :nombre")
    void darAltaAutor(@Param("nombre") String nombre);
    
    @Modifying
    @Query("UPDATE Autor a SET a.alta = false WHERE a.id = :id")
    void darBajaAutor( @Param("id") String id);
    
    @Query("SELECT a FROM Autor a WHERE a.alta = true")
    public List<Autor> buscarAutor();
}
