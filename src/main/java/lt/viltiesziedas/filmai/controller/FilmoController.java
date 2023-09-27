package lt.viltiesziedas.filmai.controller;

import lt.viltiesziedas.filmai.model.entity.Filmai;
import lt.viltiesziedas.filmai.model.entity.FilmoKategorija;
import lt.viltiesziedas.filmai.model.repository.FilmoKategorijaRepository;
import lt.viltiesziedas.filmai.model.repository.FilmoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class FilmoController {

    @Autowired
    FilmoRepository filmoRepository;

    @Autowired
    FilmoKategorijaRepository kategorijaRepository;
    @GetMapping ("/filmai/paieska")
    public String filmuPaieska (Model model, @RequestParam (required = false) String pavadinimas) {
        List<Filmai> visiFilmai;
        if (StringUtils.isEmpty(pavadinimas)) {
            visiFilmai = filmoRepository.findAll();
        } else {
            visiFilmai = filmoRepository.findByPavadinimasLike("%" + pavadinimas + "%");
        }

        model.addAttribute ("visiFilmai", visiFilmai);
        return "filmas/paieska.html";

    }
    @GetMapping ("/filmai/informacija/{id}")
    public String filmoInformacija(Model model, @PathVariable long id) {
        Filmai filmai = filmoRepository.findById(id);
        model.addAttribute("filmai", filmai);
        return "filmas/informacija.html";
    }
    @PostMapping("/filmai/istrinti/{id}")
    public String istrintiRecepta(Model model, @PathVariable long id) {
        filmoRepository.deleteById(id);
        return "filmas/istrintas.html";
    }
    @GetMapping("/filmas/pridejimas")
    public String filmoIdejimas(Model model) {
        model.addAttribute("filmas", new Filmai());
        List<FilmoKategorija> kategorijos = kategorijaRepository.findAll();
        model.addAttribute("kategorijos", kategorijos);
        return "filmas/pridejimas.html";
    }

    @PostMapping("/filmai/pridetas")
    public String filmasIdetas(Model model, @ModelAttribute Filmai filmai ) {
        filmoRepository.save(filmai);
        return "filmas/pridetas.html";
    }

    @GetMapping("filmai/redagavimas/{id}")
    public String filmoRedagavimas(Model model, @PathVariable long id) {
        Filmai filmai = filmoRepository.findById(id);
        model.addAttribute("filmai", filmai);
        List<FilmoKategorija> kategorijos = kategorijaRepository.findAll();
        model.addAttribute("filmai", filmai);
        return "filmas/redagavimas.html";
    }


}
