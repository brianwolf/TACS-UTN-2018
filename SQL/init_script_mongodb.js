use UTN-TACS;

/*=====================================*/
/* ELIMINACION DE DOCUMENTOS */
/*=====================================*/
db.users.drop();
db.coinsHistory.drop();
db.transactions.drop();
db.deposits.drop();
db.roles.drop();
db.conectedUsers.drop();


/*=====================================*/
/* CREACION DE DOCUMENTOS */
/*=====================================*/
db.createCollection("users");
db.createCollection("coinsHistory");
db.createCollection("transactions");
db.createCollection("deposits");
db.createCollection("roles");
db.createCollection("conectedUsers");


/*=====================================*/
/* CARGA DE DATOS */
/*=====================================*/

/*ROLES*/
db.roles.insert([
  { 
    description: "administrator",
    functionalities: [
      "/admin",
      "/users",
      "/servicies/external",
      "/wallets"
    ]
  },
  { 
    description: "user",
    functionalities: [
      "/users",
      "/servicies/external",
      "/wallets"
    ]
  }
]);


/*USUARIOS*/
db.users.insert([
  { 
    login: {
      nick: "lobezzzno",
      pass: "1234",
      active: true,
      tries: 0,
      lastLogin: new Date()
    },
    person: {
      name: "brian",
      lastName: "lobo",
      email: "lobezzzno@gmail.com"
    },
    wallet: {
      coinAmounts: [
        {
         coin: {
          name: "Bitcoin",
          ticker: "BTC",
          valueInDollars: 7586.77
        },
        amount: 0.005
      },
      {
       coin: {
        name: "Ethereum",
        ticker: "ETH",
        valueInDollars: 591.96
      },
      amount: 0.08
    }
  ],
      dolarAmount: 10000
    },
    roles: db.roles.findOne({description: "administrator"})
  },

  { 
    login: {
      nick: "tostado",
      pass: "1234",
      active: true,
      tries: 0,
      lastLogin: new Date()
    },
    person: {
      name: "alexis",
      lastName: "taberna",
      email: "tostado@gmail.com"
    },
    wallet: {
      coinAmounts: [
        {
         coin: {
          name: "Bitcoin",
          ticker: "BTC",
          valueInDollars: 7586.77
        },
        amount: 0.005
      },
      {
       coin: {
        name: "Ethereum",
        ticker: "ETH",
        valueInDollars: 591.96
      },
      amount: 0.08
    }
  ],
      dolarAmount: 10000
    },
    roles: db.roles.findOne({description: "administrator"})
  },

  { 
    login: {
      nick: "boberman"
      pass: "1234",
      active: true,
      tries: 0,
      lastLogin: new Date()
    },
    person: {
      name: "alejandro",
      lastName: "bobero",
      email: "boberman@gmail.com"
    },
    wallet: {
      coinAmounts: [
        {
         coin: {
          name: "Bitcoin",
          ticker: "BTC",
          valueInDollars: 7586.77
        },
        amount: 0.005
       },
       {
       coin: {
        name: "Ethereum",
        ticker: "ETH",
        valueInDollars: 591.96
      },
      amount: 0.08
      }
    ],
      dolarAmount: 10000
    },
    roles: db.roles.findOne({description: "user"})
  }
]);




