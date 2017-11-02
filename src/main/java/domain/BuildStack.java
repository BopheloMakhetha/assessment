package domain;

public class BuildStack {

    private final String tech;
    private final String use;
    private final String reason;
    private final String lifecycle;

    public BuildStack(String tech, String use, String reason, String lifecycle) {
        this.tech = tech;
        this.use = use;
        this.reason = reason;
        this.lifecycle = lifecycle;
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

    public String getLifecycle() {
        return lifecycle;
    }
}
