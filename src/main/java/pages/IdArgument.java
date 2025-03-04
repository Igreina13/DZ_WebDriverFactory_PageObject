package pages;

public enum IdArgument {
    USERNAME("username"),
    EMAIL("email"),
    PASSWORD("password"),
    CONFIRM_PASSWORD("confirm_password"),
    BIRTHDATE("birthdate"),
    LANGUAGE_LEVEL("language_level"),
    OUTPUT("output");


    private String id;

    IdArgument(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
