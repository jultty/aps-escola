package ifsp.jcr.aps;

import java.util.Objects;

class Disciplina {
  private Integer id;
  private String nome;
  private String descricao;

  public Disciplina(Integer id, String nome, String descricao) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
  }

  public Integer obterId() {
    return id;
  }

  public String obterNome() {
    return nome;
  }

  public String obterDescricao() {
    return descricao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Disciplina that = (Disciplina) o;
    return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, descricao);
  }
}
