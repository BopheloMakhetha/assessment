package domain;

public class Infrastructure extends Table{
    private final String infrastructure;
    private final String use;
    private final String lifeCycle;

    public Infrastructure(String infrastructure, String use, String lifeCycle) {
        this.infrastructure = infrastructure;
        this.use = use;
        this.lifeCycle = lifeCycle;
    }

    public String getInfrastructure() {
        return infrastructure;
    }

    public String getUse() {
        return use;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }
}
