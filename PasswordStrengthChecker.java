package com.example.password;

import java.util.regex.Pattern;

public class PasswordStrengthChecker {

    public enum PasswordStrength {
        WEAK, MEDIUM, STRONG, VERY_STRONG
    }

    public static PasswordStrength assessPasswordStrength(String password) {
        int lengthScore = calculateLengthScore(password);
        int complexityScore = calculateComplexityScore(password);
        int uniquenessScore = calculateUniquenessScore(password);

        int totalScore = lengthScore + complexityScore + uniquenessScore;

        if (totalScore <= 4) {
            return PasswordStrength.WEAK;
        } else if (totalScore <= 7) {
            return PasswordStrength.MEDIUM;
        } else if (totalScore <= 10) {
            return PasswordStrength.STRONG;
        } else {
            return PasswordStrength.VERY_STRONG;
        }
    }

    private static int calculateLengthScore(String password) {
        if (password.length() >= 12) {
            return 4;
        } else if (password.length() >= 8) {
            return 3;
        } else if (password.length() >= 6) {
            return 2;
        } else {
            return 1;
        }
    }

    private static int calculateComplexityScore(String password) {
        int complexityScore = 0;

        if (Pattern.compile("[a-z]").matcher(password).find()) complexityScore++;
        if (Pattern.compile("[A-Z]").matcher(password).find()) complexityScore++;
        if (Pattern.compile("[0-9]").matcher(password).find()) complexityScore++;
        if (Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) complexityScore++;

        return complexityScore;
    }

    private static int calculateUniquenessScore(String password) {
        String[] commonPasswords = {"123456","shaukat","password", "123456789", "12345678", "12345", "1234567", "qwerty", "abc123"};

        for (String commonPassword : commonPasswords) {
            if (password.equalsIgnoreCase(commonPassword)) {
                return 0;
            }
        }

        return 2;
    }
}
