package br.com.oobj.escalar.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${escalar.jwt.expiration}")
    private String expiration;
    @Value("${escalar.jwt.secret}")
    private String secret;

    public String gerarToken() {
        Date dataLogada = new Date();
        Date dataExpiracao = new Date(dataLogada.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("API do Projeto Escalar")
                .setIssuedAt(dataLogada)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
