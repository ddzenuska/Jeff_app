package Handlers;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Client.Client;

public class EmailHandler implements PromptHandler {
    public static final String EMAIL_ADDRESS_PROMPT = "🤖: What's your email address?";
    public static final String INVALID_EMAIL_PROMPT = "🤖: Please input a correct email address!";
    public static final String EMAIL_VALIDATION_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public void handler(Consumer<String> output, Supplier<String> input, Client client) {
        while (true) {
            output.accept(EMAIL_ADDRESS_PROMPT);
            output.accept("👧: ");
            String email = input.get();

            if (isEmailAddressValid(email)) {
                client.setEmail(email);
                break;
            } else {
                output.accept(INVALID_EMAIL_PROMPT);
            }
        }
    }

    public boolean isEmailAddressValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}