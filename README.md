# 🏥 Sistema de Gestão de Pacientes - Clínica Médica Dr. Ved

Sistema Fullstack para gerenciamento de prontuários médicos (cadastro, listagem, atualização e exclusão). O projeto utiliza arquitetura desacoplada com API RESTful em Java/Spring Boot e Front-end dinâmico em HTML, CSS e JavaScript no VS Code.


## 🛠️ Tecnologias Utilizadas

* **Back-end:** Java, Spring Boot, Spring Web, Validation, Maven.
* **Front-end:** HTML5, CSS3 (Flexbox/Grid), JavaScript ES6 (Fetch API).
* **Ferramentas:** Spring Tool Suite (STS) / VS Code (Extensão *Live Server*).


## 📁 Estrutura dos Arquivos

```text
├── backend/ (Spring Boot)
│   └── src/main/java/com/example/demo/
│       ├── controller/
│       │   └── PacienteController.java
│       ├── entities/
│       │   └── Paciente.java
│       ├── service/
│       │   └── PacienteService.java
│       └── config/
│           └── CorsConfig.java
│
└── frontend/ (VS Code)
    ├── index.html
    ├── style.css
    ├── script.js
    └── image_008a8b.png (Demais imagens do projeto)
