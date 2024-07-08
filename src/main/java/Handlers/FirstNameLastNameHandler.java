package Handlers;

import java.util.function.Consumer;
import java.util.function.Supplier;
import Client.Client;

public class FirstNameLastNameHandler implements PromptHandler {
    private static final String FIRSTNAME_PROMPT = "🤖: What is your first name?";
    private static final String LASTNAME_PROMPT = "🤖: And what is your last name, ";
    private static final String MEETING_PROMPT = "🤖: Nice to meet you, ";

    @Override
    public void handler(Consumer<String> output, Supplier<String> input, Client client) {
        output.accept(FIRSTNAME_PROMPT);
        output.accept("👧: ");
        client.setFirstName(input.get());
        output.accept(LASTNAME_PROMPT + client.getFirstName() + "?");
        output.accept("👧: ");
        client.setLastName(input.get());

        output.accept(MEETING_PROMPT + client.getFirstName() + " " + client.getLastName() + "!");
    }
}