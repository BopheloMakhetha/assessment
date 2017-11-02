package domain;

public class ProgrammingStack {
    private final String tech;
    private final String reason;
    private final String lifeCycle;

    public ProgrammingStack(String tech, String reason, String lifeCycle) {
        this.tech = tech;
        this.reason = reason;
        this.lifeCycle = lifeCycle;
    }

    public String getTech() {
        return tech;
    }

    public String getReason() {
        return reason;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

}
