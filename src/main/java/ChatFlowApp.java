import Controller.ChatFlowController;
import Handlers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatFlowApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<PromptHandler> prompts = new ArrayList<>();
        prompts.add(new IntroductionHandler());
        prompts.add(new FirstNameLastNameHandler());
        prompts.add(new EmailHandler());
        prompts.add(new BirthInformationHandler());
        prompts.add(new ReasonForLoanHandler());

        ChatFlowController chatFlowController = new ChatFlowController(System.out::println, in::next, prompts);
        chatFlowController.handlePrompts();
    }
}