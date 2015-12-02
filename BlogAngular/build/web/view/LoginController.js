angular.module("loginApp", ["ngAnimate"])
        .controller("LoginController", ["$http", function ($http) {
                var self = this;
                self.login = "";
                self.senha = "";
                self.usuario = "";
                self.msg = "";


                self.verLogin = function () {
                    //console.log(self.login);

                    $http.post("login", "nome=" + self.login + "&senha=" + self.senha + "&method=verificar", {
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        }
                    }).then(function (response) {
                        self.usuario = response.data;
                        if (self.usuario !== "") {
                            self.msg = "Login efetuado com sucesso!";
                            window.location = "view/noticias.html";
                            document.getElementById("texto").innerHTML = "chegou até o final goku!";
                        } else {
                            document.getElementById("texto").innerHTML = "Usuario ou senha inválidos!";
                        }
                    });

                };

                self.logout = function () {
                    
                    $http.post("login", "&method=logout", {
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        }
                    });
//                    window.location = "../view/login.html";


                };



            }]);


