package com.example.servergit.ui.profile;

import android.content.Context;
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

import com.example.servergit.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private EditText editTextLogin;
    private EditText editTextAddress;
    private EditText editTextPassord;
    private View root;
    private Button buttonLogIn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextLogin = root.findViewById(R.id.editTextlogin);
        editTextPassord = root.findViewById(R.id.editTextPassword);
        editTextAddress = root.findViewById(R.id.editTextAddress);
        this.root = root;

        buttonLogIn = root.findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(view -> {
            String login = String.valueOf(editTextLogin.getText());
            String password = String.valueOf(editTextPassord.getText());
            String address = String.valueOf(editTextAddress.getText());
            if (login.isEmpty() || password.isEmpty() || address.isEmpty()) {
                Toast toast = new Toast(root.getContext());
                toast.setText("Заполните все поля!");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
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

        tabHost.setCurrentTab(0);
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
        String password = String.valueOf(editTextPassord.getText());
        String address = String.valueOf(editTextAddress.getText());
        if (login.isEmpty() || password.isEmpty() || address.isEmpty()) {
            Toast toast = new Toast(root.getContext());
            toast.setText("Заполните все поля!");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}