package be.technifutur.java.timairport.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtProvider {

    public String createToken(Authentication auth) {

        Date issuedAt = new Date();
        Date expiresAt = new Date( System.currentTimeMillis() + (24*60*60*1000));

        try {
            JWT.create()
                    .withSubject( auth.getName() )
                    .withExpiresAt( expiresAt )
                    .withIssuedAt( issuedAt )
                    .sign(Algorithm.HMAC512("s3cr3t"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Internal Error", e);
        }

        return null;

    }

}
