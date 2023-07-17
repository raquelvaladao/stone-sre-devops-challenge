package com.stone.demo.shared;

import com.stone.demo.shared.utils.FormatUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
class FormatUtilsTest {

    @Test
    void shouldConvertDateToString() {
        Date date = Date.valueOf("2022-05-13");
        String expectedString = "13/05/2022";
        String resultString = FormatUtils.dateToStr(date);
        assertEquals(expectedString, resultString);
    }

    @Test
    void shouldConvertStringToDate() {
        String dateString = "13/05/2022";
        Date expectedDate = Date.valueOf("2022-05-13");
        Date resultDate = FormatUtils.strToDate(dateString);
        assertEquals(expectedDate, resultDate);
    }

    @Test
    void testStrToDateWithInvalidDateString() {
        String dateString = "---------";

        Date resultDate = FormatUtils.strToDate(dateString);

        assertNull(resultDate);
    }
}