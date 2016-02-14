package com.kickstarter;

public class Project {

    private String name;
    private int amount;
    private int days;
    private Category category;
    private String description;
    private int exist;
    private String history;
    private String demoVideo;
    private String questionAnswers;


    public Project(String name, int amount, int days, String demoVideo, String description) {
        this.name = name;
        this.amount = amount;
        this.days = days;
        this.description = description;
        this.exist = 0;
        this.history = null;
        this.demoVideo = demoVideo;
        this.questionAnswers = null;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public int getExist() {
        return exist;
    }

    public int getDays() {
        return days;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDemoVideo() {
        return demoVideo;
    }

    public void setDemoVideo(String demoVideo) {
        this.demoVideo = demoVideo;
    }

    public String getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(String questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
}