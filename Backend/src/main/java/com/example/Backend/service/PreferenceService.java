package com.example.Backend.service;

import com.example.Backend.api.model.Preference;
import org.springframework.stereotype.Service;
import com.example.Backend.api.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreferenceService {
    private final List<Preference> prefList;


    public PreferenceService() {
        prefList = new ArrayList<>();

        Preference pref1 = new Preference("user1", 1, 1, false, false, false, false);
        Preference pref2 = new Preference("user2", 1, 1, false, false, false, false);

        prefList.add(pref1);
        prefList.add(pref2);
    }

    public Optional<Preference> getPreference(String id) {
        Optional<Preference> preference = Optional.empty();
        //preference = this.preferenceRepository.findById(id);
        for (Preference pref : prefList) {
            if (pref.getId().equals(id)) {
                preference = Optional.of(pref);
                return preference;
            }
        }
        return preference;
    }

    public Optional<Preference> postPreference(String id, Preference pref) {
        Optional<Preference> optional = Optional.empty();
        if (prefList.stream().noneMatch(p -> p.getId().equals((id)))) {
            prefList.add(pref);
            optional = Optional.of(pref);
        }
        return optional;
    }

    public Optional<Preference> putPreference(String id, Preference upPref) {
        Optional<Preference> existingPreference = prefList.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (existingPreference.isPresent()) {
            Preference prefToUpdate = existingPreference.get();

            prefToUpdate.setPrice(upPref.getPrice());
            prefToUpdate.setDistance(upPref.getDistance());
            prefToUpdate.setHalal(upPref.isHalal());
            prefToUpdate.setGlutenFree(upPref.isGlutenFree());
            prefToUpdate.setVegan(upPref.isVegan());
            prefToUpdate.setVegetarian(upPref.isVegetarian());
        }
        return existingPreference;
    }

    public String deletePreference(String id) {
        boolean flag = prefList.removeIf(x -> x.getId().equals(id));
        return !flag ? "User not found" : "User deleted";
    }
}