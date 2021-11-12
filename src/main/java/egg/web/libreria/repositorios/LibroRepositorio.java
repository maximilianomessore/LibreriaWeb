package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Libro;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String> {
   @Query("SELECT l FROM Libro l WHERE l.id = :id")
   public List<Libro> buscarLibroPorId(@Param("id") String Id);
   @Query("SELECT l FROM Libro l WHERE l.ISBN = :isbn")
   public Libro buscarLibroPorISBN(@Param("isbn") Long isbn);
   
   
   
   
//   @Modifying
//   @Query("UPDATE Libro l SET (l.titulo =: titulo, l.anio =: anio, l.ISBN = : ISBN,"
//           + " l.ejemplares = : ejemplares l.ejemplaresPrestados = : ejemplaresPrestados)")
//   void modificarLibro(@Param("titulo") String titulo, @Param("anio") Integer anio,
//           @Param("ISBN") Long ISBN, @Param("ejemplares") Integer ejemplares,
//           @Param("ejemplaresPrestados") Integer ejemplaresPrestados);

   @Modifying
   @Query("UPDATE Libro l SET l.alta = true WHERE l.titulo = : titulo")
   void darAltaLibro(@Param("titulo") String titulo);
   
   @Modifying
   @Query("UPDATE Libro l SET l.alta = 0 WHERE l.id=:id")
   void darBajaLibro(@Param("id") String id);
   
   @Query("SELECT l FROM Libro l WHERE l.alta = true")
   public List<Libro> buscarLibros();
    
}
