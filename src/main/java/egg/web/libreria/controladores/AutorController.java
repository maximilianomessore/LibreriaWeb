package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.erroresServicios.ErroresSistema;
import egg.web.libreria.servicios.AutorServicio;
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
@RequestMapping("/Autor")
public class AutorController {
    @Autowired
    private AutorServicio service;
    @GetMapping("/listar")
    public ModelAndView listar(){
        ModelAndView model = new ModelAndView("tabla-autor");
        model.addObject("autores", service.misAutores());
        return model;
    }
    
    @GetMapping("/crear")
    public ModelAndView crear(){
        ModelAndView model = new ModelAndView("formulario-autor");
        model.addObject("autor",new Autor());
        model.addObject("title","Crear Autor");
        model.addObject("action","guardar");
        return model;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) throws ErroresSistema{
        service.crearAutor(nombre);
        return new RedirectView("/Autor/listar");
    }
    
    @GetMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) throws ErroresSistema{
        service.BajaAutor(id);
        return new RedirectView("/Autor/listar");
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id){
        ModelAndView model = new ModelAndView("/formulario-autor");
        model.addObject("autor", service.buscarAutorPorId(id));
        model.addObject("id", id);
        model.addObject("title", "Editar Autor");
        model.addObject("action", "modificar");
        return model;
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String nombre, @RequestParam String id) throws ErroresSistema{
        try{
            service.modificarAutor(id, nombre);
            return new RedirectView("/Autor/listar");
        }catch(ErroresSistema e){
            throw new ErroresSistema("Error al modificar Autor");
        }
    }
}
