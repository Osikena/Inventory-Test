package com.mega.amps.service;

import com.mega.amps.details.UserDetailsCustom;
import com.mega.amps.domain.User;
import com.mega.amps.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSevice implements UserDetailsService {

    private final UserRepository userRepository;
    private UserDetailsCustom userDetailsCustom;

    public UserSevice(UserRepository userRepository, UserDetailsCustom userDetailsCustom){
        this.userRepository = userRepository;
        this.userDetailsCustom = userDetailsCustom;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));

        return user.map(UserDetailsCustom::new).get();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username){
        return  userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long Id){
        return userRepository.findById(Id);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User saveAndFlush(User user){
        return userRepository.saveAndFlush(user);
    }

    public void deleteById(Long Id){
        userRepository.deleteById(Id);
    }

}
