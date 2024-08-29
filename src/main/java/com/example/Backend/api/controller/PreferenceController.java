package com.example.Backend.api.controller;
import com.example.Backend.api.model.Preference;
import com.example.Backend.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PreferenceController {
    private final PreferenceService prefService;

    @Autowired
    public PreferenceController(PreferenceService prefService) {
        this.prefService = prefService;
    }

    @GetMapping("/pref/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getPreference(@PathVariable String email) {
        return prefService.getPreference(email);
    }

    @PostMapping("/pref/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> postPreference(@PathVariable String id, @RequestBody Preference newPreference) {
        return prefService.postPreference(id, newPreference);
    }

    @PutMapping("/pref/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> putPreference(@PathVariable String email, @RequestBody Preference updatedPreference) {
        return prefService.putPreference(email, updatedPreference);
    }

    @DeleteMapping("/pref/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deletePreference(@PathVariable String id) {
        return prefService.deletePreference(id);
    }
}
