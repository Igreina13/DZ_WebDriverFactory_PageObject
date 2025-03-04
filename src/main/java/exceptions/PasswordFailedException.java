package exceptions;

public class PasswordFailedException extends RuntimeException {
    public PasswordFailedException(String password, String confirmPassword) {
        super(String.format("Пароль: %s не совпадает с паролем %s", password, confirmPassword));
    }
}
