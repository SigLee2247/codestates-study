package com.codestates.homework;

import com.codestates.helper.RandomPasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RandomPasswordGeneratorTest {
    @DisplayName("실습 3: 랜덤 패스워드 생성 테스트")
    @Test
    public void generateTest() {
        // TODO 여기에 테스트 케이스를 작성해주세요.
        //given

        int numOfUpper = 0;
        int numOfLower = 0;
        int numOfNumber = 0;
        int numOfSpecial = 0;

        //when
        String actual = RandomPasswordGenerator.generate(2, 5, 2, 1);

        for (char c : actual.toCharArray()) {
            if( c<=90 && c>=65) numOfUpper++;
            else if(c<=122 && c>=97) numOfLower++;
            else if(c>=33 && c<=37) numOfSpecial++;
            else numOfNumber++;
        }




        //then
        Assertions.assertEquals(numOfUpper,2);
        Assertions.assertEquals(numOfLower,5);
        Assertions.assertEquals(numOfNumber,2);
        Assertions.assertEquals(numOfSpecial,1);




    }
}
