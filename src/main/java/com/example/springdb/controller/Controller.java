package com.example.springdb.controller;

import com.example.springdb.dto.CreatePlayerDTO;
import com.example.springdb.dto.PlayerDTO;
import com.example.springdb.dto.ResponseDTO;
import com.example.springdb.entity.PlayerEntity;
import com.example.springdb.entity.PlayerStatus;
import com.example.springdb.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final PlayerService service;

    @GetMapping("/player/{id}")
    public PlayerEntity loadPlayer(@PathVariable("id") Long id) {
        return service.getPlayerById(id);
    }

    @GetMapping("/player")
    public ResponseEntity<ResponseDTO> loadPlayers(@RequestParam("status") PlayerStatus status) {
        return service.findPlayersByStatus(status);
    }

    @PostMapping("/player")
    public void create(@RequestBody CreatePlayerDTO createPlayerDTO) {
        service.create(createPlayerDTO);
    }
}
