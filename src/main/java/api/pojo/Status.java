package api.pojo;

public enum Status {

    OK,
    SUCCESS,
    ERROR;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
