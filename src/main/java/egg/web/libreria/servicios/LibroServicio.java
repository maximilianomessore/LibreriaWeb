package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.erroresServicios.ErroresSistema;
import egg.web.libreria.repositorios.AutorRepositorio;
import egg.web.libreria.repositorios.EditorialRepositorio;
import egg.web.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio librorepositorio;
    @Autowired
    private AutorRepositorio autorrepositorio;
    @Autowired
    private EditorialRepositorio editorialrepositorio;
    @Transactional
    public void crearLibro(Long ISBN,String titulo, Integer anio, Integer cant_ejemplares, Integer cant_prestados, Integer cant_restantes, Boolean alta, String idAutor, String idEditorial) throws ErroresSistema{
        validar(titulo,anio,cant_ejemplares,cant_prestados,cant_restantes,alta);
        Libro libro = new Libro();
        libro.setAlta(alta);
        libro.setAnio(anio);
        libro.setEjemplares(cant_ejemplares);
        libro.setEjemplaresPrestados(cant_prestados);
        libro.setEjemplaresRestantes(cant_restantes);
        libro.setTitulo(titulo);
        libro.setISBN(ISBN);
        Optional <Editorial> editorial = editorialrepositorio.findById(idEditorial);
            if(editorial.isPresent()){
                Editorial editoria = editorial.get();
                libro.setEditorial(editoria); 
            }else{
                throw new ErroresSistema("Editorial no encontrada");
            }
            Optional <Autor> a = autorrepositorio.findById(idAutor);
            if(a.isPresent()){
                Autor autor = a.get();
                libro.setAutor(autor);
            }else{
                throw new ErroresSistema("Autor no encontrado");
            }
        
        librorepositorio.save(libro);
    }
    @Transactional
    public void modificarLibro(Long isbn,String titulo, Integer anio, Integer cant_ejemplares, Integer cant_prestados, Integer cant_restantes, Boolean alta, String idAutor, String idEditorial) throws ErroresSistema{
        validar(titulo,anio,cant_ejemplares,cant_prestados,cant_restantes,alta);
            Libro libro = librorepositorio.buscarLibroPorISBN(isbn);
           
            libro.setAlta(true);
            libro.setAnio(anio);
            libro.setEjemplares(cant_ejemplares);
            libro.setEjemplaresPrestados(cant_prestados);
            libro.setEjemplaresRestantes(cant_restantes);
            libro.setTitulo(titulo);
            libro.setISBN(isbn);
            Optional <Editorial> editorial = editorialrepositorio.findById(idEditorial);
            if(editorial.isPresent()){
                Editorial editoria = editorial.get();
                libro.setEditorial(editoria); 
            }else{
                throw new ErroresSistema("Editorial no encontrada");
            }
            Optional <Autor> a = autorrepositorio.findById(idAutor);
            if(a.isPresent()){
                Autor autor = a.get();
                libro.setAutor(autor);
            }else{
                throw new ErroresSistema("Autor no encontrado");
            }
            librorepositorio.save(libro);
        
    }   
    @Transactional
    public Libro buscarLibroporId(String id) throws ErroresSistema{
        Optional<Libro> respuesta = librorepositorio.findById(id);
        if(respuesta.isPresent()){
           Libro libro = respuesta.get();
           return libro;
        }else{
            throw new ErroresSistema("No se encontró el Libro");
        }
    }
    @Transactional
    public void baja(String id) throws ErroresSistema{    
        librorepositorio.darBajaLibro(id);
              
    }
        @Transactional
    public void alta(String id) throws ErroresSistema{
        Optional<Libro> respuesta = librorepositorio.findById(id);
        if(respuesta.isPresent()){
           Libro libro = respuesta.get();
           librorepositorio.darAltaLibro(libro.getTitulo());
        }else{
            throw new ErroresSistema("No se encontró el Libro");
        }
    }
    
    public List<Libro> misLibros(){
        List<Libro> libros = librorepositorio.buscarLibros();
        return libros;
    }
    
    public void validar(String titulo, Integer anio, Integer cant_ejemplares, Integer cant_prestados, Integer cant_restantes, Boolean alta) throws ErroresSistema{
        if(titulo.isEmpty() || titulo == null){
            throw new ErroresSistema("El titulo no puede ser nulo");
        }
        if(anio == null){
            throw new ErroresSistema("Año ingresado nulo o incorrecto");
        }
        if(cant_ejemplares == null){
            throw new ErroresSistema("Cantidad de ejemplares no puede ser nula");
        }
        if(alta == null){
            throw new ErroresSistema("Alta invalida");
        }
    }
} 

