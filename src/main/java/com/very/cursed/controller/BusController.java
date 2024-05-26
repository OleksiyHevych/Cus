package com.very.cursed.controller;

import com.very.cursed.model.Buses;
import com.very.cursed.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://oleksiyhevych.github.io")
@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @GetMapping
    public ResponseEntity<List<Buses>> getBus() {
        return ResponseEntity.ok(busRepository.findAll());
    }

    @PostMapping
    public ResponseEntity createBus(@RequestBody Buses buses) {
        busRepository.save(buses);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBus(@PathVariable Integer id, @RequestBody Buses buses) {
        buses.setId(id);
        busRepository.save(buses);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable Integer id) {
        busRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/departure/{id}")
    public ResponseEntity departBus(@PathVariable Integer id) {
        Optional<Buses> optionalBus = busRepository.findById(id);
        if (optionalBus.isPresent()) {
            Buses bus = optionalBus.get();
            bus.setInpark(false);
            busRepository.save(bus);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/arrival/{id}")
    public ResponseEntity arriveBus(@PathVariable Integer id) {
        Optional<Buses> optionalBus = busRepository.findById(id);
        if (optionalBus.isPresent()) {
            Buses bus = optionalBus.get();
            bus.setInpark(true);
            busRepository.save(bus);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/parked")
    public ResponseEntity<List<Buses>> getParkedBuses() {
        List<Buses> parkedBuses = busRepository.findByinpark(true);
        return ResponseEntity.ok(parkedBuses);
    }

    @GetMapping("/onroute")
    public ResponseEntity<List<Buses>> getBusesOnRoute() {
        List<Buses> busesOnRoute = busRepository.findByinpark(false);
        return ResponseEntity.ok(busesOnRoute);
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Buses> parkedBuses = busRepository.findByinpark(true);
        List<Buses> busesOnRoute = busRepository.findByinpark(false);
        model.addAttribute("parkedBuses", parkedBuses);
        model.addAttribute("busesOnRoute", busesOnRoute);
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        List<Buses> parkedBuses = busRepository.findByinpark(true);
        List<Buses> busesOnRoute = busRepository.findByinpark(false);
        model.addAttribute("parkedBuses", parkedBuses);
        model.addAttribute("busesOnRoute", busesOnRoute);
        return "user";
    }
}