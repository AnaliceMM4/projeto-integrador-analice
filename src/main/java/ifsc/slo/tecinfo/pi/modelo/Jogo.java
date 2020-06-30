
package ifsc.slo.tecinfo.pi.modelo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author analice
 */
@Entity
public class Jogo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codJogo;
    private String nome;
    private String descricao;
    private double nota;
    @OneToMany
    private List<Avaliacao> avaliacoes;

    public Jogo() {
    }
    
    public Jogo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
   
    public Jogo(int codJogo, String nome, String descricao, double nota, List<Avaliacao> avaliacoes) {
        this.codJogo = codJogo;
        this.nome = nome;
        this.descricao = descricao;
        this.nota = nota;
        this.avaliacoes = avaliacoes;
    }

    public int getCodJogo() {
        return codJogo;
    }

    public void setCodJogo(int codJogo) {
        this.codJogo = codJogo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    
}
