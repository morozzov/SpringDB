package com.example.springdb.adapter;

import com.example.springdb.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class UserInfoAdapter {

    private final RestTemplate restTemplate;

    public UserInfoDTO loadUserInfo(Long userId) {
        String uri = UriComponentsBuilder.fromUriString("http://jsonplaceholder.typicode.com/posts/%s")
                .toUriString();
        restTemplate.exchange("url", HttpMethod.GET, null, UserInfoDTO.class);
        ResponseEntity<UserInfoDTO> responseEntity = restTemplate.getForEntity(String.format(uri, userId), UserInfoDTO.class);
        return responseEntity.getBody();
    }
}
