package com.vedant.LifeOps.service;


import com.vedant.LifeOps.config.JwtProperties;
import com.vedant.LifeOps.model.RefreshToken;
import com.vedant.LifeOps.model.User;
import com.vedant.LifeOps.repo.RefreshTokenRepo;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepo refreshTokenRepo;
    private final JwtProperties jwtProperties;

    public RefreshTokenService(RefreshTokenRepo refreshTokenRepo,
                               JwtProperties jwtProperties) {
        this.refreshTokenRepo = refreshTokenRepo;
        this.jwtProperties = jwtProperties;
    }

    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(
                Instant.now().plusMillis(jwtProperties.getExpiration())
        );

        return refreshTokenRepo.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){

        if(token.getExpiryDate().isBefore(Instant.now())){
            refreshTokenRepo.delete(token);
            throw new RuntimeException("Refresh token expired. Please login again.");

        }
        return token;

    }

    public void deleteByUser(User user){
        refreshTokenRepo.deleteByUserId(user.getId());
    }

}
