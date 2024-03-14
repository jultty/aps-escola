package ifsp.jcr.aps;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class Professor implements Serializable {
  private Integer id;
  private String nome;
  private HashSet<Integer> idTurmas;


  public Professor(Integer id, HashSet<Integer> idTurmas, String nome) {
    this.id = id;
    this.nome = nome;
    this.idTurmas = idTurmas;
  }

  public Integer obterId() { return id; }
  public HashSet<Integer> obterIdTurmas() { return idTurmas; }
  public String obterNome() { return nome; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Professor professor = (Professor) o;
    return Objects.equals(id, professor.id) && Objects.equals(nome, professor.nome) && Objects.equals(idTurmas, professor.idTurmas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, idTurmas);
  }

  @Serial
  private static final long serialVersionUID = 1L;
}