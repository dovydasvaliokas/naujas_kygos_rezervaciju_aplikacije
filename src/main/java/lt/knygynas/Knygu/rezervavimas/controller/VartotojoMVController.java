package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.KnygosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VartotojoMVController {
    @Autowired
    VartotojoRepository vartotojoRepository;
    @Autowired
    KnygosRepository knygosRepository;

    @PostMapping("/vartotojas/pamegti_knyga/{id}")
    String pamegtiknyga(@PathVariable int id){
        Vartotojas vartotojas = vartotojoRepository.findById(1);
        vartotojas.getMegstamosKnygos().add(knygosRepository.findById(id));
        vartotojoRepository.save(vartotojas);
        return "pamegtos_knygos.html";
    }
}
