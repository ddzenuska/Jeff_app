package Handlers;

import java.util.function.Consumer;
import java.util.function.Supplier;
import Client.Client;

public class FirstNameLastNameHandler implements PromptHandler {
    private final String FIRSTNAME_PROMPT = "🤖: What is your first name?";
    private final String LASTNAME_PROMPT = "🤖: And what is your last name, ";
    private final String MEETING_PROMPT = "🤖: Nice to meet you, ";
    private final String INVALID_FIRSTNAME_PROMPT = "Please write your first name correctly.";
    private final String INVALID_LASTNAME_PROMPT = "Please write your last name correctly.";
    private final String FIRSTNAME_REGEX = "[A-Za-z]+";
    private final String LASTNAME_REGEX = "[A-Za-z]+([ '-][A-Za-z]+)*";

    @Override
    public void handler(Consumer<String> output, Supplier<String> input, Client client) {
        while (true) {
            output.accept(FIRSTNAME_PROMPT);
            output.accept("👧: ");
            String firstName = input.get();

            if (isFirstNameValid(firstName)) {
                client.setFirstName(capitalizeFirstLetter(firstName));
                output.accept(LASTNAME_PROMPT + client.getFirstName() + "?");
                output.accept("👧: ");
                String lastName = input.get();
                if (isLastNameValid(lastName)) {
                    client.setLastName(capitalizeFirstLetter(lastName));
                    output.accept(MEETING_PROMPT + client.getFirstName() + " " + client.getLastName() + "!");
                    break;
                } else {
                    output.accept(INVALID_LASTNAME_PROMPT );
                }
            } else {
                output.accept(INVALID_FIRSTNAME_PROMPT);
            }
        }
    }

    private boolean isFirstNameValid( String firstName ) {
        return firstName.matches( FIRSTNAME_REGEX);
    }
    private boolean isLastNameValid( String lastName ) {
        return lastName.matches( LASTNAME_REGEX );
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}