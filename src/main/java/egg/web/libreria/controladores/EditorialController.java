package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.erroresServicios.ErroresSistema;
import egg.web.libreria.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/Editorial")
public class EditorialController {
    @Autowired
    private EditorialServicio editorialService;
    @GetMapping("/listar")
    public ModelAndView listar(){
        List <Editorial> misEditoriales = editorialService.misEditoriales();
        ModelAndView model = new ModelAndView("tabla");
        model.addObject("editoriales", misEditoriales);
        return model;
    }
    
    @GetMapping("/crear")
    public ModelAndView crear(){
        ModelAndView model = new ModelAndView("formulario-editorial");
        model.addObject("editorial",new Editorial());
        model.addObject("title", "Crear Editorial");
        model.addObject("action","guardar");
        return model;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) throws ErroresSistema{
        try{
            editorialService.crearEditorial(nombre);
            return new RedirectView("/Editorial/listar");
        }catch(ErroresSistema e){
            throw new ErroresSistema("Error al crear Editorial");
        }
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id){
        ModelAndView model = new ModelAndView("/formulario-editorial");
        model.addObject("editorial", editorialService.buscarEditorialPorId(id));
        model.addObject("id", id);
        model.addObject("title", "Editar Editorial");
        model.addObject("action", "modificar");
        return model;
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String nombre, @RequestParam String id) throws ErroresSistema{
        try{
            editorialService.modificarEditorial(id,nombre);
            return new RedirectView("/Editorial/listar");
        }catch(ErroresSistema e){
            throw new ErroresSistema("Error al modificar Editorial");
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) throws ErroresSistema{
        editorialService.BajaEditorial(id);
        return new RedirectView("/Editorial/listar");

    }
}
