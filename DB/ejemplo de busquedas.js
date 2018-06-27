/*===================================================*/
/* BUSQUEDAS */
/*===================================================*/

/*busquedas en variables*/
db.users.find({"login.nick": "lobezzzno"});

/*aritmetico: <: $lt	>: $gt	<=: $lte	>=: $gte*/
db.users.find({"wallet.dolarAmount": {$gt: 1000} });

/*colecciones*/
db.users.find({"roles.functionalities": "/admin"});
db.users.find({"roles.functionalities": "/admin"}).limit(1);
db.users.find({"roles.functionalities": "/admin"}).sort({"login.nick": 1});
db.users.find({"roles.functionalities": "/admin"}).sort({"login.nick": 1}).limit(1);

/*1: incluye; 0: excluye*/
db.users.find({"roles.functionalities": "/admin"}, {wallet: 1, _id:0});

/*AND*/
db.users.find({
  "roles.functionalities": "/admin", 
  "login.nick": "lobezzzno"
});

/*OR*/
db.users.find({
  $or: [
  	{"login.nick": "lobezzzno"},
  	{"login.nick": "boberman"}
  ]
});

/*OR AND*/
db.users.find({
  "roles.functionalities": "/admin", 
  $or: [
  	{"login.nick": "lobezzzno"},
  	{"login.nick": "boberman"}
  ]
});


/*===================================================*/
/* INSERTS */
/*===================================================*/

db.transactions.insert({
  user: ObjectId("5b0ca21c384ea7b10bc1c566"),
  transactions: []
});


/*===================================================*/
/* UPDATES */
/*===================================================*/

db.transactions.update({
  {_id: ObjectId("5b0ca21c384ea7b10bc1c566")},
  {transactions: {$push: {
    
  }}}
});
















