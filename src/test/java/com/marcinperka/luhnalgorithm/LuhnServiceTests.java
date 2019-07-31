package com.marcinperka.luhnalgorithm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuhnServiceTests {

    @InjectMocks
    private LuhnService luhnService;

    @Test
    public void testProcessLuhnAlgorithm(){

        assertThat(luhnService.processLuhnAlgorithm("92480",true)).isEqualTo(7);

        assertThat(luhnService.processLuhnAlgorithm("292723200000000021",true)).isEqualTo(2);

        assertThat(luhnService.processLuhnAlgorithm("855",true)).isEqualTo(3);

        assertThat(luhnService.processLuhnAlgorithm("00000000",true)).isEqualTo(0);

        assertThat(luhnService.processLuhnAlgorithm("000000001",true)).isEqualTo(2);
    }

    @Test
    public void testGetCheckDigit(){
        assertThat(luhnService.getCheckDigit("92480")).isEqualTo(3);

        assertThat(luhnService.getCheckDigit("292723200000000021")).isEqualTo(8);

        assertThat(luhnService.getCheckDigit("855")).isEqualTo(7);

        assertThat(luhnService.getCheckDigit("00000000")).isEqualTo(0);

        assertThat(luhnService.getCheckDigit("000000001")).isEqualTo(8);
    }

    @Test
    public void testValidateTheNumber(){
        assertThat(luhnService.validateTheNumber("924803")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("2927232000000000218")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("8557")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("000000000")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("0000000018")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("11")).isEqualTo(false);
    }
}
