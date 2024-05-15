package com.example.demo.validator;


import com.example.demo.model.Premio;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PremioValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Premio.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Premio premio = (Premio) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome",
                "field.required", "O campo 'nome' é obrigatório.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao",
                "field.required", "O campo 'descricao' é obrigatório.");
        ValidationUtils.rejectIfEmpty(errors, "pontos",
                "field.required", "O campo 'pontos' é obrigatório.");
        if (premio.getPontos_pr() < 0) {
            errors.rejectValue("pontos",
                    "field.invalid", "O campo 'pontos' deve ser um valor positivo.");
        }
    }
}
