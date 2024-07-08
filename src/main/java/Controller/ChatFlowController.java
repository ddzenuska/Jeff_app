package Controller;

import Client.Client;
import Handlers.PromptHandler;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChatFlowController implements ChatFlowControllerInterface {
    private final Consumer<String> output;
    private final Supplier<String> input;
    List<PromptHandler> prompts;

    public ChatFlowController(Consumer<String> output, Supplier<String> input, List<PromptHandler> prompts) {
        this.output = output;
        this.input = input;
        this.prompts = prompts;
    }

    @Override
    public Client handlePrompts() {
        Client client = new Client();
        for (PromptHandler prompt : prompts) {
            prompt.handler(output, input, client);
        }
        return client;
    }
}