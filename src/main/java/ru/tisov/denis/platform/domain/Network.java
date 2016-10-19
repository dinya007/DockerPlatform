package ru.tisov.denis.platform.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "networks")
public class Network {

    @Id
    @Column(name = "network_id")
    private String id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


}
