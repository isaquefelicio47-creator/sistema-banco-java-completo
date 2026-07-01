* Sistema Bancário: Arquitetura de Gerenciamento e Operações
Este sistema foi desenvolvido como parte integrante do projeto de Programação Orientada a Objetos (POO), com o objetivo de oferecer uma solução robusta para gestão bancária, suporte a múltiplos tipos de contas e monitoramento de movimentações financeiras.

* Arquitetura do Sistema
O projeto foi estruturado seguindo o padrão MVC (Model-View-Controller) adaptado para uma aplicação Desktop Java (Swing), garantindo a separação entre a lógica de persistência de dados, regras de negócio e a interface de usuário.

* Model: Contém as entidades principais (Usuario, ContaBancaria, ContaCorrente, ContaPoupanca e Cliente). A lógica de herança e polimorfismo é utilizada para tratar os comportamentos específicos de cada tipo de conta.

* DAO (Data Access Object): Camada responsável pela abstração da persistência, simulando a conexão com um banco de dados e gerenciando as coleções de objetos.

* Service: Camada de negócio que processa as operações (transferências, cálculos de saldo e autenticação) garantindo a integridade dos dados antes da persistência.

* UI (Interface): Desenvolvida em Java Swing, foca na experiência do usuário com janelas dedicadas para cada operação, incluindo extrato detalhado e controle de acesso.

* Funcionalidades Principais
Gestão de Perfis: Sistema de login com diferenciação entre usuários ADMIN e OPERADOR, com controle de tentativas de acesso.

* Operações Financeiras:

Saque/Depósito: Com validação de limites.

Transferência: Implementação segura com verificação de existência de conta e disponibilidade de saldo/cheque especial.

Extrato Inteligente: Sistema de registro de transações em tempo real. O extrato não apenas exibe o saldo, mas consolida um histórico cronológico e detalhado de todas as movimentações do titular.

Controle de Conta Corrente vs. Poupança:

Conta Corrente: Integração com limite de Cheque Especial dinâmico.

Poupança: Lógica de cálculo de rendimento mensal (abstraída via herança).

* Tecnologias Utilizadas
Linguagem: Java (JDK 17+)

Interface Gráfica: Swing

Persistência: Simulação em memória com ArrayList e HashMap (estruturas otimizadas para busca rápida por login/ID).

IDE: Apache NetBeans

* Como Executar
Certifique-se de ter o JDK instalado.

Clone o repositório ou importe o projeto no seu editor de preferência.

Execute a classe principal banco.app.SistemaBanco.

Utilize o login admin para acessar o gerenciamento total de contas.
