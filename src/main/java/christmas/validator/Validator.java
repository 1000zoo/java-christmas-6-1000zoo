package christmas.validator;

public interface Validator<T> {

    void validate(T target);
}
