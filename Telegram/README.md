
# Telegram Bot
## Info

**BotName**
TACSCryptoBot

**UserName**
TACSCryptoBot

**Token**
528360666:AAE215nVkyXqXnOOYt9MJlaMm8o8JxWnyL0

## Comandos

```/me```
Responde el nombre del contacto de quien lo invoque.

```/login <user> <pass>```
Registra al usuario en el sistema.

```/buy <coin-ticker> <quantity>```
```/sell <coin-ticker> <quantity>```
Registra una transacción de la cryptomoneda indicada en el primer parámetro y su cantidad a la cotización actual.
El usuario debe tener el saldo en dolares/crypto necesario para dicha transacción, de lo contrario
se devuelve el mensaje de error correspondiente.

```/coin <coin-ticker>```
Responde la cantidad que posee el usuario de la cryptomoneda indicada en el primer parámetro.

```/quote <coin-ticker>```
Responde la cotización actual de de la cryptomoneda indicada en el primer parámetro.

```/cita```
Responde una cita random sobre lo bueno que es usar el framework Spring :).

## Instalación

```mvn install```

## Ejecución

```mvn exec:java```