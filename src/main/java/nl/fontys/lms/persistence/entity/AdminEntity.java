package nl.fontys.lms.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "admin")
@Data
@SuperBuilder
@NoArgsConstructor
public class AdminEntity extends UserEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long adminId;

    @Column(name = "department")
    private String department;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @OneToOne
    @MapsId
    private UserEntity user;
}
