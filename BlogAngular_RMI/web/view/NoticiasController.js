angular.module("noticiasApp", ["ngAnimate"])
        .controller("NoticiasController", ["$http", "$interval", function ($http, $interval) {
                var self = this;
                self.busca = "";
                self.users = [];
                self.posts = [];
                self.post = "";
                self.titulo = "";
                self.usuarioAtualNome = "";


//                $interval(function () {
//                    $http.get("../usuario", {params: {busca: self.busca}})
//                            .then(function (response) {
//                                console.log("atualizando usuario");
//                                self.users = response.data;
//                            });
//                }, 1000);

                // listando posts
                $interval(function () {
                    $http.get("../noticias", {params: {busca: self.busca}})
                            .then(function (response) {
                                console.log("atualizando posts");
                                self.posts = response.data;
                                console.log(self.posts);
                            });
                }, 1000);


                //Inserindo posts
                self.addPost = function () {
                    console.log("titulo=" + self.titulo);
                    console.log("texto=" + self.post);

                    if (self.titulo === "" || self.titulo === null ||
                            self.post === "" || self.post === null) {
                        document.getElementById("texto").innerHTML = "Preencha os campos corretamente";
                    } else {
                        $http.post("../noticias", "titulo=" + self.titulo + "&post=" + self.post + "&method=adicionar", {
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded"
                            }
                        }).then(function (response) {
                            console.log(response.data);
                            if (response.data !== "false") {
                                //self.frutas.push({nome: self.busca, hidden: ""});
                                self.usuarioAtualNome = "";
                                self.titulo = "";
                                self.post = "";
                                window.location = "../view/noticias.html";
                            } else {
                                document.getElementById("texto").innerHTML = "Não foi possível registrar a postagem!";
                            }
                        });
                    }
                    //   }
                };

                // listando usuarios
                $http.get("../usuario", {params: {busca: self.busca}})
                        .then(function (response) {
                            console.log("atualizando usuario");
                            self.users = response.data;
                        });

                self.visita = function (visita) {
                    $http.get("../noticias", {params: {visita: visita, controle: "sim"}})
                            .then(function (response) {
//                                console.log("atualizando posts");
                                self.posts = response.data;
//                                console.log(self.posts);
                            });
//                     console.log(visita);
                };
                
                self.postsGo = function () {
                    window.location = "../view/CadastrarPosts.html";
                };

            }])