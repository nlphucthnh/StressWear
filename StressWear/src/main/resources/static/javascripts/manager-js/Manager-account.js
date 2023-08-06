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

// ----------- API - SPA ----------- //
const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const API_VAITRO = "http://localhost:8080/api/vaitro";
const API_VAITROTAIKHOAN = "http://localhost:8080/api/vaitrotaikhoan";

const app = angular.module("app", []);
app.controller("ctrl-acc", function ($scope, $http) {

    // ----------- Biến toàn cục----------- //
    $scope.form_acc = {};
    $scope.list_acc = [];
    $scope.form_acctct = {};
    $scope.list_acct = [];
    $scope.text_search = "";
    $scope.list_size = [];
    $scope.ACCT;


    
  // ----------- Hàm Sản Phẩm----------- //

  // load dữ liệu của những tài khoản lên table
  $scope.load_data_acc = function () {
    var url = `${API_TAIKHOAN}/paging`;
    $http.get(url).then((result) => {
      $scope.list_acc = result.data.content;
      $scope.infor_acc = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  // load dữ liệu của những tài khoản lên table và phân trang
  $scope.paging_data_acc = function (nameAccount, numberPage) {
    var url = `${API_TAIKHOAN}/paging?page=${numberPage}&&name=${nameAccount}`;
    $http.get(url).then((result) => {
      $scope.list_acc = result.data.content;
      $scope.infor_acc = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  // sắp xếp dữ liệu trong table sản phẩm
  $scope.sort_list_acc = function (sort) {
    $scope.sort = sort;
    if (sort) {
      $scope.sort_list = `${$("#sortField").val()}`;
    } else {
      $scope.sort_list = `-${$("#sortField").val()}`;
    }
  }

  $("#sortField").change(function () {
    $scope.sort_list_acc($scope.sort);
    $scope.load_data_acc();
  });

  // Hiện sản phẩm lên form
  $scope.edit_acc = function (taikhoan) {
    $("#btn-update-acc").css("pointer-events", "all");
    $("#btn-update-acc").css("background-color", "white");

    $("#btn-add-acc").css("pointer-events", "none");
    $("#btn-add-acc").css("background-color", "var(--gray_500)");
    taikhoan.ngayTaoTaiKhoan = new Date(taikhoan.ngayTaoTaiKhoan);
    $scope.form_acc = angular.copy(taikhoan);

    var url = `${API_VAITROTAIKHOAN}/taikhoan/${taiKhoan.tenDangNhap}`;
    $http.get(url).then((result) => {
      $scope.list_acct = [];
      $scope.list_acct = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }


    $scope.load_data_acc();
});