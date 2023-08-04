const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const API_VAITROTAIKHOAN = "http://localhost:8080/api/vaitrotaikhoan";
const app = angular.module("app", []);
app.controller("ctrll-register", function ($scope, $http) {
    $scope.form_register = {};

    $scope.create_acc = function () {
        var item = angular.copy($scope.form_register);// lấy dữ liệu từ form khi người ta sumit
        console.log(item);
        var taikhoanNew = {};
        var url = `${API_TAIKHOAN}`;// đường dẫn post
        $http.post(url, item).then((result) => {
            console.log(result.data);// trả ra tài khoản vừa tạo
            $scope.add_role(result.data)
            alert("Thêm tài khoản thành công");
        }).catch((err) => {
            console.log("ERROR", err);
        }); 
       
    }


    $scope.add_role = function (taikhoan) {
        var vaiTroTaiKhoan = {
            idVaiTroTaiKhoan: "",
            vaiTro: {
                idVaiTro: "USER",
                tenVaiTro: "Đứa khách hàng"
            },
            taiKhoan: {
                tenDangNhap: taikhoan.tenDangNhap,
                matKhau: taikhoan.matKhau,
                email: taikhoan.email,
                trangThai: true
            }
        }
        console.log(vaiTroTaiKhoan);
        var url = `${API_VAITROTAIKHOAN}`;
        $http.post(url, angular.copy(vaiTroTaiKhoan)).then((result) => {
            console.log(result.data);
            $scope.taikhoanNew = result.data;
            alert("Thêm vai trò thành công");
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

})