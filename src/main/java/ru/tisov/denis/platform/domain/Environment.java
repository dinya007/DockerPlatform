package ru.tisov.denis.platform.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "environments")
public class Environment {

    @Id
    @Column(name = "environment_id")
    private Long id;
    private String name;
    //TODO change to LocalDateTime
    private Date createdDate;
    private Date modifiedDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rel_environments_hosts",
            joinColumns = {@JoinColumn(name = "environment_id")},
            inverseJoinColumns = {@JoinColumn(name = "host_id")})
    private List<Host> hosts;

    public Environment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }
}
