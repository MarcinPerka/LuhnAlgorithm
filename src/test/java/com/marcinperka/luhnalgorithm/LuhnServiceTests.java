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
    public void testGetCheckDigit() {
        assertThat(luhnService.getCheckDigit("92480")).isEqualTo(3);

        assertThat(luhnService.getCheckDigit("292723200000000021")).isEqualTo(8);

        assertThat(luhnService.getCheckDigit("855")).isEqualTo(7);

        assertThat(luhnService.getCheckDigit("00000000")).isEqualTo(0);

        assertThat(luhnService.getCheckDigit("000000001")).isEqualTo(8);
    }

    @Test
    public void testValidateTheNumber() {
        assertThat(luhnService.validateTheNumber("92480")).isEqualTo(false);

        assertThat(luhnService.validateTheNumber("924803")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("292723200000000021")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("855")).isEqualTo(false);

        assertThat(luhnService.validateTheNumber("000000000")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("0000000018")).isEqualTo(true);

        assertThat(luhnService.validateTheNumber("11")).isEqualTo(false);
    }
}
