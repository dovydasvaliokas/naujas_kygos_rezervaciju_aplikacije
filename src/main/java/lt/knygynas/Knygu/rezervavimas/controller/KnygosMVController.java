package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.entity.Knygos;
import lt.knygynas.Knygu.rezervavimas.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.repository.AutoriausRepository;
import lt.knygynas.Knygu.rezervavimas.repository.KategorijosRepository;
import lt.knygynas.Knygu.rezervavimas.repository.KnygosRepository;
import lt.knygynas.Knygu.rezervavimas.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/knyg/knygos_idejimas")
    String knygosIdejimas(Model model){
        Vartotojas vartotojas;
        Knygos naujaKnyga = new Knygos();
        model.addAttribute("knyga", naujaKnyga);
        model.addAttribute("vartotojas", vartotojoRepository.findById(1));
        model.addAttribute("autorius", autoriausRepository.findById(1));
        model.addAttribute("kategorijos", kategorijosRepository.findById(1));
        return "ideti_knyga.html";
    }
    @PostMapping("/knyg/ideta_knyga")
    String idedamaKnyga(Model model, @ModelAttribute Knygos knyga){
        System.out.println("cia vedas");
        knygosRepository.save(knyga);
        return "ideta_knyga.html";
    }
}
