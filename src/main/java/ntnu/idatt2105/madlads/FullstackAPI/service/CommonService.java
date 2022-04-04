package ntnu.idatt2105.madlads.FullstackAPI.service;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class for common services
 * Like sending and email with generated password
 */
public class CommonService {
    /**
     * Send email with user credentials
     *
     * @param toMail
     * @param text   content of the email
     */
    public static void sendEmail(String toMail, String text) {
        EmailService emailService = new EmailService(toMail, text);
        emailService.start();
    }

    /**
     * Checking if the email is in email-format
     *
     * @param email
     * @return
     */
    public static boolean regexEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        return pattern.matcher(email).matches();
    }

    /**
     * Generates a password when registering a user.
     *
     * @return
     */
    public static String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }
}
