package api.retrofit;


public class RetrofitException extends RuntimeException {

    public RetrofitException(final String message) {
        super(message);
    }

    public RetrofitException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
