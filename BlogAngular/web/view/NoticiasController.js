angular.module("noticiasApp", ["ngAnimate"])
        .controller("NoticiasController", ["$http", function ($http) {
                var self = this;
                self.busca = "";
                self.users = [];

                $http.get("../usuario", {params: {busca: self.busca}})
                        .then(function (response) {
                            console.log("opa");
                            self.users = response.data;
                        });
            }])