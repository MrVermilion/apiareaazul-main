package bancocrudspringboot.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "UsuarioSistema", uniqueConstraints={@UniqueConstraint(columnNames={"telefone"})})
public class Usuario {

    private long id;
    private String telefone;
    private String senha;
    private String cidade;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
}