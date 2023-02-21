package mokito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class GameTest {

    @DisplayName("모의 객체의 메서드가 불렸는지 여부를 검증하는 코드")
    @Test
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);
        then(genMock).should().generate(GameLevel.EASY);
    }
}
