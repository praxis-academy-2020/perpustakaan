package com.project.perpustakaan.controller.service;


import org.springframework.beans.factory.annotation.Autowired;
import java.util.Calendar;
import java.util.Date;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.TimeZone;
import com.project.perpustakaan.repository.UserRepository;
import com.project.perpustakaan.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;




@RestController
public class Service {


    private final long denda = 5000;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    UserRepository userRepository;
    //membuat edit membernya

    //menghitung tagihannya by id tagihan
    public long hitungTagihanByTgl(Date d1){
            long tagihan;
            Date d2 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta")).getTime();  
            long diff = d2.getTime()-d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if(diffDays>7){
                    tagihan = (diffDays-7)*denda;
            }else tagihan = 0;
            return tagihan;
      }

      //cek akses dengan token
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        String bearerToken = this.getJwt(request);
        if (bearerToken == null) {
            return ResponseEntity.status(403).body("akses tidak diizinkan.");
        }
        long userId = tokenProvider.getUserIdFromJWT(bearerToken);
        return ResponseEntity.ok(userId);
    }

    //get user id by token
    public long getUserIdByToken(HttpServletRequest request) {
      String bearerToken = this.getJwt(request);
      long userId = tokenProvider.getUserIdFromJWT(bearerToken);
      return userId;
    }

    private String getJwt(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
          return bearerToken.substring(7, bearerToken.length());
      }

      return null;
  }
  
}
