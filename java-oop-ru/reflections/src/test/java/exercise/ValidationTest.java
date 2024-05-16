package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
    @Test
    void testAdvancedValidate() {
        var address1 = new Address(
            "Russia",
            "Ufa",
            "Lenina",
            "54",
            null
        );
        var result1 = Validator.advancedValidate(address1);
        var expected1 = Map.<String, List<String>>of();
        assertThat(result1).isEqualTo(expected1);

        var address2 = new Address(
            null,
            "London",
            "1-st street",
            "5",
            "1"
        );
        var result2 = Validator.advancedValidate(address2);
        var expected2 = Map.of(
            "country", List.of("can not be null"),
            "houseNumber", List.of("length less than 42")
        );
        assertThat(result2).isEqualTo(expected2);

        var address3 = new Address(
            "USA",
            null,
            null,
            null,
            "1"
        );
        var result3 = Validator.advancedValidate(address3);
        var expected3 = Map.of(
            "city", List.of("can not be null"),
            "street", List.of("can not be null"),
            "houseNumber", List.of("can not be null")
        );
        assertThat(result3).isEqualTo(expected3);

        var address4 = new Address(
            "Russia",
            "Ufa",
            "Lenina",
            "abc",
            null
        );
        var result4 = Validator.advancedValidate(address4);
        var expected4 = Map.of(
            "houseNumber", List.of("invalid number format")
        );
        assertThat(result4).isEqualTo(expected4);

    }
    // END
}
