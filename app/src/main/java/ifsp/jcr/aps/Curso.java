package ifsp.jcr.aps;

import java.io.Serial;

class Curso {
  private Integer id;
  private String nome;
  private String descricao;
  private Integer[] idDisciplinas;

  public Curso(Integer id, String nome, String descricao, Integer[] idDisciplinas) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.idDisciplinas = idDisciplinas;
  }

  public Integer obterId() { return id; }
  public String obterNome() { return nome; }
  public String obterDescricao() { return descricao; }
  public Integer[] obterIdDisciplinas() { return idDisciplinas; }

  @Serial
  private static final long serialVersionUID = 1L;
}