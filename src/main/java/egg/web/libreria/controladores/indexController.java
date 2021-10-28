package egg.web.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class indexController {
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    @GetMapping("/libros")
    public String libros(){
        return "tabla.html";
    }
    @GetMapping("/editoriales")
    public String editoriales(){
        return "tabla.html";
    }
    @GetMapping("/autores")
    public String autores(){
        return "tabla.html";
    }
}
