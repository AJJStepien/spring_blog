package pl.sda.spring.blog.springblog.model;

public enum Category {
    DEV("Programowanie"),
    DEV_OPS("Administracja"),
    TESTING("Testowanie");
    private String name;

    public String getName() {
        return name;
    }

    Category(String name) {
        this.name = name;
    }
}
