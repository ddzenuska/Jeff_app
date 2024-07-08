import Controller.ChatFlowController;
import Handlers.*;
import Client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

public class ChatFlowTestCases {
    private ChatFlowController chatFlowController;
    private ByteArrayOutputStream outContent;
    private Consumer<String> output;
    private Supplier<String> input;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    private void initializeController(String inputString) {
        input = new Scanner(new ByteArrayInputStream(inputString.getBytes()))::nextLine;
        output = System.out::println;

        List<PromptHandler> prompts = new ArrayList<>();
        prompts.add(new IntroductionHandler());
        prompts.add(new FirstNameLastNameHandler());
        prompts.add(new EmailHandler());
        prompts.add(new BirthInformationHandler());
        prompts.add(new ReasonForLoanHandler());

        chatFlowController = new ChatFlowController(output, input, prompts);
    }

    @Test
    void testIntroduction() {
        initializeController("John\nDoe\ntest@example.com\n2000-01-01\nHome\n");

        Client client = chatFlowController.handlePrompts();
        assertEquals("John", client.getFirstName());
        assertEquals("Doe", client.getLastName());
    }


    @Test
    void testFirstAndLastNameHandler_InvalidFirstName() {
        initializeController("john\nDoe\ntest@example.com\n2000-01-01\nHome\n");
        Client client = chatFlowController.handlePrompts();

        assertNotEquals("john", client.getFirstName());
        assertEquals("Doe", client.getLastName());
    }

    @Test
    void testFirstAndLastNameHandler_InvalidLastName() {
        initializeController("John\ndoe\ntest@example.com\n2000-01-01\nHome\n");
        Client client = chatFlowController.handlePrompts();

        assertEquals("John", client.getFirstName());
        assertNotEquals("doe", client.getLastName());
    }

    @Test
    void testClientEmail_ValidEmail() {
        initializeController("John\nDoe\ntest@example.com\n2000-01-01\nHome\n");
        Client client = chatFlowController.handlePrompts();

        assertEquals("test@example.com", client.getEmail());
    }

    @Test
    void testIsEmailAddressValid_ValidEmail() {
        assertTrue(EmailHandler.isEmailAddressValid("test@example.com"));
    }

    @Test
    void testIsEmailAddressValid_InvalidEmail() {
        assertFalse(EmailHandler.isEmailAddressValid("invalid-email"));
    }

    @Test
    void testClientEmail_InvalidEmailThenValidEmail() {
        initializeController("John\nDoe\ninvalid-email\nanother-invalid\nvalid@example.com\n2000-01-01\nHome\n");
        Client client = chatFlowController.handlePrompts();

        assertEquals("valid@example.com", client.getEmail());
    }

    @Test
    void testClientBirthInformation_ValidAge() {
        initializeController("John\nDoe\ntest@example.com\n2000-10-01\nHome\n");
        Client client = chatFlowController.handlePrompts();

        assertTrue(client.getIsOverEighteen());
        assertEquals(LocalDate.parse("2000-10-01"), client.getAge());
    }

    @Test
    void testClientBirthInformation_Underage() {
        initializeController("John\nDoe\ntest@example.com\n2018-12-18\nHome\n");
        Client client = chatFlowController.handlePrompts();

        assertFalse(client.getIsOverEighteen());
    }

    @Test
    void testClientReasonForLoan() {
        initializeController("John\nDoe\ntest@example.com\n2000-01-01\nCar\n");
        Client client = chatFlowController.handlePrompts();

        assertEquals("Car", client.getLoanReason());
    }
}