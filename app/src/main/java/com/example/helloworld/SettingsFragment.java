package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.entity.Settings;
import com.example.helloworld.viewmodels.SettingsViewModel;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class SettingsFragment extends Fragment {

    public TextView uid;
    public TextView reminder_time;
    public TextView max_search;
    public TextView gender;
    public TextView account;
    public TextView age_range;

    private SettingsViewModel settingsViewModel;

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        uid = view.findViewById(R.id.uid);
        reminder_time = view.findViewById(R.id.reminder_time);
        max_search = view.findViewById(R.id.max_search);
        gender = view.findViewById(R.id.gender);
        account = view.findViewById(R.id.account);
        age_range = view.findViewById(R.id.age_range);

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        final Observer<List<Settings>> getSettingsObserver = newSettings -> {
            if(newSettings == null || newSettings.size() <= 0) {
                return;
            }

            Settings settings = newSettings.get(0);

            if(settings == null) {
                return;
            }

            reminder_time.setText(String.valueOf(settings.getTime()));
            max_search.setText(String.valueOf(settings.getDistance()));
            gender.setText(settings.getGender());

            if(settings.getPrivacy()) {
                account.setText(R.string.privacy_private);
            }
            else {
                account.setText(R.string.privacy_public);
            }
            age_range.setText(String.valueOf(settings.getAgeRange()));

        };

        String[] uids = { "00A" };
        settingsViewModel.loadAllByIds(this.getContext(), uids).observe(getViewLifecycleOwner(), getSettingsObserver);

        return view;
    }

    public void updateDatabase(View view) {
        Settings fakeSettings = new Settings();
        long currentTime = new Date().getTime();
        String uid = this.uid.getText().toString();
        if(uid.equals("")) {
            uid = "fake";
        }
        fakeSettings.setUid(uid);
        fakeSettings.setTime(currentTime);
        fakeSettings.setDistance(10.0);
        fakeSettings.setGender("Male");
        fakeSettings.setPrivacy(false);
        fakeSettings.setAgeRange(5);

        settingsViewModel.updateSettings(this.getContext(), fakeSettings);

        this.uid.setText(fakeSettings.getUid());
        reminder_time.setText(String.valueOf(fakeSettings.getTime()));
        max_search.setText(String.valueOf(fakeSettings.getDistance()));
        gender.setText(fakeSettings.getGender());
        if(fakeSettings.getPrivacy()) {
            account.setText(R.string.privacy_private);
        }
        else {
            account.setText(R.string.privacy_public);
        }
        age_range.setText(String.valueOf(fakeSettings.getAgeRange()));
    }

    public void deleteSettings(View view) {
        Settings currentSettings = new Settings();
        currentSettings.setUid(this.uid.getText().toString());
        currentSettings.setTime(Long.parseLong((reminder_time.getText().toString())));
        currentSettings.setDistance(Double.parseDouble(max_search.getText().toString()));
        currentSettings.setGender(gender.getText().toString());
        currentSettings.setPrivacy(Boolean.parseBoolean(account.getText().toString()));
        currentSettings.setAgeRange(Integer.parseInt(age_range.getText().toString()));

        settingsViewModel.deleteSettings(this.getContext(), currentSettings);
        uid.setText("");
        reminder_time.setText("");
        max_search.setText("");
        gender.setText("");
        account.setText("");
        age_range.setText("");
    }

}
