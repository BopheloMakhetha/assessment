package domain;

public class Infrastructure extends Table{
    private final String infrastructure;
    private final String use;

    public Infrastructure(String infrastructure, String use) {
        this.infrastructure = infrastructure;
        this.use = use;
    }

    public String getInfrastructure() {
        return infrastructure;
    }

    public String getUse() {
        return use;
    }
}
