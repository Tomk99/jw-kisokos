package com.tomk99.jwkisokos.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        String googleAuthUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?response_type=code"
                + "&client_id=134321666322-bq2vafnrfdt56khpl7av682j96j4lfbv.apps.googleusercontent.com"
                + "&scope=email%20profile"
                + "&redirect_uri=https://jw-kisokos-aa53f6432494.herokuapp.com/login/oauth2/code/google"
                + "&state=random_state_string"; // Használj biztonságos generált állapotot
        response.sendRedirect(googleAuthUrl);
    }

    @GetMapping("/callback")
    public String handleGoogleCallback(@RequestParam("code") String code, @RequestParam("state") String state) {
        // A kódot kicseréled egy hozzáférési tokenre (Google API hívással)
        String accessToken = exchangeCodeForAccessToken(code);

        // A token tárolása vagy felhasználói adatainak lekérése
        return "Sikeres hitelesítés! Hozzáférési token: " + accessToken;
    }

    private String exchangeCodeForAccessToken(String code) {
        // Használj Google API kliens könyvtárat vagy manuális HTTP hívást a kód cseréjéhez
        // Példa: RestTemplate vagy HttpClient
        return "dummy_access_token"; // Ezt cseréld le az API válaszára
    }
}
