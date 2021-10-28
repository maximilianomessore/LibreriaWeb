
package egg.web.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AutorController {
   @GetMapping("/home") 
   public String home(){
       return "holaMundo";
   }
}
