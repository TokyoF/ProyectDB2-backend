package com.skillprint.skillprintbackend.Security.Filters;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillprint.skillprintbackend.Entity.Employed;
import com.skillprint.skillprintbackend.Security.Jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }



    @Override   //try to authenticate
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String username,password;
        try{

            Employed employed = new ObjectMapper().readValue(request.getInputStream(), Employed.class);  //toma los parametros user, password y lo mapeas a la clase Employed= new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);  //toma los parametros user, password y lo mapeas a la clase UserEntity
             username = employed.getUsername();
             password = employed.getPassword();

        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    //If the authentication is correct, proceed to the following method successful Out...
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        String token = jwtUtils.generateAccessToken(user.getUsername());  //generated the access token

        response.addHeader("Authorization", token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Authorization correct");
        httpResponse.put("Username", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));   //imprimiendo en la parte del body
        response.setStatus(HttpStatus.OK.value()); //enum Http.status
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
