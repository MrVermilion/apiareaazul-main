package bancocrudspringboot.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Perfil", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Perfil {
    
    private long id;

    private String nome;
    private String cpf;
    private String email;    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

}