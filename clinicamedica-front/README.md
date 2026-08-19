![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![CSS](https://img.shields.io/badge/css-%23663399.svg?style=for-the-badge&logo=css&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)


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
