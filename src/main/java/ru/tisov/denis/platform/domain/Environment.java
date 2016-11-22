package ru.tisov.denis.platform.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "environments")
public class Environment {

    @Id
    @Column(name = "environment_id")
    private Long id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rel_environments_hosts",
            joinColumns = {@JoinColumn(name = "environment_id")},
            inverseJoinColumns = {@JoinColumn(name = "host_id")})
    private List<Host> hosts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rel_environments_networks",
            joinColumns = {@JoinColumn(name = "environment_id")},
            inverseJoinColumns = {@JoinColumn(name = "network_id")})
    private List<Network> networks;

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

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Environment that = (Environment) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", hosts=" + hosts +
                ", networks=" + networks +
                '}';
    }
}
