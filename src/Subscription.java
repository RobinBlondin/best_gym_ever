public enum Subscription {
    ACTIVE("an active"),
    EXPIRED("an expired"),
    NONE("never had an");

    private final String status;


    Subscription(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
