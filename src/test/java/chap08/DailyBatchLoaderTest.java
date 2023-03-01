package chap08;

import chap08.subs.Times;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class DailyBatchLoaderTest {

    private Times mocTimes = Mockito.mock(Times.class);
    private final DailyBatchLoader loader = new DailyBatchLoader();


    @BeforeEach
    void setUp() {
        loader.setBasePath("src/test/resources");
        loader.setTimes(mocTimes);
    }

    @Test
    void loadCount() {
        given(mocTimes.today()).willReturn(LocalDate.of(2019, 1, 1));
        int ret = loader.load();
        assertEquals(3, ret);
    }

}