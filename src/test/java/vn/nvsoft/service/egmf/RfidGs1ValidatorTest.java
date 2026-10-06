package vn.nvsoft.service.egmf;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RfidGs1ValidatorTest {

    @Test
    void isValidGs1Loose_acceptsShortSerial() {
        assertThat(RfidGs1Validator.isValidGs1Loose("(01)03608393569882(21)38939")).isTrue();
    }

    @Test
    void isValidGs1Strict_requiresTwelveDigitSerial() {
        assertThat(RfidGs1Validator.isValidGs1Strict("(01)03608393569882(21)38939")).isFalse();
        assertThat(RfidGs1Validator.isValidGs1Strict("(01)03608393569882(21)000000038939")).isTrue();
    }

    @Test
    void isDecathlonCustomer_matchesName() {
        assertThat(RfidGs1Validator.isDecathlonCustomer("DECATHLON VIETNAM")).isTrue();
        assertThat(RfidGs1Validator.isDecathlonCustomer("Nike")).isFalse();
        assertThat(RfidGs1Validator.isDecathlonCustomer((String) null)).isFalse();
    }

    @Test
    void extractGtin_readsFourteenDigitsBetween01And21() {
        assertThat(RfidGs1Validator.extractGtin("(01)03608393569882(21)000000038939"))
            .isEqualTo("03608393569882");
        assertThat(RfidGs1Validator.extractGtin("invalid")).isNull();
    }
}
