package com.revature.revaturetrainingroomplanner.ui.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.revature.revaturetrainingroomplanner.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment {

    private NavController mNavController;
    private Button buttonSubmit;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        buttonSubmit = root.findViewById(R.id.btn_forgot_password_submit);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        buttonSubmit = view.findViewById(R.id.btn_forgot_password_submit);
        buttonSubmit.setOnClickListener(v -> {
            EditText email = view.findViewById(R.id.et_forgot_password_input);
            // TO DO: send reset password link to email
            String emailMsg = "An e-mail with further instruction will be sent to your email address.";
            Snackbar.make(v, emailMsg, Snackbar.LENGTH_LONG).show();
            mNavController.navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetPasswordFragment());
        });
    }
}
