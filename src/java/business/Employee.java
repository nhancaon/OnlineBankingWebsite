package business;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@AttributeOverride(name = "name", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "dateofBirth", column = @Column(columnDefinition = "varchar(20)", nullable = false))
@AttributeOverride(name = "password", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "email", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "phoneNumber", column = @Column(columnDefinition = "varchar(20)", nullable = false))
@AttributeOverride(name = "address", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "citizenId", column = @Column(columnDefinition = "varchar(20)", nullable = false))
public class Employee extends User implements Serializable {

    @Id
    @Column(columnDefinition = "varchar(20)",nullable = false)
    private String employeeId;
    @Column(columnDefinition = "varchar(20)",nullable = false)
    private String role;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRoles() {
        return role;
    }

    public void setRoles(String role) {
        this.role = role;
    }

}
