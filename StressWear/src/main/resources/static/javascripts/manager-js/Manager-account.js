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
$("#USER")[0].checked = true;


// ----------- API - SPA ----------- //
const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const API_VAITRO = "http://localhost:8080/api/vaitro";
const API_VAITROTAIKHOAN = "http://localhost:8080/api/vaitrotaikhoan";

const app = angular.module("app", []);
app.controller("ctrl-acc", function ($scope, $http) {

  // ----------- Biến toàn cục----------- //
  $scope.form_vttk = {};
  $scope.list_vttk = [];
  $scope.form_acc = {
    trangThai: 'true'
  }
  $scope.text_search = "";
  $scope.role_account = 'USER';

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

  $scope.load_data_tk_not_vt = function () {
    var url = `${API_TAIKHOAN}/notrole`;
    $http.get(url).then((result) => {
      $scope.list_tk_notrole = result.data;

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
    // jquery
    $(".btn-update-acc").css("pointer-events", "all");
    $(".btn-update-acc").css("background-color", "white");
    $(".btn-add-acc").css("pointer-events", "none");
    $(".btn-add-acc").css("background-color", "var(--gray_500)");
    $("#username_account").css("pointer-events", "none");
    // angularjs
    $("#USER")[0].checked = false;
    var url = `${API_VAITROTAIKHOAN}/taikhoan/${VaiTroTaiKhoan.taiKhoan.tenDangNhap}`;
    $http.get(url).then((result) => {
      for (let index = 0; index < result.data.length; index++) {
        const element = result.data[index];
        $("#" + element.vaiTro.idVaiTro.trim())[0].checked = true;
      }
    })
    $scope.idVaiTro = angular.copy(VaiTroTaiKhoan.vaiTro.idVaiTro.trim());
    $scope.form_vttk = VaiTroTaiKhoan;
    $scope.form_acc = angular.copy(VaiTroTaiKhoan.taiKhoan);
    $scope.form_acc.trangThai = angular.copy(VaiTroTaiKhoan.taiKhoan.trangThai.toString());
  }

  $scope.edit_acc2 = function (TaiKhoan) {
    // jquery
    $(".btn-update-acc").css("pointer-events", "all");
    $(".btn-update-acc").css("background-color", "white");
    $(".btn-add-acc").css("pointer-events", "none");
    $(".btn-add-acc").css("background-color", "var(--gray_500)");
    $("#username_account").css("pointer-events", "none");
    // angularjs
    $scope.form_acc = angular.copy(TaiKhoan); 
    $("#USER")[0].checked = false;
    $scope.form_acc.trangThai = angular.copy(TaiKhoan.trangThai.toString());
  }

  $scope.create_acc = function () {
    var item = angular.copy($scope.form_acc);
    var url = `${API_TAIKHOAN}`;
    $http.post(url, item).then((result) => {
      add_role(result.data.tenDangNhap, "USER");
      alert("Thêm tài khoản Thành công");
      $scope.load_data_vttk();
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }



  $(".check-box-size .form-check").click(function () {
    if (!$(this).children()[1].checked) {
      var url = `${API_VAITROTAIKHOAN}/${$scope.form_acc.tenDangNhap}/${$(this).children()[1].value}`;
      $http.delete(url).then((result) => {
        console.log("Xóa vai trò Thành công");
        $scope.list_role = [];
      }).catch((err) => {
        console.log("Đã bỏ check");
      });
    } else {
      $scope.list_role = [];
      if ($scope.list_role != null) {
        var index = $scope.list_role.findIndex(item => item == $(this).children()[1].value);
        if (index == -1) {
          $scope.list_role.push($(this).children()[1].value);
        }
      } else {
        $scope.list_role.push($(this).children()[1].value);
      }
    }
  });


  $scope.update_acc = function () {
    var item = angular.copy($scope.form_acc);
    var url = `${API_TAIKHOAN}/${item.tenDangNhap}`;
    $http.put(url, item).then((result) => {
      var index = $scope.list_vttk.findIndex(item => item.taiKhoan.tenDangNhap === result.data.tenDangNhap);
      if(index != -1) {
         $scope.list_vttk[index].taiKhoan = result.data;
      }
      alert("Cập nhật tài khoản thành công");
      return result.data;
    }).then(async (result) => {
      if ($scope.list_role != null) {
        for (let index = 0; index < $scope.list_role.length; index++) {
          add_role(result.tenDangNhap, $scope.list_role[index]);
        }
      }
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
      console.log("thêm vai trò " + result.data.vaiTro.idVaiTro + "Thành công");
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }


  $scope.delete_vttk = function (idVaiTroTaiKhoan) {
    if (confirm("Bạn muốn xóa vai trò này ?")) {
      var url = `${API_VAITROTAIKHOAN}/${idVaiTroTaiKhoan}`;
      $http.delete(url).then((result) => {
        alert("Xóa vai trò thành công");
        var index = $scope.list_vttk.findIndex(item => item.idVaiTroTaiKhoan == result.data.idVaiTroTaiKhoan);
        $scope.list_vttk.splice(index, 1);
      }).catch((err) => {
        console.log("ERROR", err);
      });
      $scope.reset_acc();
    }
  }

  $scope.delete_acc = function (tenDangNhap) {
    if (confirm("Bạn muốn xóa tài khoản này ?")) {
      var url = `${API_TAIKHOAN}/${tenDangNhap}`;
      $http.delete(url).then((result) => {
        var index = $scope.list_tk_notrole.findIndex(item => item.tenDangNhap == result.data.tenDangNhap);
        $scope.list_tk_notrole.splice(index, 1);
        alert("Xóa Tài khoản thành công");
      }).catch((err) => {
        console.log("ERROR", err);
      });
      $scope.reset_acc();
    }
  }


  $scope.reset_acc = function () {
    $scope.list_role = [];
    $scope.form_acc = {
      trangThai: 'true'
    }
    var list = $(".check-box-size .form-check");
    for (let index = 0; index < 3; index++) {
      list[index].children[1].checked = false
    }
    $("#USER")[0].checked = true;
    $(".btn-add-acc").css("pointer-events", "all");
    $(".btn-add-acc").css("background-color", "var(--gray_900)");
  }

  $scope.load_data_vttk();
  $scope.load_data_tk_not_vt();
});