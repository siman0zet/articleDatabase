package ru.pnu.edu.articledatabase.enumerations;

public enum ArticleLevel {
    RINC("РИНЦ"),
    WOS("WOS"),
    SCOPUS("Scopus"),
    WAK("ВАК"),
    NO_STATUS("нет");

    private String label;

    ArticleLevel(String label){
        this.label = label;
    }

    public String toString(){
        return label;
    }
}
