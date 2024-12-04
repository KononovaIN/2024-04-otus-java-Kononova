package main.web;

import java.util.*;
import main.entity.User;
import main.repos.UserRepository;
import main.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Autowired
  UserRepository userRep;
  @Autowired
  PasswordEncoder pwdEncoder;

  @PostMapping(value = "/singing", consumes = "application/json", produces = "application/json")
  public ResponseEntity addUser(@RequestBody AuthRequest request) {
    try {
      String name = request.getUserName();

      Optional<User> userByUserName = userRep.findUserByUserName(name);
      if (userByUserName.isEmpty()) {
        userRep.save(new User(name, pwdEncoder.encode(request.getPassword()),
            Collections.singletonList("ROLE_USER")));
      }

      User user = userRep.findUserByUserName(name)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      String token = jwtTokenProvider.createToken(
          name,
          user.getRoles()
      );

      Map<Object, Object> model = new HashMap<>();
      model.put("userName", name);
      model.put("token", token);
      model.put("role", user.getRoles());

      return ResponseEntity.ok(model);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid userName or password");
    }
  }
}
