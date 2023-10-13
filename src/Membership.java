public enum Membership {
    ACTIVE(1, "has an active subscription."),
    EXPIRED(0, "has an expired subscription."),
    NONE(-1, "is not a member.");

    private final int id;
    private final String status;


    Membership(int id, String status) {
        this.id = id;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public static Membership getById(int id) {
        for (Membership membership : values()) {
            if(membership.getId() == id) {
                return membership;
            }
        }
        throw new IllegalArgumentException();
    }

}
