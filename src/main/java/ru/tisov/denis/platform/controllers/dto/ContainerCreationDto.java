package ru.tisov.denis.platform.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContainerCreationDto {

    @JsonProperty
    private String imageName;
    @JsonProperty
    private String imageTag;
    @JsonProperty
    private String hostName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }
}
