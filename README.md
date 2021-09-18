# desafio-java-web-BeHOH

Esta aplica��o foi constru�da como solu��o para o [Desafio Java Web](https://gitlab.com/behoh/desafio-java-web/) da [BeHOH - Solu��es inovadoras para eventos](https://www.behoh.com/)

#### Tecnologias:

Vers�o do JAVA: 11

Maven 3.8.2

Vers�o do SpringBoot: [2.5.4](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent/2.5.4)

Bando de dados: H2

### Como rodar

Na IDE de sua prefer�ncia, importe o projeto para seu "workspace" atrav�s da op��o "importar projeto MAVEN", busque o arquivo EventmanagerApplication.java -> bot�o direito -> rodar como "Springboot app".

Caso prefira executar via linha de comando, abra um terminal dentro da pasta back end e execute `mvn spring-boot:run`

Os endpoints da API estar�o dispon�veis na porta 8080.

Obs: A Interface CommandLineRunner foi implementada para popular o banco.

### Regras gerais implementadas:

N�o permitir a inscri��o de usu�rios quando o limite de vagas for atingido;

N�o permitir a inscri��o de usu�rios ap�s o evento ter sido iniciado;

O usu�rio s� poder� entrar no evento no per�odo de uma hora antes do in�cio do evento at� a hora de t�rmino do evento;

N�o permitir o cancelamento de uma inscri��o ap�s o usu�rio ter realizado a entrada no evento.


### Endpoints dos requisitos do desafio

1 - Realizar a cria��o de um evento;

	POST: /events
	
2 - Realizar a cria��o de um usu�rio;

	POST: /users
	
3 - Realizar a opera��o de inscri��o de um usu�rio em um evento

	POST: /subscriptions
	
4 - Realizar o cancelamento de uma inscri��o de um usu�rio em um evento;

	PATCH: /subscriptions/cancel/{id}
	
5 - Listar as inscri��es de um usu�rio;

	GET: /users/subscriptions/{id}
	
6 - Listar os inscritos de um evento;

	GET: /events/{id}/subscriptions
	
7 - Realizar entrada do usu�rio no evento;

	PATCH: /subscriptions/checkin/{id}
	
8 - Implementar opera��o de cria��o de reservas;
	
	POST: subscriptions/reservation

9 - Realizar convers�o de reservas em inscri��es

	PATCH: /subscriptions/confirm
	
### Documenta��o da API

A documenta��o da API est� dispon�vel em https://documenter.getpostman.com/view/17256075/UUxtDqHd



