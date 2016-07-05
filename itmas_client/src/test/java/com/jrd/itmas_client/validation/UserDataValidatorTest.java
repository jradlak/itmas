package com.jrd.itmas_client.validation;

import com.jrd.itmas_client.infrastructure.validation.UserDataValidator;
import com.jrd.itmas_client.infrastructure.validation.ValidationResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jakub on 26.06.16.
 */
public class UserDataValidatorTest {

    private UserDataValidator validator;

    private String[][] lines = {
            {"First Name", "Jakub"},
            {"Last Name", "Radlak"}
    };

    @Before
    public void setup() {
        this.validator = new UserDataValidator();
    }

    @Test
    public void shouldValidateCorrectLines() {
        ValidationResult result = validator.validateUserDataLine(lines[0]);
        Assert.assertTrue(result.isCorrent());
    }
}
