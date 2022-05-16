package entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "name", length = 55)
    String name;

    @Column(name = "age")
    Integer age;

//    @OneToOne
//    @JoinColumn(name = "employee_address_id")
//    EmployeeAddress employeeAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="department_id")
    Department department;

}
