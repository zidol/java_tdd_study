package mokito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class VoidMehtodStubTest {

    @DisplayName("BDDMockito.given()을 이용한 스텁 설정")
    @Test
    void voidMethodStubTest() {
        List<String> mockList = mock(List.class);
        BDDMockito.willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        assertThrows(
                UnsupportedOperationException.class,
                mockList::clear
        );
    }
}
