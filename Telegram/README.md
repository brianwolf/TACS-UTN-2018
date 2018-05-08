
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
****
`/login <user> <pass>`

Registra al usuario en el sistema.
****
`/buy <coin-ticker> <quantity>`
    
`/sell <coin-ticker> <quantity>`

Registra una transacción de la cryptomoneda indicada en el primer parámetro y su cantidad a la cotización actual.
El usuario debe tener el saldo en dolares/crypto necesario para dicha transacción, de lo contrario
se devuelve el mensaje de error correspondiente.
****
`/amount <coin-ticker>`

Responde la cantidad que posee el usuario de la cryptomoneda indicada en el primer parámetro.
****
`/quote <coin-name>` ó `/quote <coin-ticker>`

Responde la cotización actual de de la cryptomoneda indicada en el primer parámetro.

### Observaciones

` <coin-ticker>`
Hace referencia al ticker o simbolo de la criptomoneda, ej. **BTC** (Bitcoin), **ETH** (Ethereum), **LTC** (Litecoin), etc.

## Instalación

`mvn install`

*El proyecto usa la biblioteca **Lombok** de Apache.
Instalar el archivos **lombok.jar** en su IDE.*

## Ejecución

`mvn exec:java`

## Uso
Buscar en el directorio de Telegram el bot ***TACSCryptoBot*** y usar los comandos descriptos anteriormente. 