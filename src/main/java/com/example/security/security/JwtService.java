package com.example.security.security;

import com.nimbusds.jose.JWSAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JwtService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateToken(String username) {
        Instant issuedAt = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(3600))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.from(JwsHeader.with(MacAlgorithm.HS256).build()).build(), claims)).getTokenValue();
    }

    public Jwt decodeToken(String token) {
        return jwtDecoder.decode(token);
    }

    public Collection<? extends GrantedAuthority> extractAuthorities(String token) {
        // todo: here you can place roles etc.
        return List.of();
    }
}
