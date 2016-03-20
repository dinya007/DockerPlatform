package ru.tisov.denis.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Host {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String ip;
    private String name;

    public Host() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }
}
