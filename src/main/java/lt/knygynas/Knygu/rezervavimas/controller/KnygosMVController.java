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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/knyg/paieska")
    String ieskomaKnyga(Model model){
        return "rasti_knyga.html";
    }

    @GetMapping("/knyg/visos_knygos")
    String rodytiVisasKnygas(Model model) {
        List<Knygos> visosKnygos = knygosRepository.findAll();
        model.addAttribute("visosKnygos", visosKnygos);
        return "rodyti_knygas.html";
    }
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
        knyga.setKnygosAutoriai(autoriusVardas);
        knygosRepository.save(knyga);
        return "ideta_knyga.html";
    }

    @GetMapping("/knyg/rasti_knyga_id")
    String ieskomaKnygaPagalId(Model model,@RequestParam int id){
        Knygos knyga = knygosRepository.findById(id);
        model.addAttribute("id" , knyga.getId());
        model.addAttribute("pavadinimas", knyga.getPavadinimas());
        model.addAttribute("puslapiuSkait", knyga.getPuslapiuSkait());
        model.addAttribute("aprasymas", knyga.getAprasymas());
        model.addAttribute("turinys", knyga.getTurinys());
        model.addAttribute("kiekis", knyga.getKiekis());
        model.addAttribute("knygosVartotojei", knyga.getKnygosVartotojei());
        model.addAttribute("KnygosAutoriai", knyga.getKnygosAutoriai());
        model.addAttribute("knygosKategorijos", knyga.getKnygosKategorijos());
        return "parodyti_knyga.html";
    }

    @GetMapping("/knyg/rasti_knyga")
    String ieskomaKnyga(Model model , @RequestParam String pavadinimas){
        Knygos knyga = knygosRepository.findByPavadinimas(pavadinimas);
        model.addAttribute("id", knyga.getId());
        model.addAttribute("pavadinimas", knyga.getPavadinimas());
        model.addAttribute("puslapiuSkait", knyga.getPuslapiuSkait());
        model.addAttribute("aprasymas", knyga.getAprasymas());
        model.addAttribute("turinys" , knyga.getTurinys());
        model.addAttribute("kiekis", knyga.getKiekis());
        model.addAttribute("knygosVartotojei", knyga.getKnygosVartotojei());
        model.addAttribute("KnygosAutoriai", knyga.getKnygosAutoriai());
        model.addAttribute("knygosKategorijos", knyga.getKnygosKategorijos());
        return "parodyti_knyga.html";
    }

    @PostMapping("/knyg/istrinti_knyga/{id}")
    String istrintiKyga(Model model, @PathVariable int id) {
        knygosRepository.delete(knygosRepository.findById(id));
        return "istrinta_knyga.html";
    }

    @GetMapping("/knyg/redaguoti_knyga/{id}")
    String redaguojamaKnyga(Model model, @RequestParam int id){
        Knygos knyga = knygosRepository.findById(id);
        model.addAttribute("knyga", knyga);
        model.addAttribute("kategorijos", kategorijosRepository.findAll());
        model.addAttribute("autoriai", autoriausRepository.findAll());
        return "redaguoti_knyga.html";
    }
}
