angular.module("loginApp", ["ngAnimate"])
        .controller("LoginController", ["$http", function ($http) {
                var self = this;
                self.login = "";
                self.senha = "";
//            self.frutas = [];
//            self.frutas_removidas = [];

//            $http.get("usuario", { params: { busca: self.busca} })
//                 .then(function (response) {
//                     self.frutas = response.data;
//                 });

                self.add = function () {
                    
                    //if (ev.keyCode === 13) {
//                        document.getElementById("texto").innerHTML = "Ate aqui tudo bem";
                        $http.post("../LoginController", "nome=" + self.login + "&senha=" + self.senha + "&method=adicionar", {
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded"
                            }
                        }).then(function (response) {
                            //self.frutas.push({nome: self.busca, hidden: ""});
                            self.buscaNome = "";
                            self.buscaSenha = "";
                            self.buscaEmail = "";
                            window.location = "../";
                        });
                 //   }
                };

//            self.search = function () {
//                $http.get("fruta", { params: { busca: self.busca} })
//                     .then(function (response) {
//                         self.frutas = response.data;
//                     });
//            };
//
//            self.remove = function (target) {
//                $http.post("fruta", "id=" + target.id + "&method=deletar", {
//                   headers: {
//                       "Content-Type": "application/x-www-form-urlencoded"
//                   }
//                }).then(function (response) {
//                    for (var i = 0; i < self.frutas.length; i++) {
//                        if (self.frutas[i] === target) {
//                            self.frutas.splice(i, 1);
//                            return;
//                        }
//                    };
//                });
//            };
//
//            self.editar = function (target) {
//                self.remove(target);
//                self.busca = target.nome;
//            };

            }]);


