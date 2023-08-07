// ------------------------ DOM - JQUERY HTML ------------------------ //
$("#list-accout-tab").click(function (e) {
  $("#list-accout-tab").css("border", "1px solid var(--gray_300)");
  $("#list-accout-tab").css("background-color", "var(--gray_50)");
  $("#form-account-tab").css("border", "none");
  $("#form-account-tab").css("background-color", "white");
});

$("#form-account-tab").click(function (e) {
  $("#form-account-tab").css("border", "1px solid var(--gray_300)");
  $("#form-account-tab").css("background-color", "var(--gray_50)");
  $("#list-accout-tab").css("border", "none");
  $("#list-accout-tab").css("background-color", "white");
});

$(".filte-sort").click(function (e) {
  $(".filte-sort").show();
  $("#" + $(this).attr("id")).hide();
});

$("#code_hex-color").change(function (e) {
  $("#input-text-color").val(this.value);
});

$(".check-box-size .form-check").click(function (e) {
  if (!$(this).children()[1].checked) {
    $(this).children()[1].checked = true;
  } else {
    $(this).children()[1].checked = false;
  }
});

// -------------------------- FORMAT MONYE -------------------------- //
$("input[data-type='currency']").on({
  keyup: function () {
    formatCurrency($(this));
  },
  blur: function () {
    formatCurrency($(this), "blur");
  }
});

function formatCurrency(input, blur) {
  var inputValue = input.val();
  if (inputValue === "") { return; }

  var formattedInputValue = inputValue.replace(/\D/g, "");
  formattedInputValue = Number(formattedInputValue).toLocaleString('vi-VN');

  input.val(formattedInputValue);

  // keep cursor position as is after format
  var caretPosition = input.get(0).selectionStart;
  input.get(0).setSelectionRange(caretPosition, caretPosition);
}


$(".btn-update-acc").css("pointer-events", "none");
$(".btn-update-acc").css("background-color", "var(--gray_200)");

// ----------- API - SPA ----------- //
const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const API_VAITRO = "http://localhost:8080/api/vaitro";
const API_VAITROTAIKHOAN = "http://localhost:8080/api/vaitrotaikhoan";

const app = angular.module("app", []);
app.controller("ctrl-acc", function ($scope, $http) {

  // ----------- Biến toàn cục----------- //
  $scope.form_vttk = {};
  $scope.list_vttk = [];
  $scope.text_search = "";

  // ----------- Hàm Tài khoản---------- //

  // load dữ liệu của những tài khoản lên table
  $scope.load_data_vttk = function () {
    var url = `${API_VAITROTAIKHOAN}/paging`;
    $http.get(url).then((result) => {
      $scope.list_vttk = result.data.content;
      $scope.infor_vttk = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }


  // // load dữ liệu của những tài khoản lên table và phân trang
  $scope.paging_data_vttk = function (nameAccount, numberPage) {
    var url = `${API_VAITROTAIKHOAN}/paging?page=${numberPage}&&tenDangNhap=${nameAccount}`;
    $http.get(url).then((result) => {
      $scope.list_vttk = result.data.content;
      $scope.infor_vttk = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }


  // sắp xếp dữ liệu trong table sản phẩm
  $scope.sort_list_vttk = function (sort) {
    $scope.sort = sort;
    if (sort) {
      $scope.sort_list = `idVaiTroTaiKhoan`;
    } else {
      $scope.sort_list = `-idVaiTroTaiKhoan`;
    }
  }

  $("#role_account").change(function () { 
    $scope.role_account = this.value;
    $scope.load_data_vttk();
  });

  $("#status_account").change(function () { 
    $scope.status_account = this.value;
    $scope.load_data_vttk();
  });



  $scope.edit_acc = function (VaiTroTaiKhoan) {
    $(".btn-update-acc").css("pointer-events", "all");
    $(".btn-update-acc").css("background-color", "white");

    $(".btn-add-acc").css("pointer-events", "none");
    $(".btn-add-acc").css("background-color", "var(--gray_500)");
    $scope.idVaiTro = angular.copy(VaiTroTaiKhoan.vaiTro.idVaiTro.trim());
    $scope.form_vttk = VaiTroTaiKhoan;
    vaiTroCu = $scope.idVaiTro;
    $scope.form_acc = angular.copy(VaiTroTaiKhoan.taiKhoan);
    $scope.form_acc.trangThai = angular.copy(VaiTroTaiKhoan.taiKhoan.trangThai.toString());
    console.log(VaiTroTaiKhoan.taiKhoan.trangThai.toString());
  }


  $scope.update_acc = function () {
    var item = angular.copy($scope.form_acc);
    var url = `${API_TAIKHOAN}/${item.tenDangNhap}`;
    $http.put(url, item).then((result) => {
      var index = $scope.list_vttk.findIndex(item => item.taiKhoan.tenDangNhap === result.data.tenDangNhap);
      $scope.list_vttk[index].taiKhoan = result.data;
      alert("Cập nhật tài khoản thành công");
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }


  $scope.delete_acc = function (tenDangNhap) {
    var url = `${API_TAIKHOAN}/${tenDangNhap}`;
    $http.delete(url).then((result) => {
      alert("Xóa tài khoản thành công");
      var index = $scope.list_vttk.findIndex(item => item.taiKhoan.tenDangNhap === result.data.tenDangNhap.trim());
      $scope.list_vttk.splice(index, 1);
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }



  // $scope.reset_acc = function () {
  //   $scope.form_acc = {
  //     tenDangNhap: "",
  //     matKhau: "",
  //     email:"",
  //     trangThai: false,
  //     vaiTro:"USER"
  // };
  // }

  $scope.load_data_vttk();
});