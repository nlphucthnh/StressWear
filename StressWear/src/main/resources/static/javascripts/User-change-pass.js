const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const app = angular.module("myapp", []);
app.controller("ctrl-changepass", function ($scope, $http) {
  $scope.password = {};
  $scope.list = [];

    $scope.updatePass = function () {
      var item = angular.copy($scope.password);
      var url = `${API_TAIKHOAN}/${item.tenDangNhap}`;

      $http.put(url, item).then((result) => {
        var index = $scope.list_sp.findIndex(item => item.tenDangNhap == result.data.tenDangNhap);
        $scope.list_sp[index] = result.data;
        alert("Cập nhật thành công");
  
      }).catch((err) => {
        alert("Cập nhật ko thành công");
        console.log("error", err);
      });
    }

});
