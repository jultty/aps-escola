package ifsp.jcr.aps;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(3)
class TestesAluno {

  //  2.4. Avaliar aprovação, sendo
  //     - 2.4.1. alunos aprovados os que tiverem
  //       - 2.4.1.1. 75% ou mais de presença em relação ao total de aulas cadastradas
  //       - 2.4.1.2. e média de notas igual ou maior que 6,0

  @Test
  @Order(6)
  void  alunoComMediaIgualOuMaiorQue6EstaAprovado() {}

  @Test @Order(6)
  void alunoComMediaInferiorA6EstaReprovado() {}

}