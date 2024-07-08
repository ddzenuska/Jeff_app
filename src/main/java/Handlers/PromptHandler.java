package Handlers;

import Client.Client;
import java.util.function.Consumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface PromptHandler {
    void handler(Consumer<String> output, Supplier<String> input, Client client);
}