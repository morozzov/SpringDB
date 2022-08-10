package com.example.springdb.service;

import com.example.springdb.adapter.UserInfoAdapter;
import com.example.springdb.advice.Audit;
import com.example.springdb.data.PlayerRepository;
import com.example.springdb.dto.CreatePlayerDTO;
import com.example.springdb.dto.PlayerDTO;
import com.example.springdb.dto.ResponseDTO;
import com.example.springdb.dto.UserInfoDTO;
import com.example.springdb.entity.PlayerEntity;
import com.example.springdb.entity.PlayerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final UserInfoAdapter userInfoAdapter;

    public PlayerEntity getPlayerById(Long id) {
        log.info("getPlayerById.in - searching player {}", id);
        Optional<PlayerEntity> byId = playerRepository.findById(id);
        if (byId.isPresent()) {
            log.info("getPlayerById.out - found player {}", id);
            return byId.get();
        } else {
            log.error("getPlayerById.out - not found player {}", id);
            throw new EntityNotFoundException("Entity with given id not found!");
        }
    }

    @Audit
    public ResponseEntity<ResponseDTO> findPlayersByStatus(PlayerStatus status) {
        try {
            List<PlayerDTO> collect = playerRepository.findAll().stream()
                    .filter(playerEntity -> status.isVal() != playerEntity.isTerminated())
                    .map(this::map)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ResponseDTO.builder().result(collect).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Transactional
    public void create(CreatePlayerDTO createPlayerDTO) {
        Optional<PlayerEntity> byNickName = playerRepository.findByNickName(createPlayerDTO.getNickname());
        if (byNickName.isPresent()) {
            throw new EntityExistsException(String.format("Player with nickname %s already exists!", createPlayerDTO.getNickname()));
        }
        var playerEntity = PlayerEntity.builder().nickName(createPlayerDTO.getNickname()).build();
        PlayerEntity saved = playerRepository.save(playerEntity);
        UserInfoDTO userInfoDTO = userInfoAdapter.loadUserInfo(saved.getId());
        saved.setProfileInfo(userInfoDTO.getTitle());
        playerRepository.save(saved);
        log.info("create.out - created user");
    }

    public PlayerDTO map(PlayerEntity playerEntity) {
        return PlayerDTO.builder()
                .nickname(playerEntity.getNickName())
                .id(playerEntity.getId())
                .description(playerEntity.getProfileInfo()).build();
    }
}
