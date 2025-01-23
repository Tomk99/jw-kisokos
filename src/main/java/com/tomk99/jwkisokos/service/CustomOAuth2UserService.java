package com.tomk99.jwkisokos.service;

import com.tomk99.jwkisokos.model.User;
import com.tomk99.jwkisokos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Get user information from OAuth2 response
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Check if user exists in the database
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            // Create a new user if not found
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setAllowed(false);
            newUser.setAdmin(false);
            return userRepository.save(newUser);
        });

        return oAuth2User;
    }
}
