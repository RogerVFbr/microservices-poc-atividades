package com.soundlab.atividades.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "tb_user_association")
@ToString
public class UserAssociation extends AbstractAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "grade")
    private Integer grade;

    public UserAssociation withActivityId(Long activityId) {
        this.activityId = activityId;
        return this;
    }

    public UserAssociation withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserAssociation withGrade(Integer grade) {
        this.grade = grade;
        return this;
    }
}
