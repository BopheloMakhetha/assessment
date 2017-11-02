package domain;

public class Monitoring extends Table {
    private final String tech;
    private final String use;
    private final String reason;

    public Monitoring(String tech, String use, String reason) {
        this.tech = tech;
        this.use = use;
        this.reason = reason;
    }

    public String getTech() {
        return tech;
    }

    public String getUse() {
        return use;
    }

    public String getReason() {
        return reason;
    }
}
