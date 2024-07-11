package Handlers;

import Client.Client;
import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BirthInformationHandler implements PromptHandler {
    private static final String AGE_PROMPT_SETUP = "🤖: Fantastic. We are 70% done with the setup!";
    private static final String AGE_PROMPT = "🤖: Your age is another important value for finding the best offers. Please enter your date of birth YYYY-MM-DD 📅";
    private static final String UNDER_AGE_PROMPT = "🤖: Sorry, but you seem to be under the legal age of 18.";

    @Override
    public void handler(Consumer<String> output, Supplier<String> input, Client client) {
        output.accept(AGE_PROMPT_SETUP);
        output.accept(AGE_PROMPT);
        output.accept("👧: ");
        client.setAge(LocalDate.parse(input.get()));
        if (LocalDate.now().minusYears(18).isBefore(client.getAge())) {
            output.accept(UNDER_AGE_PROMPT);
            client.setIsOverEighteen(false);
        } else client.setIsOverEighteen(true);
    }
}