angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8181/students/rest_api/v1';

    $scope.getStudent = function (id) {
        $http.get(contextPath + "/" + id)
            .then(function (response) {
                $scope.StudentName = response.data.name;
                $scope.StudentAge = response.data.age;
            });
    };


    $scope.changeStudent = function () {
        $http.post(contextPath, $scope.updateStudent)
            .then(function (response) {
                $scope.updateStudent = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();

});