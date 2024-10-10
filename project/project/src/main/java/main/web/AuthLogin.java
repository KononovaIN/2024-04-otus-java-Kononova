package main.web;

import java.util.HashMap;
import java.util.Map;
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
@RequestMapping("/login")
public class AuthLogin {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Autowired
  UserRepository userRep;
  @Autowired
  PasswordEncoder pwdEncoder;

  @PostMapping(value = "/process-login", consumes = "application/json", produces = "application/json")
  public ResponseEntity login(@RequestBody AuthRequest request) {
    try {
      String name = request.getUserName();

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
