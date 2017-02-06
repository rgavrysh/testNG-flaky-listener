package db;

public class Test {
    private Boolean isFlaky;
    private Integer buildRun;
    private String name;
    private Integer id;

    public Test(Boolean isFlaky, Integer buildRun, String name, Integer id) {
        this.isFlaky = isFlaky;
        this.buildRun = buildRun;
        this.name = name;
        this.id = id;
    }

    public Boolean getFlaky() {
        return isFlaky;
    }

    public Integer getBuildRun() {
        return buildRun;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
