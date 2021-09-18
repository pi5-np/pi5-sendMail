# pi5-sendMail
Microsserviço de envio de email - Newton Paiva


### Microsserviço de Email utilizando Java + Spring Boot + Spring Starter Email

Method: POST
-- URL local para envio de Email: localhost:8080/api/send-mail
Obs.: Caso o Spring Boot esteja rodando em outra porta, substitua o 8080 pela porta escolhida

##### Exemplo de input para envio de EMAIL
{
    "email":"email@hotmail.com",
    "path":"C:\\Users\\public\\AppData\\Local\\Temp\\tmp7742914969084455363.txt"
}
##### Exemplo de output do envio de EMAIL
{} HTTP - 200 - OK
##### O microsserviço espera um EMAIL para o qual será enviado (o email que irá ser usado para enviar os email's já está configurado) e espera um PATH que é o local onde o arquivo .txt está, no caso esse PATH será retornado no Body de outro microsserviço.

