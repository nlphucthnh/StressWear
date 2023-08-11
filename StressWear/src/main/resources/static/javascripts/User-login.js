const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const API_VAITROTAIKHOAN = "http://localhost:8080/api/vaitrotaikhoan";
const app = angular.module("app", []);
app.controller("ctrl-confirm", function ($scope, $http) {

    $scope.input_confirm = "";

    $scope.confirm = function () {
        $http.get(`${API_TAIKHOAN}/numberConfirm`).then((result) => {
            if (result.data == $scope.input_confirm) {
                $http.get(`${API_TAIKHOAN}/taikhoandangky`).then((result) => {
                    console.log(result.data);
                    addTaikhoan(result.data)
                }).catch((err) => {
                    console.log("ERROR", err);
                });
            }else {
                alert("Mã xác thực không đúng");
            }
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    function addTaikhoan(taiKhoanDangKy) {
        var url = `${API_TAIKHOAN}`;
        $http.post(url, taiKhoanDangKy).then((result) => {
            add_role(result.data.tenDangNhap, "USER");
            alert("Thêm tài khoản Thành công");
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    function add_role(username, id_role) {
        var vaiTroTaiKhoan = {
          idVaiTroTaiKhoan: "",
          vaiTro: {
            idVaiTro: id_role,
            tenVaiTro: ""
          },
          taiKhoan: {
            tenDangNhap: username,
            matKhau: "",
            email: "",
            trangThai: true
          }
        }
        var url = `${API_VAITROTAIKHOAN}`;
        $http.post(url, vaiTroTaiKhoan).then((result) => {
          window.location="http://localhost:8080/auth/login/form";
        }).catch((err) => {
          console.log("ERROR", err);
        });
      }


})