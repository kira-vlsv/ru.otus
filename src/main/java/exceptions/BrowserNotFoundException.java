package exceptions;

public class BrowserNotFoundException extends RuntimeException {

    public BrowserNotFoundException(String browserName) {
        super(String.format("Browser with name %s is not supported", browserName));
    }
}
