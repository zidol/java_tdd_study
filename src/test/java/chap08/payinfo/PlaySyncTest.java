package chap08.payinfo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaySyncTest {

    //대역 생성
    private MemoryPayInfoDao memoryDao = new MemoryPayInfoDao();

    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync();
        paySync.setPayInfoDao(memoryDao);//대역으로 교체
        paySync.setFilePath("src/test/resources/c0111.csv");
        paySync.sync();

        //대역을 이용한 결과 검증
        List<PayInfo> savedInfos = memoryDao.getAll();
        assertEquals(2, savedInfos.size());
    }
}