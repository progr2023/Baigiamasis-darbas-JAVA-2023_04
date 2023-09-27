package lt.viltiesziedas.filmai.model.sampledata;

import lt.viltiesziedas.filmai.model.entity.*;
import lt.viltiesziedas.filmai.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DuomenuUzpildymas implements ApplicationListener <ContextRefreshedEvent> {
    boolean arJauYra = true;

    @Autowired
    FilmoKategorijaRepository filmoKategorijaRepository;
    @Autowired
    FilmoRepository filmoRepository;

    @Autowired
    VartotojasRepository vartotojasRepository;

    @Autowired
    KomentarasRepository komentarasRepository;

    @Autowired
    PrivilegijaRepository privilegijaRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!arJauYra) {
            sukurtiKategorijas();
            sukurtiFilmus();
            sukurtiVartotojus();
            sukurtiKomentarus();
        }

    }

    public void sukurtiKategorijas() {
        FilmoKategorija kategorija1 = new FilmoKategorija();
        kategorija1.setPavadinimas("Veiksmo");
        FilmoKategorija kategorija2 = new FilmoKategorija();
        kategorija2.setPavadinimas("Trileris");
        FilmoKategorija kategorija3 = new FilmoKategorija();
        kategorija3.setPavadinimas("Istorinis");
        FilmoKategorija kategorija4 = new FilmoKategorija();
        kategorija4.setPavadinimas("Epinis");

        filmoKategorijaRepository.save(kategorija1);
        filmoKategorijaRepository.save(kategorija2);
        filmoKategorijaRepository.save(kategorija3);
        filmoKategorijaRepository.save(kategorija4);

    }

    public void sukurtiFilmus() {
        Filmai filmas1 = new Filmai();
        filmas1.setPavadinimas("Linkolnas");
        filmas1.setAprasymas("JAV istorinė biografinė drama, režisuota ir prodiusuota Steven Spielberg");
        filmas1.setImdbReitingas(7.3);
        FilmoKategorija kategorija1 = new FilmoKategorija();
        kategorija1.setId(1);
        filmas1.setKategorija(kategorija1);


        Filmai filmas2 = new Filmai();
        filmas2.setPavadinimas("Spiraliniai laiptai");
        filmas2.setAprasymas("Trileris. Serijinis žudikas renkasi moteris su negalia, ir taip, vieną naktį audros metu...");
        filmas2.setImdbReitingas(7.3);
        FilmoKategorija kategorija2 = new FilmoKategorija();
        kategorija2.setId(2);
        filmas2.setKategorija(kategorija2);

        Filmai filmas3 = new Filmai();
        filmas3.setPavadinimas("Babilonas");
        filmas3.setAprasymas("Drama. Ankstyvajame Holivude vienų veikėjų laukė didžiuliai užmojai, kitų – siaubingas nuosmukis");
        filmas3.setImdbReitingas(7.1);
        FilmoKategorija kategorija3 = new FilmoKategorija();
        kategorija3.setId(3);
        filmas3.setKategorija(kategorija3);

        Filmai filmas4 = new Filmai();
        filmas4.setPavadinimas("Gladiatorius");
        filmas4.setAprasymas("Epinis filmas. Įgudęs karys Maksimas sėkmingai kaunasi arenose ir tampa garsus kaip nuostabus gladiatorius");
        filmas4.setImdbReitingas(8.5);
        FilmoKategorija kategorija4 = new FilmoKategorija();
        kategorija4.setId(4);
        filmas4.setKategorija(kategorija4);

        Filmai filmas5 = new Filmai();
        filmas5.setPavadinimas("Juodoji pantera");
        filmas5.setAprasymas("Filmą režisavo Ryan Coogler, kuris kartu su Joe Robert Cole parašė scenarijų.");
        filmas5.setImdbReitingas(7.3);
        FilmoKategorija kategorija5 = new FilmoKategorija();
        kategorija5.setId(1);
        filmas5.setKategorija(kategorija5);

        filmoRepository.save(filmas1);
        filmoRepository.save(filmas2);
        filmoRepository.save(filmas3);
        filmoRepository.save(filmas4);
        filmoRepository.save(filmas5);
    }

    public void sukurtiVartotojus() {

        Privilegija readPrivilegija = sukurtiPrivilegijaJeiguNera("READ_PRIVILEGIJA");
        Privilegija writePrivilegija = sukurtiPrivilegijaJeiguNera("WRITE_PRIVILEGIJA");
        Set<Privilegija> adminoPrivilegijos = Stream.of(readPrivilegija, writePrivilegija).collect(Collectors.toSet());
        Set<Privilegija> vartotojoPrivilegijos = Stream.of(readPrivilegija).collect(Collectors.toSet());
        Role adminoRole = sukurtiRoleJeiguNera("ROLE_ADMIN", adminoPrivilegijos);
        Role vartotojoRole = sukurtiRoleJeiguNera("ROLE_USER", vartotojoPrivilegijos);

        Vartotojai vartotojas1 = new Vartotojai();
        vartotojas1.setUsername("jonas");
        vartotojas1.setPassword(passwordEncoder.encode("kasparas456"));
        vartotojas1.setVardas("Vardenis");
        vartotojas1.setPavarde("Pavardenis");
        vartotojas1.setEnabled(true);
        vartotojas1.setExpiredToken(false);
        vartotojas1.setRoles(Stream.of(adminoRole).collect(Collectors.toSet()));

        Vartotojai vartotojas2 = new Vartotojai();
        vartotojas2.setUsername("kirpykla256");
        vartotojas2.setPassword(passwordEncoder.encode("zirkles777"));
        vartotojas2.setVardas("Jonas");
        vartotojas2.setPavarde("Jonaitis");
        vartotojas2.setEnabled(true);
        vartotojas2.setExpiredToken(false);
        vartotojas2.setRoles(Stream.of(vartotojoRole).collect(Collectors.toSet()));

        vartotojasRepository.save(vartotojas1);
        vartotojasRepository.save(vartotojas2);
    }

    public void sukurtiKomentarus() {
        Komentarai komentaras1 = new Komentarai();
        komentaras1.setTekstas("Labai patiko.");
        komentaras1.setPridejimoData(new Date());
        Filmai filmas1 = new Filmai();
        filmas1.setId(1);
        komentaras1.setFilmai(filmas1);
        Vartotojai vartotojas1 = new Vartotojai();
        vartotojas1.setId(1);
        komentaras1.setVartotojai(vartotojas1);

        Komentarai komentaras2 = new Komentarai();
        komentaras2.setTekstas("Prastas filmas.");
        komentaras2.setPridejimoData(new Date());
        Filmai filmas2 = new Filmai();
        filmas2.setId(1);
        komentaras2.setFilmai(filmas2);
        Vartotojai vartotojas2 = new Vartotojai();
        vartotojas2.setId(2);
        komentaras2.setVartotojai(vartotojas2);

        komentarasRepository.save(komentaras1);
        komentarasRepository.save(komentaras2);
    }

    public Privilegija sukurtiPrivilegijaJeiguNera(String name) {
        Privilegija privilegija = privilegijaRepository.findByPavadinimas(name);
        if (privilegija == null) {
            privilegija = new Privilegija();
            privilegija.setPavadinimas(name);
            privilegijaRepository.save(privilegija);
        }
        return privilegija;
    }
    public Role sukurtiRoleJeiguNera(String name, Set<Privilegija> privilegijos) {
        Role role = roleRepository.findByPavadinimas(name);
        if (role == null) {
            role = new Role();
            role.setPavadinimas(name);
            role.setPrivilegijos(privilegijos);
            roleRepository.save(role);
        }
        return role;
    }
}
