package lt.viltiesziedas.filmai.security;

import lt.viltiesziedas.filmai.model.entity.Privilegija;
import lt.viltiesziedas.filmai.model.entity.Role;
import lt.viltiesziedas.filmai.model.entity.Vartotojai;
import lt.viltiesziedas.filmai.model.repository.RoleRepository;
import lt.viltiesziedas.filmai.model.repository.VartotojasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service("userDetailsService")
@Transactional

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    VartotojasRepository vartotojasRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Vartotojai vartotojas = vartotojasRepository.findByUsername(username);
        if (vartotojas == null) {
            return new User(" ", " ", true, true, true, true,
                    getGrantedAuthorities(gautiLeidimus(Stream.of(roleRepository.findByPavadinimas("ROLE_USER")).collect(Collectors.toSet()))));
        }
        return new User(vartotojas.getUsername(), vartotojas.getPassword(),
                vartotojas.isEnabled(), true, true, true,
                getGrantedAuthorities(gautiLeidimus(vartotojas.getRoles())));
    }

    private List<String> gautiLeidimus(Set<Role> roles) {
        List<String> leidimai = new ArrayList<>();
        List<Privilegija> privilegijuKolekcija = new ArrayList<>();
        for (Role role : roles) {
            leidimai.add(role.getPavadinimas());
            privilegijuKolekcija.addAll(role.getPrivilegijos());
        }
        for (Privilegija privilegija : privilegijuKolekcija) {
            leidimai.add(privilegija.getPavadinimas());
        }
        return leidimai;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> leidimai) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String leidimas : leidimai) {
            authorities.add(new SimpleGrantedAuthority(leidimas));
        }
        return authorities;


    }
}