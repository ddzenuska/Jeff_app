package Handlers;

import java.util.function.Consumer;
import java.util.function.Supplier;
import Client.Client;

public class IntroductionHandler implements PromptHandler {
    private final String GREETING_PROMPT = "🤖: Hi there, I'm Jeff 👋";
    private final String INTRODUCTION_PROMPT = "🤖: Your new best friend for finding great loan offers!";
    private final String SETTING_UP_PROMPT = "🤖: First things first - let's get your account set up 🛠️";

    @Override
    public void handler(Consumer<String> output, Supplier<String> input, Client client) {
        output.accept(GREETING_PROMPT);
        output.accept(INTRODUCTION_PROMPT);
        output.accept(SETTING_UP_PROMPT);
    }
}