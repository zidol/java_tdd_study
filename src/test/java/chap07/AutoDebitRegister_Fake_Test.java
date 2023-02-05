package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static chap07.CardValidity.INVALID;
import static chap07.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Fake_Test {

    private AutoDebitRegister register;

    private StubCardNumberValidator stubValidator;

    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.result(req);
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    @Test
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.result(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }
}
