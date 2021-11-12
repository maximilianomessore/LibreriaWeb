
package egg.web.libreria.controladores;


import egg.web.libreria.entidades.Libro;
import egg.web.libreria.erroresServicios.ErroresSistema;
import egg.web.libreria.repositorios.LibroRepositorio;
import egg.web.libreria.servicios.AutorServicio;
import egg.web.libreria.servicios.EditorialServicio;
import egg.web.libreria.servicios.LibroServicio;
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
@RequestMapping("/Libro")
public class LibroControler {
    @Autowired
    private LibroRepositorio libro;
    @Autowired
    private LibroServicio service;
    @Autowired
    private AutorServicio autorServ;
    @Autowired
    private EditorialServicio editorialServ;
    
    @GetMapping("/listar")
    public ModelAndView listar(){
        List<Libro> libros = service.misLibros();
        ModelAndView mvc = new ModelAndView("/libroTabla");
        mvc.addObject("libros", libros);
        return mvc ;
    }
    
    @GetMapping("/crear")
    public ModelAndView crear(){
        ModelAndView mvc = new ModelAndView("/formulario");
        mvc.addObject("libro",new Libro());
        mvc.addObject("autores",autorServ.misAutores());
        mvc.addObject("editoriales",editorialServ.misEditoriales());
        mvc.addObject("title","Cargar Libro");
        mvc.addObject("action", "guardar");
        return mvc;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam long ISBN,@RequestParam String titulo,
            @RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,
            @RequestParam String autor, @RequestParam String editorial) throws ErroresSistema{
            try{
            service.crearLibro(ISBN,titulo, anio, ejemplares, ejemplaresPrestados, ejemplares, true, autor, editorial);
            return new RedirectView("/Libro/listar");
            }catch(ErroresSistema e){
                throw new ErroresSistema("Error al crear Usuario");
            }
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam long ISBN,@RequestParam String titulo,
            @RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,
            @RequestParam String autor, @RequestParam String editorial) throws ErroresSistema{
            try{
            service.modificarLibro(ISBN,titulo, anio, ejemplares, ejemplaresPrestados, ejemplares, true, autor, editorial);
            return new RedirectView("/Libro/listar");
            }catch(ErroresSistema e){
                throw new ErroresSistema("Error al crear Usuario");
            }
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id) throws ErroresSistema{
        ModelAndView mvc = new ModelAndView("formulario");
        mvc.addObject("libro", service.buscarLibroporId(id));
        mvc.addObject("id", id);
        mvc.addObject("autores",autorServ.misAutores());
        mvc.addObject("editoriales",editorialServ.misEditoriales());
        mvc.addObject("title","Editar Libro");
        mvc.addObject("action","modificar");
        return mvc;
    }
    
    @GetMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) throws ErroresSistema{
          
            service.baja(id);
            return new RedirectView("/Libro/listar");
            
    }
    
  
    
}
