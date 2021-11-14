package egg.web.libreria.controladores;

import egg.web.libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
