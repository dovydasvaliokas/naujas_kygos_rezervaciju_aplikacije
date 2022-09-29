package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Autorius;
import lt.knygynas.Knygu.rezervavimas.model.entity.Knygos;
import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.AutoriausRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.KategorijosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.KnygosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import lt.knygynas.Knygu.rezervavimas.service.KnygosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class KnygosMVController {
    @Autowired
    KnygosRepository knygosRepository;
    @Autowired
    VartotojoRepository vartotojoRepository;
    @Autowired
    AutoriausRepository autoriausRepository;
    @Autowired
    KategorijosRepository kategorijosRepository;
    @Autowired
    KnygosService knygosService;

    @GetMapping("/knyg/knygos_idejimas")
    String knygosIdejimas(Model model){
        Vartotojas vartotojas;
        Knygos naujaKnyga = new Knygos();
        model.addAttribute("knyga", naujaKnyga);
        model.addAttribute("kategorijos", kategorijosRepository.findAll());
        return "ideti_knyga.html";
    }
    @PostMapping("/knyg/ideta_knyga")
    String idedamaKnyga(Model model, @ModelAttribute Knygos knyga, @RequestParam String autorius){
        System.out.println("SAVE komanda pasileido");
        Set<Autorius> autoriusVardas = knygosService.konvertavimasIsStringISet(autorius);
        knyga.setKnygosAutorius(autoriusVardas);
        knygosRepository.save(knyga);
        return "ideta_knyga.html";
    }
}
