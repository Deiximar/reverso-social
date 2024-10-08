package com.reversosocial.models.dto;

public class EmployDto {
    private String position;
    private String cvUrl;
    private String description;
    private int sectorId;

    public EmployDto(String position, String cvUrl, String description, int sectorId) {
        this.position = position;
        this.cvUrl = cvUrl;
        this.description = description;
        this.sectorId = sectorId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }
}
