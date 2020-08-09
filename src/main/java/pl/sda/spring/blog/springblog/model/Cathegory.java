package pl.sda.spring.blog.springblog.model;

public enum Cathegory {
    DEV("Programowanie"),
    DEV_OPS("Administracja"),
    TESTING("Testowanie");

    private String name;

    Cathegory(String name) {
        this.name = name;
    }
}
