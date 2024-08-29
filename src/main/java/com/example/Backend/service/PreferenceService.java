package com.example.Backend.service;

import com.example.Backend.api.model.Preference;
import org.springframework.stereotype.Service;
import com.example.Backend.api.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreferenceService {
    private List<Preference> prefList;
    private final PreferenceRepository preferenceRepository;


    @Autowired
    public PreferenceService(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public ResponseEntity<?> getPreference(String email) {
        Optional<Preference> preference = this.preferenceRepository.findByEmail(email);

        if (preference.isPresent()) {
            return ResponseEntity.ok(preference);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> postPreference(String id, Preference pref) {

        if (this.preferenceRepository.existsById(id)) {
            return ResponseEntity.status(409).body("User Preference Already Exists");
        }

        return ResponseEntity.status(201).body(this.preferenceRepository.save(pref));
    }

    public ResponseEntity<?> putPreference(String email, Preference updatedPreference) {
        Optional<Preference> currentPreference = this.preferenceRepository.findByEmail(email);

        if (currentPreference.isPresent()) {
            updatedPreference.setId(currentPreference.get().getId());
            return ResponseEntity.status(204).body(this.preferenceRepository.save(updatedPreference));
        }

        return ResponseEntity.status(404).body("User Not Found");
    }

    public ResponseEntity<?> deletePreference(String id) {
        Optional<Preference> preference = this.preferenceRepository.findById(id);

        if (preference.isPresent()) {
            this.preferenceRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).body("User Not Found");
    }
}