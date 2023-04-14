package org.example.service;

import org.example.enums.UserRole;
import org.example.model.UserCredentials;
import org.example.repository.UserCredentialsRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserCredentialsService implements UserDetailsService {
    private final UserCredentialsRepository userCredentialsRepository;

    public UserCredentialsService(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("load user by username " + username);
        UserCredentials userCredentials = userCredentialsRepository.findByEmail(username);
        if (userCredentials == null) {
            throw new UsernameNotFoundException("No userCredentials with email " + username);
        }

        var authorities = new ArrayList<SimpleGrantedAuthority>();
        if (userCredentials.getRole() == UserRole.USER) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (userCredentials.getRole() == UserRole.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(userCredentials.getEmail(), userCredentials.getPassword(), authorities);
    }

    public UserCredentials create(UserCredentials userCredentials) {
        if (userCredentialsRepository.existsByEmail(userCredentials.getEmail())) {
            return null;
        }
        return userCredentialsRepository.save(userCredentials);
    }

    public List<UserCredentials> list() {
        return userCredentialsRepository.findAll();
    }

    public UserCredentials get(Long userId) {
        return userCredentialsRepository.findById(userId).orElseThrow();
    }

    public UserCredentials update(UserCredentials userCredentials) {
        if (!userCredentialsRepository.existsById(userCredentials.getId())) throw new NoSuchElementException();
        return userCredentialsRepository.save(userCredentials);
    }

    public void delete(Long userId) {
        if (!userCredentialsRepository.existsById(userId)) throw new NoSuchElementException();
        userCredentialsRepository.deleteById(userId);
    }
}
