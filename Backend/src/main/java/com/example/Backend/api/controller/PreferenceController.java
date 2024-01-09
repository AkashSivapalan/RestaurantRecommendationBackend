package com.example.Backend.api.controller;
import com.example.Backend.api.model.Preference;
import com.example.Backend.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PreferenceController {
    private final PreferenceService prefService;

    @Autowired
    public PreferenceController(PreferenceService prefService) {
        this.prefService = prefService;
    }

    @PostMapping("/pref")
    public Preference postPreference(@RequestParam String id, @RequestBody Preference newPreference) {
        Optional<Preference> pref = prefService.postPreference(id, newPreference);
        return (Preference) pref.orElse(null);
    }

    @GetMapping("/pref")
    public Preference getPreference(@RequestParam String id) {
        Optional<Preference> pref = prefService.getPreference(id);
        return (Preference) pref.orElse(null);
    }

    @PutMapping("/pref")
    public Preference putPreference(@RequestParam String id, @RequestBody Preference updatedPreference) {
        Optional<Preference> pref = prefService.putPreference(id, updatedPreference);
        return (Preference) pref.orElse(null);
    }

    @DeleteMapping("/pref")
    public String deletePreference(@RequestParam String id) {
        return prefService.deletePreference(id);
    }
}
