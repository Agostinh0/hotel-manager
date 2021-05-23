# Hotel Manager 

> Projeto que simula uma API de controle de cadastro de hóspedes e realização de check-in e check-out.

## 💻 Pré-requisitos para executá-lo em seu ambiente local.

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Você possui o ```Java 8``` instalado
* Você possui o ```Maven``` instalado
* Você possui um esquema de banco de dados ```MySQL```
* Você possui a variável de ambiente ```CLEARDB_DATABASE_URL``` definida.
* Você possui a variável de ambiente ```HOTEL_MANAGER_DATABASE_USERNAME``` definida.
* Você possui a variável de ambiente ```HOTEL_MANAGER_DATABASE_PASSWORD``` definida.

## 🚀 Instalando o Hotel Manager 

Para instalar o Hotel Manager em seu ambiente local, siga estas etapas:

Clone o projeto para o seu ambiente local:

* Selecione o diretório desejado em seu computador e execute ```git clone https://github.com/Agostinh0/hotel-manager.git```.

E para gerar uma build do projeto:

* Execute dentro do diretório do projeto ```mvn clean install```

## ☕ Usando o Hotel Manager

Para levantar a aplicação, execute ```mvn spring-boot-run```

Para realizar chamadas REST à API, temos as seguintes rotas:

### 📝 Para inserir um hóspede:

URL: ```http://<host>:<porta>/guests/save```

Verbo: ```POST```

Corpo da requisição:  ```{
                        "cpf": <CPF válido*>,
                        "name": <nome>,
                        "phone": <n° de telefone>"
                      }``` JSON

* Você pode gerar um CPF para teste aqui: https://www.4devs.com.br/gerador_de_cpf

### :mag_right: Para listar todos os hóspedes cadastrados

URL: ```http://<host>:<porta>/guests```

Verbo: ```GET```

Corpo da requisição:  N/A

### :mag_right: Para buscar um hóspede por CPF, nome ou n° de telefone

URL: ```http://<host>:<porta>/guests/<cpf | name | phone>/<cpf | nome | n° de telefone>```

Verbo: ```GET```

Corpo da requisição:  N/A

### 📝 Para atualizar dados de um hóspede:

URL: ```http://<host>:<porta>/guests/update?cpf=<cpf do hóspede a editar>&name=<novo nome>&phone=<novo telefone>```

Verbo: ```PUT```

Corpo da requisição:  N/A

### :x: Para deletar um hóspede do sistema:

URL: ```http://<host>:<porta>/guests/delete/<CPF do hóspede>```

Verbo: ```DELETE```

Corpo da requisição:  N/A

### :white_check_mark: Para realizar um Check-in (Criar uma estadia):

URL: ```http://<host>:<porta>/stays/checkIn```

Verbo: ```POST```

Corpo da requisição:  ```{
                            "guest": {
                              "cpf": <CPF do hóspede>,
                              "name": <nome>,
                              "phone": <n° de telefone>
                            },
                            "roomId": <número do quarto>,
                            "checkInDateTime": "<data e hora do check-in *>",
                            "garageNeeded": <true | false>
                          }``` JSON
* Formato de datas = ```yyyy-MM-ddTHH:mm```

### :white_check_mark: Para realizar um Check-out:

URL: ```http://<host>:<porta>/stays/checkOut?stayId=<Id da estadia (check-in)>&checkOutDateTime=<data e hora do check-out>```

Verbo: ```PUT```

Corpo da requisição:  N/A

* Formato de datas = ```yyyy-MM-ddTHH:mm```

### :mag_right: Para listar todos os check-ins (estadias) já feitos no sistema

URL: ```http://<host>:<porta>/stays```

Verbo: ```GET```

Corpo da requisição:  N/A

### :mag_right: Para buscar uma estadia pelo Id

URL: ```http://<host>:<porta>/stays/<id>```

Verbo: ```GET```

Corpo da requisição:  N/A

### :mag_right: Para listar todos os hóspedes que estão no hotel

URL: ```http://<host>:<porta>/stays/currentGuests```

Verbo: ```GET```

Corpo da requisição:  N/A

### :mag_right: Para listar todos os hóspedes que possuem pelo menos uma estadia no hotel (ex-hóspedes)

URL: ```http://<host>:<porta>/stays/formerGuests```

Verbo: ```GET```

Corpo da requisição:  N/A
