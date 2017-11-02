package domain;

public enum TableType {
    BUILD_STACK("Build Stack"),
    INFRASTRUCTURE("Infrastructure"),
    MONITORING("Monitoring"),
    PROGRAMMING_STACK("Programming Stack");

    private final String name;

    TableType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }

}
