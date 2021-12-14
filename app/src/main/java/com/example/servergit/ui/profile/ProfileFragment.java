package com.example.servergit.ui.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
    private String password;
    private TabHost tabHost;
    private Button buttonSetting;
    private Button buttonSettingBack;
    private Button buttonLogOut;
    private TextView textViewLogin;
    private TextView textViewAddress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextLogin = root.findViewById(R.id.editTextlogin);
        editTextPassword = root.findViewById(R.id.editTextPassword);
        editTextAddress = root.findViewById(R.id.editTextAddress);
        this.root = root;

        textViewLogin = root.findViewById(R.id.textViewLogin);
        textViewAddress = root.findViewById(R.id.textViewAddress);

        buttonLogIn = root.findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(view -> {
            logIn();
        });

        buttonSetting = root.findViewById(R.id.buttonSetting);
        buttonSetting.setOnClickListener(view -> {
            goToSetting();
        });

        buttonSettingBack = root.findViewById(R.id.buttonSettingBack);
        buttonSettingBack.setOnClickListener(view -> {
            goToProfile();
        });

        buttonLogOut = root.findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(view -> {
            logOut();
        });

        tabHost = root.findViewById(R.id.profileTabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.tabLogin);
        tabSpec.setIndicator("Login");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tabProfile);
        tabSpec.setIndicator("Profile");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.tabSetting);
        tabSpec.setIndicator("Setting");
        tabHost.addTab(tabSpec);

        if (MainActivity.mSetting.getBoolean("is_logged", true)) {
            login = MainActivity.mSetting.getString("login", new String());
            gitAddress = MainActivity.mSetting.getString("gitAddress", new String());
            password = MainActivity.mSetting.getString("password", new String());
            textViewLogin.setText(login);
            textViewAddress.setText(gitAddress);
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

    private void logIn() {
        login = String.valueOf(editTextLogin.getText());
        password = String.valueOf(editTextPassword.getText());
        gitAddress = String.valueOf(editTextAddress.getText());
        if (login.isEmpty() || password.isEmpty() || gitAddress.isEmpty()) {
            Toast toast = new Toast(root.getContext());
            toast.setText("Заполните все поля!");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } else {
            SharedPreferences.Editor editor = MainActivity.mSetting.edit();
            editor.putBoolean("is_logged", true).apply();
            editor.putString("login", login).apply();
            editor.putString("password", password).apply();
            editor.putString("gitAddress", gitAddress).apply();
            textViewLogin.setText(login);
            textViewAddress.setText(gitAddress);
            tabHost.setCurrentTab(1);
        }
    }
    private void goToSetting() {
        tabHost.setCurrentTab(2);
    }

    private void goToProfile() {
        tabHost.setCurrentTab(1);
    }

    private void logOut() {
        SharedPreferences.Editor editor = MainActivity.mSetting.edit();
        editor.putBoolean("is_logged", false).apply();
        tabHost.setCurrentTab(0);
    }
}