package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.erroresServicios.ErroresSistema;
import egg.web.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialrepositorio;
    @Transactional
    public void crearEditorial(String nombre) throws ErroresSistema{
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setAlta(true);
        editorial.setNombre(nombre);
        
        editorialrepositorio.save(editorial);
    }
    @Transactional
    public void modificarEditorial(String id,String nombre) throws ErroresSistema{
        validar(id);
        validar(nombre);
        Optional <Editorial> e = editorialrepositorio.findById(id);
        if(e.isPresent()){
            Editorial editorial = e.get();
            editorial.setNombre(nombre);
            editorialrepositorio.save(editorial);
        }else{
            throw new ErroresSistema("Editorial no encontrada");
        }
    }
    @Transactional
    public void BajaEditorial(String id) throws ErroresSistema{
        validar(id);
        Optional <Editorial> e = editorialrepositorio.findById(id);
        if(e.isPresent()){
            editorialrepositorio.darBajaEditorial(id);
        }else{
            throw new ErroresSistema("Editorial no encontrada");
        }
    }
    public void AltaEditorial(String id) throws ErroresSistema{
        validar(id);
        Optional <Editorial> e = editorialrepositorio.findById(id);
        if(e.isPresent()){
            editorialrepositorio.darAltaEditorial(id);
        }else{
            throw new ErroresSistema("Editorial no encontrada");
        }
    }
    public List<Editorial> misEditoriales(){
        List<Editorial> editoriales = editorialrepositorio.buscarEditorial();
        return editoriales;
    }
    public void validar(String nombre) throws ErroresSistema{
        if(nombre.isEmpty() || nombre == null){
            throw new ErroresSistema("El campo no puede ser nullo");
        }
    }
}
