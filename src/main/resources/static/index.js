angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8181/students/rest_api/v1';

    $scope.fillTable = function () {
        $http.get(contextPath )
            .then(function (response) {
                $scope.StudentsList = response.data;
            });
    };

    $scope.deleteStudentById = function (id) {
        $http.delete(contextPath + "/" + id)
            .then(function (response) {
                $scope.fillTable()
            });
    };

    $scope.createNewStudent = function () {
        $http.post(contextPath, $scope.newStudent)
            .then(function (response) {
                $scope.newStudent = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();

});