package com.example.password;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private TextView feedbackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordEditText = findViewById(R.id.passwordEditText);
        feedbackTextView = findViewById(R.id.feedbackTextView);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(v -> {
            String password = passwordEditText.getText().toString();
            PasswordStrengthChecker.PasswordStrength strength = PasswordStrengthChecker.assessPasswordStrength(password);
            feedbackTextView.setText(getFeedbackMessage(strength));
        });
    }

    private String getFeedbackMessage(PasswordStrengthChecker.PasswordStrength strength) {
        switch (strength) {
            case WEAK:
                return "Password is weak. Try adding more characters and using a mix of different types.";
            case MEDIUM:
                return "Password is medium. Consider adding more special characters or numbers.";
            case STRONG:
                return "Password is strong. Good job!";
            case VERY_STRONG:
                return "Password is very strong. Excellent!";
            default:
                return "Error in assessing password strength.";
        }
    }
}
