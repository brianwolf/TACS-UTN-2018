
# Telegram Bot
## Info

#### BotName
TACSCryptoBot

#### UserName
TACSCryptoBot

#### Token
528360666:AAE215nVkyXqXnOOYt9MJlaMm8o8JxWnyL0

## Comandos

`/me`

Responde el nombre del contacto de quien lo invoque.

`/login <user> <pass>`

Registra al usuario en el sistema.

`/buy <coin-id> <quantity>`
    
`/sell <coin-id> <quantity>`

Registra una transacción de la cryptomoneda indicada en el primer parámetro y su cantidad a la cotización actual.
El usuario debe tener el saldo en dolares/crypto necesario para dicha transacción, de lo contrario
se devuelve el mensaje de error correspondiente.

`/coin <coin-id>`

Responde la cantidad que posee el usuario de la cryptomoneda indicada en el primer parámetro.

`/quote <coin-id>`

Responde la cotización actual de de la cryptomoneda indicada en el primer parámetro.

`/cita`

Responde una cita random sobre lo bueno que es usar el framework Spring :).

# Observaciones

` <coin-id>`
Hace referencia al nombre de la criptomoneda, ej. bitcoin, ethereum, litecoin, etc.

## Instalación

`mvn install`

*El proyecto usa la biblioteca **Lombok** de Apache.
Instalar el archivos **lombok.jar** en su IDE.*

## Ejecución

`mvn exec:java`
