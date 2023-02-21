package mokito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

/**
 * Mockito는 일치하는 스텁 설정이 없을 경우 리턴 타입의 기본 값을 리턴 한다.
 * 예를 들어 리턴 타입이 int면 0을 리턴하고 boolean이면 false를 리턴한다. 기본 데이터 타입이 아닌
 * String이나 List와 같은 참조 타입이면 null을 리턴한다.
 */
public class AnyMatcherTest {

    @DisplayName("ArgumentMatchers.any()메서드로 인자 값 매칭 처리")
    @Test
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("456", num2);
    }

    @DisplayName("임의 값 일치와 정확한 값 일치가 필요한 경우 eq() 메서드를 사용")
    @Test
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);
        given(mockList.set(anyInt(), eq("123"))).willReturn("456");
        String old = mockList.set(5, "123");
        assertEquals("456", old);
    }
}
