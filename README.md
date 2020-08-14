# CRdb

mini projeto do curso de Spring Boot da UFPB Campus IV

## Endpoints

- ### Comment


    * PostMapping("/{id_discipline}") : Adiciona um comentario, caso o usuario esteja autenticado.

    * @PutMapping("/{id}") : Atualiza um comentario atraves do id, caso o usuario esteja autenticado.

    * @DeleteMapping("/{id}") : Deleta um comentario atraves do id caso ele esteja autenticado.

- ### Discipline
  - @GetMapping("") : Retorna todas as disciplinas, não precisando da autenticação.
  - @GetMapping("/{name}") ; Retorna a disciplina do nome passado, também não precisando da autenticação.
  * @GetMapping("/auth/ranking") : Retorna uma lista de disciplina ordenada por nota.

* ### Favorite

  - @PostMapping("/{id_discipline}") : Dar like ou deslike dependendo do valor da variavel, usuario precisa esta autenticado para realizar essa função.

* ### Grade

  - @PostMapping("/{id_discipline}") : Adiciona uma nota a disciplina atraves do codigo da disciplina, precisa esta autenticado para realizar essa função.
  - @PutMapping("/{id}") : Atualiza uma nota atraves do id, usuario precisa esta autenticado para realizar essa função.

* ### Login

  - @PostMapping(""): Autentica usuario caso ele esteja no banco.

* ### ProfielDisciplineService

  - @GetMapping("/{code}") : Retorna uma lista de disciplina com nome e nota, usuario precisa esta autenticado para realizar essa função.

* ## User
  - @PostMapping("/user") : Adiciona um usuario ao sistema, não precisa de autenticação para realizar essa função.
  * @DeleteMapping("/auth/user"): Deleta um usuario do banco, precisa de autenticação.
  * @PutMapping("/auth/user"): Atualiza um usuario do banco, usuario precisa esta autenticado para realizar essa função.

## Banco De Dados

    Foi utilizado o banco h2, para realização da manipulação dos dados.

## Disciplina.json

    Foi utilizado a api Curso UFPB, para disponibilizar as disciplinas.

link: http://labdados.dcx.ufpb.br/api/sistemas_de_informacao_rt/disciplinas

### Obs: não consegui implementar o swagger.
