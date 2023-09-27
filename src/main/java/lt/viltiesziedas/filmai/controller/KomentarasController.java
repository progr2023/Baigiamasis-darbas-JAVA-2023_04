package lt.viltiesziedas.filmai.controller;

import lt.viltiesziedas.filmai.model.entity.Filmai;
import lt.viltiesziedas.filmai.model.entity.Komentarai;
import lt.viltiesziedas.filmai.model.entity.Vartotojai;
import lt.viltiesziedas.filmai.model.repository.KomentarasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class KomentarasController {
    @Autowired

    KomentarasRepository komentarasRepository;

    @PostMapping("/komentaras/prideti/{id}")
    public String pridetiKomentara(Model model, @PathVariable long id, @RequestParam String pridedamasKomentaras) {
        Komentarai komentaras = new Komentarai();
        komentaras.setTekstas(pridedamasKomentaras);
        komentaras.setPridejimoData(new Date());
        Filmai filmas = new Filmai();
        filmas.setId(id);
        komentaras.setFilmai(filmas);
        Vartotojai vartotojas = new Vartotojai();
        vartotojas.setId(1);
        komentaras.setVartotojai(vartotojas);

        komentarasRepository.save(komentaras);
        return "komentaras/pridetas.html";
    }


}
