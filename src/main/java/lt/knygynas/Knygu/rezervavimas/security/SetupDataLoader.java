package lt.knygynas.Knygu.rezervavimas.security;

import lt.knygynas.Knygu.rezervavimas.model.entity.Privilegijos;
import lt.knygynas.Knygu.rezervavimas.model.entity.Roles;
import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.PrivilegijosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.RolesRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private VartotojoRepository vartotojoRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PrivilegijosRepository privilegijosrepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilegijos skaitytiPrivilegije = sukurtiPrivilegijeJeiguJiNerasta("READ_PRIVILEGE");
        Privilegijos rasytiPrivilegije  = sukurtiPrivilegijeJeiguJiNerasta("WHRITE_PRIVILEGE");

        Roles adminRole = rolesRepository.findByPavadinimas("ROLE_ADMIN");
        Vartotojas vartotojas = new Vartotojas();
        vartotojas.setFirstName("vardas");
        vartotojas.setLastName("pavarde");
        vartotojas.setPassword("slaptazodis");
        vartotojas.setEmail("ask@as.com");
        vartotojas.setRoles(Arrays.asList(adminRole));
        vartotojas.setIjungta(true);
        vartotojoRepository.save(vartotojas);

        alreadySetup = true;
    }

    @Transactional
    Privilegijos sukurtiPrivilegijeJeiguJiNerasta(String vardas) {
        Privilegijos privilegijos = privilegijosrepository.findByPavadinimas(vardas);
        if (privilegijos == null){
            privilegijos = new Privilegijos();
            privilegijosrepository.save(privilegijos);
        }
        return privilegijos;
    }

    @Transactional
    Roles sukurtiRileJeiguJiNerasta(String pavadinimas, Collection<Privilegijos> privilegijos){
        Roles role = rolesRepository.findByPavadinimas(pavadinimas);
        if (role == null){
            role = new Roles();
            role.setPrivelegijos(privilegijos);
            rolesRepository.save(role);
        }
        return role;
    }
}
