#import "@local/skolar:0.1.0": *
#import "@preview/tablex:0.0.8": tablex, cellx, colspanx, rowspanx

#let meta = (
  title: "Exercício de Projeto: Escola",
  author: "Juno Takano",
  course: "Arquitetura e Programação de Software",
  course_id: "APSI5",
  date: datetime.today().display("[day]/[month]/[year]"),
)

#let fig(path, caption) = {
    pad(y: 0em, [
      #figure(
        image(path, width: 100%),
        caption: caption
      )
    ])
  }

#let fake_heading(body) = {
  pad(top: 8pt)[#text(size: 12pt, weight: "extrabold")[#body]]
}

#gen_doc(properties: meta)[

  #fig("img/escola.drawio.png", "Diagrama geral da arquitetura final.")

  Como todas as estruturas já utilizavam IDs desde a proposta original, optei por desacoplar grande parte delas e centralizar as dependências no gerenciador de dados, que já concentrava ligações com quase todas elas.

  Desta forma, foi possível colocar o comportamento das classes no gerenciador de dados, deixando com que as entidades individuais se concentrem em definir as suas estruturas de dados.

  Foi adicionada uma classe `Mensagem`, que é usada para transferir dados entre as classes, desde a interface até o gerenciador de dados, como quais são as operações, seus dados de entrada, e seus retornos.

  A arquitetura final poderia ser melhorada afastando um pouco mais as alterações no estado da aplicação de onde os dados são de fatos armazenados, alocando-as, por exemplo, no controlador.

  O modelo buscou minimizar as dependências entre as classes e otimizar o fluxo de dados, com especial atenção aos pontos de contato cada vez mais externos até a interface de usuário.

  Outras mudanças menores realizadas foram:

  - Nomes de métodos foram uniformizados para serem verbos
  - A lógica de _login_ e _logout_ foi colocada entre o controlador e o gerenciador de dados, ao invés das classes originais, mediando melhor a ponte entre os dados e a interface externa.

]
- Como todas as estruturas já utilizam IDs, é possível desacoplar grande parte delas e centralizar as dependências no gerenciador de dados, que já concentra essas dependências de toda forma
- Métodos uniformizados para serem verbos
- Administrador interage apenas com GerenciadorDados
- professor deixa de ter dependência com Aluno
    - listaAlunosPorTurma() deve pedir alunos ao gerenciador
        - gerenciador retorna alunos pelo id da turmao

 - Lógica de login e logout fica entre o contrololador e o GerenciadorDeDados
 - As operações possíveis são concentradas na classe Requisicao, que serao implementadas de acordo na classe GerenciadorDeDados para serem retornadas à interface
