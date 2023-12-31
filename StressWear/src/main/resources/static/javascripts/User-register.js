// console.log("hello");
const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const API_VAITROTAIKHOAN = "http://localhost:8080/api/vaitrotaikhoan";
const app = angular.module("myapp", []);
app.controller("ctrll-register", function ($scope, $http) {
    $scope.form_register = {};

    $scope.create_acc = function () {
        // Kiểm tra nếu xác nhận mật khẩu không khớp hoặc chưa nhập
        if ($scope.form_register.confirmpw !== $scope.form_register.matKhau || !$scope.form_register.confirmpw) {
            alert("Xác nhận mật khẩu không khớp hoặc chưa được nhập.");
            return; // Dừng việc gửi dữ liệu nếu xác nhận mật khẩu không hợp lệ
        }
        // var item = angular.copy($scope.form_register); // lay data tu form khi bam submit
        var item = {
            tenDangNhap: $scope.form_register.tenDangNhap,
            matKhau: $scope.form_register.matKhau,
            email: $scope.form_register.email,
            trangThai: true
        }
        var url = `${API_TAIKHOAN}/email`; // đường dẫn post rest 
        $http.post(url, item).then((result) => {
            window.location="http://localhost:8080/user/confirm";
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    // $scope.add_role = function (taikhoan) {
    //     var vaiTroTaiKhoan = {
    //         idVaiTroTaiKhoan: "",
    //         vaiTro: {
    //             idVaiTro: "USER",
    //             tenVaiTro: ""
    //         },
    //         taiKhoan: {
    //             tenDangNhap: taikhoan.tenDangNhap,
    //             matKhau: taikhoan.matKhau,
    //             email: taikhoan.email,
    //             trangThai: true
    //         }
    //     }
    //     console.log(vaiTroTaiKhoan);
    //     var url = `${API_VAITROTAIKHOAN}`;
    //     $http.post(url, angular.copy(vaiTroTaiKhoan)).then((result) => {
    //         console.log(result.data);
    //         $scope.taikhoanNew = result.data;
    //     }).catch((err) => {
    //         console.log("ERROR", err);
    //     });
    // }
})