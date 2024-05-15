package com.example.demo.validator;
import com.example.demo.model.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class ClienteValidator implements Validator {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String EMAIL_PATTERN = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";

    @Override
    public boolean supports(Class<?> clazz) {
        return Cliente.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cliente cliente = (Cliente) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome",
                "field.required", "O campo 'nome' é obrigatório.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "field.required", "O campo 'email' é obrigatório.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "field.required", "O campo 'password' é obrigatório.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datanasc",
                "field.required", "O campo 'datanasc' é obrigatório.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genero",
                "field.required", "O campo 'genero' é obrigatório.");

        // Validar formato do e-mail
        if (!cliente.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email", "email.invalid", "Formato de e-mail inválido.");
        }

        // Validar formato da data de nascimento
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false); // Não aceitar datas inválidas como 31/02/2022, por exemplo
        try {
            dateFormat.parse(String.valueOf(cliente.getDataNascimento()));
        } catch (ParseException e) {
            errors.rejectValue("datanasc", "date.invalid", "Data de nascimento inválida. Use o formato dd/mm/yyyy.");
        }
    }
}
