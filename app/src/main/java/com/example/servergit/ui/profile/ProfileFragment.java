package com.example.servergit.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.servergit.MainActivity;
import com.example.servergit.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private EditText editTextLogin;
    private EditText editTextAddress;
    private EditText editTextPassword;
    private View root;
    private Button buttonLogIn;
    private String login;
    private String gitAddress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextLogin = root.findViewById(R.id.editTextlogin);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        editTextAddress = root.findViewById(R.id.editTextAddress);
        this.root = root;

        buttonLogIn = root.findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(view -> {
            logIn(view);
        });

        TabHost tabHost = root.findViewById(R.id.profileTabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.tabLogin);
        tabSpec.setIndicator("Login");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tabProfile);
        tabSpec.setIndicator("Profile");
        tabHost.addTab(tabSpec);

        if (MainActivity.mSetting.getBoolean("is_logged", true)) {
            login = MainActivity.mSetting.getString("login", new String());
            gitAddress = MainActivity.mSetting.getString("gitAddress", new String());
            tabHost.setCurrentTab(1);
        } else {
            tabHost.setCurrentTab(0);
        }
//        final TextView textView = root.findViewById(R.id.text_profile);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void logIn(View view) {
        String login = String.valueOf(editTextLogin.getText());
        String password = String.valueOf(editTextPassword.getText());
        String address = String.valueOf(editTextAddress.getText());
        if (login.isEmpty() || password.isEmpty() || address.isEmpty()) {
            Toast toast = new Toast(root.getContext());
            toast.setText("Заполните все поля!");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } else {
            SharedPreferences.Editor editor = MainActivity.mSetting.edit();
            editor.putBoolean("is_logged", true).apply();
            editor.putString("login", login).apply();
            editor.putString("password", password).apply();
            editor.putString("gitAddress", address).apply();
        }
    }
}