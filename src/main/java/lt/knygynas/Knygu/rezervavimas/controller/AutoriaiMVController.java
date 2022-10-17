package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Autorius;
import lt.knygynas.Knygu.rezervavimas.model.entity.Kategorijos;
import lt.knygynas.Knygu.rezervavimas.model.repository.AutoriausRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AutoriaiMVController {
    @Autowired
    AutoriausRepository autoriausRepository;

    @GetMapping("/aut/naujas_autorius")
    String naujasAutorius() {
        return "ideti_autorius.html";
    }

    @PostMapping("/aut/idedamas_autorius")
    String idetiAutoriu(String vardas, String pavarde, String aprasymas) {
        Autorius autorius = new Autorius();
        autorius.setVardas(vardas);
        autorius.setPavarde(pavarde);
        autorius.setAprasymas(aprasymas);
        autoriausRepository.save(autorius);
        return "ideta_autorius.html";
    }

    @GetMapping("/aut/knygos_pagal_autoriu")
    String knygosPagalAutoriu(Model model) {
        List<Autorius> autoriusList = autoriausRepository.findAll();
        model.addAttribute("visiAutoriai", autoriusList);
        return "knygos_pagal_autoriu.html";
    }

    @GetMapping("/aut/visi_autoriai")
    String rodytiVisusAutorius(Model model) {
        List<Autorius> autoriai = autoriausRepository.findAll();
        model.addAttribute("VisiAutoriai", autoriai);
        return "visi_autoriai.html";
    }

    @GetMapping("/aut/rodyti_autoriu")
    String rodytiAutoriausKnygas(Model model, @RequestParam int id) {
        Autorius autorius = autoriausRepository.findById(id);
        model.addAttribute("id", autorius.getId());
        model.addAttribute("vardas", autorius.getVardas());
        model.addAttribute("pavarde", autorius.getPavarde());
        model.addAttribute("aprasymas", autorius.getAprasymas());
        return "parodyti_autoriu.html";
    }

    @GetMapping("/aut/rasti_pagal_autoriu")
    String ieskomaPagalAutorius(Model model, @RequestParam String vardas) {
        Autorius autorius = autoriausRepository.findByVardas(vardas);
        List<Autorius> autoriusList = autoriausRepository.findAll();
        model.addAttribute("autoriausKnygos", autorius.getAutoriausKnygos());
        model.addAttribute("autoriausVardas", autorius.getVardas());
        model.addAttribute("visiAutoriai", autoriusList);
        return "autoriaus_knygos.html";
    }

    @GetMapping("/aut/rasti_autoriu")
    String ieskomasAutorius(Model model, @RequestParam String vardas) {
        Autorius autorius = autoriausRepository.findByVardas(vardas);
        model.addAttribute("id", autorius.getId());
        model.addAttribute("vardas", autorius.getVardas());
        model.addAttribute("pavarde", autorius.getPavarde());
        model.addAttribute("aprasymas", autorius.getAprasymas());
        return "parodyti_autoriu.html";
    }
}
