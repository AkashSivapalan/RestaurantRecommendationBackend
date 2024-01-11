package com.example.Backend.api.controller;
import com.example.Backend.api.model.Preference;
import com.example.Backend.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PreferenceController {
    private final PreferenceService prefService;

    @Autowired
    public PreferenceController(PreferenceService prefService) {
        this.prefService = prefService;
    }

    @GetMapping("/pref/{id}")
    public ResponseEntity<?> getPreference(@PathVariable String id) {
        return prefService.getPreference(id);
    }

    @PostMapping("/pref/{id}")
    public ResponseEntity<?> postPreference(@PathVariable String id, @RequestBody Preference newPreference) {
        return prefService.postPreference(id, newPreference);
    }

    @PutMapping("/pref/{id}")
    public ResponseEntity<?> putPreference(@PathVariable String id, @RequestBody Preference updatedPreference) {
        return prefService.putPreference(id, updatedPreference);
    }

    @DeleteMapping("/pref/{id}")
    public ResponseEntity<?> deletePreference(@PathVariable String id) {
        return prefService.deletePreference(id);
    }
}
