package lt.viltiesziedas.filmai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String grazintiPagrindiniPuslapi() {
        return "index.html";
    }
}