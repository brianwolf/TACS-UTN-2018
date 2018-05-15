
# Telegram Bot
## Info

#### BotName
TACSCryptoBot

#### UserName
TACSCryptoBot

## Comandos

`/me`

Responde el nombre del contacto de quien lo invoque.
****
`/login <user> <pass>`

Inicia sesión en el sistema.
****
`/logout`

Finaliza sesión en el sistema.
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
****
`/commands`

Muestra por pantalla la lista completa de comandos disponibles.
****
`/help`

Ayuda para operar el Bot.

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
El usuario ya debe poseer previamente una cuenta en el sistema.