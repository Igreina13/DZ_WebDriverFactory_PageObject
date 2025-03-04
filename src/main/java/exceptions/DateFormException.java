package exceptions;

public class DateFormException extends RuntimeException {
    public DateFormException(String textBirhday) {
        super(String.format("Формат даты: %s неверный. Должен быть формат ДД.ММ.ГГГГ", textBirhday));
    }
}
