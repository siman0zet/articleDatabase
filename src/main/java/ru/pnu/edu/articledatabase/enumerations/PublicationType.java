package ru.pnu.edu.articledatabase.enumerations;

public enum PublicationType {
    SCIENCE_MAGAZINE("Научный журнал"),
    COLLECTION_OF_ABSTRACTS("Сборник тезисов"),
    POPULAR_SCIENCE_EDITION("Научно-популярные издания"),
    COLLECTION_OF_SCIENTIFIC_PAPERS("Сборник научных трудов");

    private String label;

    PublicationType(String label){
        this.label = label;
    }

    public String toString(){
        return label;
    }
}
