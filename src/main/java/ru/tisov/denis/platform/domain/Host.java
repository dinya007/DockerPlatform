package ru.tisov.denis.platform.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;

@Entity
@Table(name = "hosts")
public class Host {

    @Id
    @Column(name = "host_id")
    private Long id;
    private String url;
    private String name;
    private String hostPath;
    @Column(name = "registry_flag")
    private boolean isRegistry;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getHostPath() {
        return hostPath;
    }

    public void setHostPath(String hostPath) {
        this.hostPath = hostPath;
    }

    public boolean isRegistry() {
        return isRegistry;
    }

    public void setRegistry(boolean registry) {
        isRegistry = registry;
    }
}
