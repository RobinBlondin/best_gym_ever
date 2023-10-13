public enum Subscription {
    ACTIVE(1, "has an active subscription."),
    EXPIRED(0, "has an expired subscription."),
    NONE(-1, "is not a member.");

    private final int id;
    private final String status;


    Subscription(int id, String status) {
        this.id = id;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public static Subscription getById(int id) {
        for (Subscription membership : values()) {
            if(membership.getId() == id) {
                return membership;
            }
        }
        throw new IllegalArgumentException();
    }

}
