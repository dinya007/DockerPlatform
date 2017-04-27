package ru.tisov.denis.platform.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "hosts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Host {

    @Id
    @Column(name = "host_id")
    private Long id;
    private String url;
    private String name;
    private String hostPath;
    @Column(name = "registry_flag")
    private boolean isRegistry;
    @Column(name = "swarm_master_flag")
    private boolean isSwarmMaster;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Host host = (Host) o;

        return id != null ? id.equals(host.id) : host.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
