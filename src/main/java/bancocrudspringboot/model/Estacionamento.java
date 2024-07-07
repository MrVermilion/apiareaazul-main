package bancocrudspringboot.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Estacionamento")
public class Estacionamento {
    
    private long id;

    private long veiculo_id;    
    private String endereco;
    private String regra;
    private long tempo; // Tempo em minutos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

}