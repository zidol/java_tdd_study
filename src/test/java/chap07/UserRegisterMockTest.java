package chap07;

import chap07.exception.WeakPasswordException;
import chap07.userRegist.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterMockTest {

    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);

    private MemoryUserRepository fakeRepository = new MemoryUserRepository();

    private UserRepository mockRepository = mock(UserRepository.class);

    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,
                fakeRepository,
                mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    //모의 객체가 기대한 대로 불렸는지 검증하는 코드
    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        BDDMockito.then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(BDDMockito.anyString());
    }

    //모의 객체를 과하게 사용하지 않기
    //모의객체를 과하게 사요하면 오히려 테스트 코드가 복잡해짐

//     @Test
//     void noDup_RegisterSuccess() {
//         userRegister.register("id", "pw", "email");
//
//         ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
//         then(mockRepository).should().save(captor.capture());
//
//         User savedUser = captor.getValue();
//         assertEquals("id", savedUser.getId());
//         assertEquals("email", savedUser.getEmail());
//     }
    @Test
    void 같은_Id가_없으면_가입() {
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    //모의 객체의 메서드를 호출할 때 전달한 인자를 구하는 코드
    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }

}
