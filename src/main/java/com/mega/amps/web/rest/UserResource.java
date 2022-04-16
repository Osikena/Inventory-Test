package com.mega.amps.web.rest;

import com.mega.amps.domain.User;
import com.mega.amps.domain.enumeration.UserRoles;
import com.mega.amps.dto.LoginDTO;
import com.mega.amps.dto.UserDTO;
import com.mega.amps.dto.response.GenericResponse;
import com.mega.amps.dto.response.ResponseDTO;
import com.mega.amps.jwtconfig.JWTFilter;
import com.mega.amps.jwtconfig.TokenProvider;
import com.mega.amps.mapper.UserMapper;
import com.mega.amps.service.UserSevice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserSevice userSevice;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    public UserResource(UserSevice userSevice, AuthenticationManagerBuilder authenticationManagerBuilder,
                        TokenProvider tokenProvider, UserMapper userMapper, AuthenticationManager authenticationManager){
        this.userSevice = userSevice;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authorize(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse res) throws Exception{

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        ResponseDTO response = new ResponseDTO();

        UsernamePasswordAuthenticationToken authenticationToken;

        try{

            authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            authenticationManager.authenticate(authenticationToken);
            final UserDetails userDetails = userSevice.loadUserByUsername(username);
            String jwt = tokenProvider.generateToken(userDetails);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            response.setCode("00");
            response.setMessage("Login Success");
            response.setToken(jwt);

            return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

        }catch (BadCredentialsException e){

            response.setCode("99");
            response.setMessage("Incorrect Username or Password");
            response.setToken(null);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/connection")
    public ResponseEntity<GenericResponse> getConnection() throws Exception{

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("Successful");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/connection/admin")
    public ResponseEntity<GenericResponse> getAdminConnection() throws Exception{

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("Admin Successful");

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenericResponse> createUser(@Valid @RequestBody UserDTO userDTO){

        String datetime = userDTO.getDate_time_created();
        LocalDateTime date_time;
        if (datetime == null){
            date_time = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            datetime = date_time.format(dateTimeFormatter);
            userDTO.setDate_time_created(datetime);
        }

        if(userDTO.getRole().equalsIgnoreCase("user")){

            userDTO.setRole(UserRoles.ROLE_USER.toString());

        } else if(userDTO.getRole().equalsIgnoreCase("admin")){

            userDTO.setRole(UserRoles.ROLE_ADMIN.toString());

        }

        User user = userMapper.toUser(userDTO);

        userSevice.saveAndFlush(user);
        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("User Created Successfully");
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
