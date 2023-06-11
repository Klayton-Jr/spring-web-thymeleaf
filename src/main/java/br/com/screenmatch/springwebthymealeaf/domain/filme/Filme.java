package br.com.screenmatch.springwebthymealeaf.domain.filme;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer duracaoEmMinutos;
    private Integer anoLancamento;
    private String genero;

    public Filme(DadosCadastroFilme dados) {
        this.nome = dados.nome();
        this.duracaoEmMinutos = dados.duracao();
        this.anoLancamento = dados.ano();
        this.genero = dados.genero();
    }

    public void atualizaDados(DadosAlteracaoFilme dados) {
        this.nome = dados.nome();;
        this.genero = dados.genero();
        this.duracaoEmMinutos = dados.duracao();
        this.anoLancamento = dados.ano();
    }
}
