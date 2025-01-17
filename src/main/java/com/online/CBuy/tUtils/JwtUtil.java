package com.online.CBuy.tUtils;

import com.online.CBuy.document.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

//    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Khóa bí mật, nên lưu trong biến môi trường
      String secretKey = "701f3c4ea36b48cacb4fb3576978cd792a7e3388ba8389874fb222d6fcc0dcea"; // Khóa phải giống nhau

    // Tạo token
    public String generateToken(Account username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Hết hạn sau 10 giờ
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    // Lấy username từ token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Kiểm tra token còn hiệu lực không
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // Kiểm tra token hết hạn
    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}