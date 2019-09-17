package br.com.wirecard.util;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * Email
 */
public class Email implements Supplier<String> {
    private static final String EMAIL_PATTERN = 
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);
    private final String value;
    private Email(String value) {
        this.value = value;
    }
    @Override
    public String get() {        
        return value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
    public static Email of(String value){
        Objects.requireNonNull(value, "email is required");
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("The email is not valid");
        }
        return new Email(value);
    }
    
    
}