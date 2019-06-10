package softuni.springbootdemotest.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getClientInfo",
                procedureName = "udp_clientinfo",
                resultClasses = Client.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "full_name", type = String.class)
                }
        )
})
public class Client extends BaseEntity {
    private String fullName;
    private Integer age;
    private List<Employee> employees;
    private BankAccount bankAccount;

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToMany(targetEntity = Employee.class, mappedBy = "clients")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @OneToOne(targetEntity = BankAccount.class, mappedBy = "client")
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
