package chap07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.*;
import static org.junit.jupiter.api.Assertions.*;

public class AutoDebitRegister_Stub_Test {

    private AutoDebitRegister register;

    private StubCardNumberValidator stubValidator;

    private StubAutoDebitRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = register.result(req);
        assertEquals(INVALID, result.getValidity());

    }

    @Test
    void theftCard() {
        stubValidator.setTheftNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = register.result(req);
        assertEquals(THEFT, result.getValidity());
    }
}
