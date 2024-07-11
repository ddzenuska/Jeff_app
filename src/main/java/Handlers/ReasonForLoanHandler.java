package Handlers;

import Client.Client;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ReasonForLoanHandler implements PromptHandler {
    private static final String LOAN_REASON_PROMPT = "🤖: And what do you need the money for?";
    private static final String LOAN_MOTIVE_PROMPT = "🤖: Home , Car , Holidays, Big Event?";
    private static final String LOAN_OPTIONS_PROMPT = "🤖: Nice, I already have some options for you";
    @Override
    public void handler(Consumer<String> output, Supplier<String> input, Client client) {
        output.accept(LOAN_REASON_PROMPT);
        output.accept(LOAN_MOTIVE_PROMPT);
        output.accept("👧: ");
        client.setLoanReason(input.get());
        output.accept(LOAN_OPTIONS_PROMPT);
    }
}
