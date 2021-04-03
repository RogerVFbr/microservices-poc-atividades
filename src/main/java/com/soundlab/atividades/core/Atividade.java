package com.soundlab.atividades.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "tb_atividades")
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class Atividade extends AbstractAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", insertable = false, updatable = false)
    private List<UserAssociation> userAssociations;

    public Atividade withId(Long id) {
        this.id = id;
        return this;
    }

    public Atividade withName(String name) {
        this.name = name;
        return this;
    }
}
