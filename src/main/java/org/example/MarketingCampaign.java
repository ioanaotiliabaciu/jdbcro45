package org.example;

import javax.xml.crypto.Data;
import java.sql.Date;

public class MarketingCampaign {

    private Integer id; //Integer poate stoca val null, insa int nu
    private String name;
    private Date startDate;
    private Double budget;

    public MarketingCampaign(Integer id, String name, Date startDate, Double budget) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.budget = budget;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "MarketingCampaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", budget=" + budget +
                '}';
    }
}