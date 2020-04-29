package hw_reflection;

public class Ident {
    private String tab;
    private int ident;
    Ident(String tab) {
        this.tab = tab;
        ident = 0;
    }
    Ident(String tab, int ident) {
        this.tab = tab;
        this.ident = ident;
    }

    void inc() {
        ident++;
    }

    void decr() {
        ident--;
    }

    String ident() {
        return tab.repeat(ident);
    }
}
