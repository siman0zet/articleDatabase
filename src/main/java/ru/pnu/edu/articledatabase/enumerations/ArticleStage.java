package ru.pnu.edu.articledatabase.enumerations;

public enum ArticleStage {
    PREPARE("Подготовка материала"),
    WRITING("Написание"),
    DEPARTURE("Отправлена"),
    REVIEW("Рецензирование"),
    PUBLISH("Опубликована");

    private String label;

    ArticleStage(String label){
        this.label = label;
    }

    public String toString(){
        return label;
    }
}
