package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.erroresServicios.ErroresSistema;
import egg.web.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio autorrepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws ErroresSistema{
        validar(nombre);
        Autor autor = new Autor();
        autor.setAlta(true);
        autor.setNombre(nombre);
        
        autorrepositorio.save(autor);
    }
    @Transactional
    public void modificarAutor(String id, String nombre) throws ErroresSistema{
        validar(nombre);
        validar(id);
        Optional <Autor> a = autorrepositorio.findById(id);
        if(a.isPresent()){
            Autor autor = a.get();
            autor.setNombre(nombre);
            autorrepositorio.save(autor);
        }else{
            throw new ErroresSistema("Autor no encontrado");
        }
    }
    @Transactional
    public void BajaAutor(String id) throws ErroresSistema{
        validar(id);
        Optional <Autor> a = autorrepositorio.findById(id);
        if(a.isPresent()){
            autorrepositorio.darBajaAutor(id);
        }else{
            throw new ErroresSistema("Autor no encontrado");
        } 
    }
    public void validar(String nombre) throws ErroresSistema{
        if(nombre.isEmpty() || nombre == null){
            throw new ErroresSistema("El campo no puede ser nullo");
        }
    }
    
    public List<Autor> misAutores(){
        List<Autor> autores = autorrepositorio.buscarAutor();
        return autores;
    }
    
    public Autor buscarAutorPorId( String id){
        return autorrepositorio.buscarAutorPorId(id);
    }
    
}
