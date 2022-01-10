package com.zavadski.web_app.validators;

import com.zavadski.model.Team;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.zavadski.model.constants.TeamConstants.TEAM_NAME_SIZE;

@Component
public class TeamValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "teamName", "teamName.empty");
        Team team = (Team) target;

        if (StringUtils.hasLength(team.getTeamName())
                && team.getTeamName().length() > TEAM_NAME_SIZE) {
            errors.rejectValue("teamName", "teamName.maxSize");
        }
    }
}