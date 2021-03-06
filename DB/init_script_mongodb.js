use TACS-UTN;

/*=====================================*/
/* ELIMINACION DE DOCUMENTOS */
/*=====================================*/
db.users.drop();
db.coinsHistory.drop();
db.transactions.drop();
db.deposits.drop();
db.roles.drop();
db.operations.drop();
db.connectedUsers.drop();


/*=====================================*/
/* CREACION DE DOCUMENTOS */
/*=====================================*/
db.createCollection("users");
db.createCollection("coinsHistory");
db.createCollection("transactions");
db.createCollection("deposits");
db.createCollection("roles");
db.createCollection("operations");
db.createCollection("connectedUsers");


/*=====================================*/
/* CARGA DE DATOS */
/*=====================================*/

/*ROLES*/
db.roles.insertMany([
  { 
    _class: "ar.utn.tacs.model.role.AdminRole",
    description: "Administrador",
    functionalities: [
		{
		 description: "/admin",
		 baseURL:"/admin"
		},
		{
		 description:"/users",
		 baseURL:"/users"
		},
		{
		 description:"/services/external",
		 baseURL:"/services/external"
		},
		{
		 description:"/wallet",
		 baseURL:"/wallet"
		}
    ]
  },
  { 
    _class: "ar.utn.tacs.model.role.UserRole",
    description: "User",
    functionalities: [
		{
		 description:"/users",
		 baseURL:"/users"
		},
		{
		 description:"/services/external",
		 baseURL:"/services/external"
		},
		{
		 description:"/wallet",
		 baseURL:"/wallet"
		}
    ]
  }
]);


/*USUARIOS*/
/*pass = 1234*/
db.users.insertMany([
  { 
    login: {
      nick: "lobezzzno",
      pass: "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
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
    roles: []
  },

  { 
    login: {
      nick: "tostado",
      pass: "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
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
    roles: []
  },

  { 
    login: {
      nick: "boberman",
      pass: "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
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
    roles: []
  }
]);


/*ROLES A LOS USUARIOS*/
db.users.update(
  {"login.nick": "lobezzzno"},
  {$push: {roles: db.roles.findOne({description: "Administrador"})}}
);

db.users.update(
  {"login.nick": "tostado"},
  {$push: {roles: db.roles.findOne({description: "Administrador"})}}
);

db.users.update(
  {"login.nick": "boberman"},
  {$push: {roles: db.roles.findOne({description: "User"})}}
);


