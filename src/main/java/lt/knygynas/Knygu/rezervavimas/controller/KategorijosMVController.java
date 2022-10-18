package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Kategorijos;
import lt.knygynas.Knygu.rezervavimas.model.repository.KategorijosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class KategorijosMVController {
    @Autowired
    KategorijosRepository kategorijosRepository;

    @PostMapping("/kategorija/istrinti_kategorija/{id}")
    String istrintiKategorija(Model model,@PathVariable int id){
        kategorijosRepository.delete(kategorijosRepository.findById(id));
        return "kategorija_istrinta.html";
    }

    @GetMapping("/kategorija/nauja_kategorija")
    String naujaKategorija(){
        return "ideti_kategorija.html";
    }

    @PostMapping("/kategorija/idedama_kategorija")
    String idetiKategorija(String pavadinimas){
        Kategorijos naujaKategorija = new Kategorijos();
        naujaKategorija.setPavadinimas(pavadinimas);
        kategorijosRepository.save(naujaKategorija);
        return "ideta_kategorija.html";
    }

    @GetMapping("/kategorijos/kat_skiltis")
    String kategorijosSkiltis(Model model){
        return "visos_kategorijos.html";
    }

    @GetMapping("/kategorijos/visos_kategorijos")
    String rodytiVisasKategorijas(Model model){
        List<Kategorijos> visosKategorijos = kategorijosRepository.findAll();
        model.addAttribute("visosKategorijos", visosKategorijos);
        return "visos_kategorijos.html";
    }

    @GetMapping("/kategorijos/rodyti_knygas")
    String rodytiKategorijjosknygos(Model model,@RequestParam int id){
        Kategorijos kategorija = kategorijosRepository.findById(id);
        model.addAttribute("id", kategorija.getId());
        model.addAttribute("kategorijosKnygos" , kategorija.getKnygaSuKategorija());
        model.addAttribute("kategorijosPavadinimas" , kategorija.getPavadinimas());
        return "kategorijos_knygos.html";
    }

    @GetMapping("/kategorija/rasti_pagal_kategorija")
    String ieskomaKategorija(Model model, @RequestParam String pavadinimas){
        Kategorijos kategorija = kategorijosRepository.findByPavadinimas(pavadinimas);
        model.addAttribute("kategorijosKnygos" ,kategorija.getKnygaSuKategorija() );
        model.addAttribute("kategorijosPavadinimas", kategorija.getPavadinimas());
        return "kategorijos_knygos.html";
    }

}
