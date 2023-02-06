package chap07;

import chap07.cardNumber.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.cardNumber.CardValidity.*;
import static org.junit.jupiter.api.Assertions.*;

class AutoDebitRegisterTest {

    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        JpaAutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        //업체에서 받은 테스트용 유효한 키드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.result(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        //업체에서 받은 도난 테스트용 키드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.result(req);
        assertEquals(THEFT, result.getValidity());
    }

}