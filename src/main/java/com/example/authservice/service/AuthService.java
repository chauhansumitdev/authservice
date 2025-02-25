package com.example.authservice.service;
import com.example.authservice.dto.Request;
import com.example.authservice.dto.Response;
import com.example.authservice.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AuthService {

    private final JwtUtil jwtUtil;


    @Autowired
    public AuthService(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    public Response generateTokens(Request request){

        Response response = new Response();

        String jwt = jwtUtil.generateJWTToken(request.getUsername(), request.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());

        response.setRefreshToken(refreshToken);
        response.setJwt(jwt);

        return response;
    }


    public Response refresh(Request request) throws  Exception{

        Response response = new Response();

        if(request.getRefreshToken() == null || request.getJwt() == null){
            throw  new Exception("INVALID TOKEN");
        }

        if(jwtUtil.validateToken(request.getRefreshToken())){
            String getusername = jwtUtil.extractUsername(request.getJwt());

            log.info(getusername);

            String getrole = jwtUtil.extractRole(request.getJwt());

            log.info(getrole);


            String token = jwtUtil.generateJWTToken(getusername, getrole);

            response.setJwt(token);
            return response;
        }

        throw  new Exception("INVLAID TOKEN");

    }


    public Response vefify(String token)throws Exception{

        Response response = new Response();

        log.info("VERIFYING TOKEN " +token);

        if(!jwtUtil.validateToken(token)){

            throw new Exception("INVALID TOKEN");
        }

        response.setRole(jwtUtil.extractRole(token));
        response.setUsername(jwtUtil.extractUsername(token));
        response.setStatusCode(200);
        response.setMessage("SUCCESS");
        return response;
    }


}
