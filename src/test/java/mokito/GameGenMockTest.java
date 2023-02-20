package mokito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {

    @DisplayName("BDDMockito.given()을 이용한 스텁 설정")
    @Test
    void mockTest() {
        //1. 모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);

        //2. 스텁 설정
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        //3. 스텁 설정에 매칭되는 메서드 실행
        String num = genMock.generate(GameLevel.EASY);
        Assertions.assertEquals("123", num);
    }

    @DisplayName("특정 타입의 익셉션을 발생하도록 스텁 생성")
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThrows(
                IllegalArgumentException.class,
                () -> genMock.generate(null)
        );
    }
}
