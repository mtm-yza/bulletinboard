package com.bulletinBoard.system.api.common;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bulletinBoard.system.bl.service.user.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * <h2>JwtTokenUtil Class</h2>
 * <p>
 * Process for Displaying JwtTokenUtil
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Component
public class JwtTokenUtil {

    /**
     * <h2>EXPIRE_DURATION</h2>
     * <p>
     * EXPIRE_DURATION
     * </p>
     */
    private static final long TOKEN_EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    /**
     * <h2>SECRET_KEY</h2>
     * <p>
     * Secret Key to Generate Token
     * </p>
     */
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    /**
     * <h2>ISSUER</h2>
     * <p>
     * ISSUER
     * </p>
     */
    @Value("${app.jwt.issuer}")
    private String ISSUER;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>generateAccessToke</h2>
     * <p>
     * Generate Access Token
     * </p>
     *
     * @param user UserDetails
     *
     * @return String
     */
    @SuppressWarnings("unchecked")
    public String generateAccessToken(UserDetails userdetail) {
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) userdetail.getAuthorities();
        boolean isAdmin = ("ROLE_ADMIN".equals(authorities.get(0).toString()));
        return Jwts.builder().setSubject(userdetail.getUsername()).claim("isAdmin", isAdmin).setIssuer(ISSUER)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * <h2>validateAccessToken</h2>
     * <p>
     * Validate Access Token
     * </p>
     *
     * @param token String
     *
     * @return boolean
     */
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            System.out.println("Token: Token Expired");
        } catch (IllegalArgumentException | MalformedJwtException ex) {
            System.out.println("Token: Invalid Token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Token: Token is Not Supported");
        } catch (SignatureException ex) {
            System.out.println("Token: Signature Validation Failed");
        }
        return false;
    }

    /**
     * <h2>getExpiration</h2>
     * <p>
     * Get Token Expiration
     * </p>
     *
     * @param token String
     *
     * @return Date
     */
    public Date getExpiration(String token) {
        return parseClaims(token).getExpiration();
    }

    /**
     * <h2>getUserDetail</h2>
     * <p>
     * Get User Detail from Toke
     * </p>
     *
     * @param token
     * @return
     * @return UserDetails
     */
    public UserDetails getUserDetail(String token) {
        String email = parseClaims(token).getSubject();
        return this.userService.doGetUserByEmail(email);
    }

    /**
     * <h2>parseClaims</h2>
     * <p>
     * Parse Claims from Token
     * </p>
     *
     * @param token String
     *
     * @return Claims
     */
    private Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
