angular.module("usuarioApp", ["ngAnimate"])
        .controller("UsuarioController", ["$http", function ($http) {
                var self = this;
                self.buscaNome = "";
                self.buscaSenha = "";
                self.buscaEmail = "";
                self.users = [];



//            self.frutas = [];
//            self.frutas_removidas = [];

//                $http.get("../usuario", {params: {busca: self.busca}})
//                        .then(function (response) {
//                            console.log("atualizando usuario");
//                            self.users = response.data;
//                        });

                self.add = function () {
                    // document.getElementById("texto").innerHTML = "Preencha os campos corretamente";
                    if (self.buscaNome === "" || self.buscaNome === null ||
                            self.buscaSenha === "" || self.buscaSenha === null ||
                            self.buscaEmail === "" || self.buscaEmail === null) {
                        document.getElementById("texto").innerHTML = "Preencha os campos corretamente";

                    } else {


                        //if (ev.keyCode === 13) {
//                        document.getElementById("texto").innerHTML = "Ate aqui tudo bem";
                        $http.post("../usuario", "nome=" + self.buscaNome + "&senha=" + self.buscaSenha + "&email=" + self.buscaEmail + "&method=adicionar", {
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded"
                            }
                        }).then(function (response) {
                            console.log(response.data);
                            if (response.data !== "false") {
                                //self.frutas.push({nome: self.busca, hidden: ""});
                                self.buscaNome = "";
                                self.buscaSenha = "";
                                self.buscaEmail = "";
                                window.location = "../";
                            } else {
                                document.getElementById("texto").innerHTML = "Este usuário já existe";
                                self.buscaNome = "";
                            }
                        });
                    }
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

