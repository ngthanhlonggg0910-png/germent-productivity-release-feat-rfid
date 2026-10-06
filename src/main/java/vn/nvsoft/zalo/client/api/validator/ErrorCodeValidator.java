package vn.nvsoft.zalo.client.api.validator;

import vn.nvsoft.zalo.client.model.IErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorCodeValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorCodeValidator.class.getName());

    public boolean validateExpiredToken(IErrorCode result) {
        if (result.getError() != 0) {
            if (result.getMessage().contains("Token is not valid")) {
                return true;
            }
            LOGGER.error("Error during contact with CSA: " + result.getMessage());
        }
        return false;
    }
}
