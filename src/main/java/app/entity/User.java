package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "current_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Getter
//    @Setter
    @NotBlank
    @Column(name="surname")
    private String surname;


//    @Setter
//    @Getter
    @NotBlank
    @Column(name="name")
    private String name;

//    @Setter
//    @Getter
//    @JsonIgnore // позволяет игнорировать
    @Column(name="patronymic")
    private String patronymic;

//    @Setter
//    @Getter
    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past
    @Column(name="birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

//    @Setter
//    @Getter
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT) // it's  not necessary
    @Column(name="sex")
    @Enumerated(STRING)
    private Sex sex;

    @Column(name="organizationid")
    private Integer organization;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "organization", foreignKey = @ForeignKey(name="organization_id"))
//    private Organization organization_id;

    private String organizationname;



//    public User(){
//
//    }

//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setPatronymic(String patronymic) {
//        this.patronymic = patronymic;
//    }
//
//    public String getPatronymic() {
//        return patronymic;
//    }
//
//    public void setSex(Sex sex) {
//        this.sex = sex;
//    }
//
//    public Sex getSex() {
//        return this.sex;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
//    public Date getBirthDate() {
//        return birthDate;
//    }
}


