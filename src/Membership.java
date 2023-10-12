public enum Membership {
    ACTIVE("has an active subscription."),
    EXPIRED("has an expired subscription."),
    NONE("is not a member.");

    private final String status;

    Membership(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
