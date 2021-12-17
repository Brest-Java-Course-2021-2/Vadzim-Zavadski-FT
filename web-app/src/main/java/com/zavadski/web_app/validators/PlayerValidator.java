package com.zavadski.web_app.validators;

import com.zavadski.model.Player;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.zavadski.model.constants.PlayerConstants.PLAYER_NAME_SIZE;

@Component
public class PlayerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "surname", "surname.empty");
        ValidationUtils.rejectIfEmpty(errors, "birthday", "birthday.empty");

        Player player = (Player) target;

        if (StringUtils.hasLength(player.getFirstName())
                && player.getFirstName().length() > PLAYER_NAME_SIZE) {
            errors.rejectValue("firstName", "firstName.maxSize");
        }
    }
}