package gumga.framework.validation;

import org.springframework.validation.Errors;

import com.google.common.base.Optional;

/**
 * Interface que define a base de uma validação.
 * 
 * Retorna sempre um {@link Optional} que irá conter a definição do erro caso
 * ele ocorrer, ou será "absent" caso não ocorrer erro.
 * 
 * @param <T>
 */
public interface GumgaFieldValidator<T> {

	public Optional<GumgaValidationError> validate(T value, Errors errors);

}
