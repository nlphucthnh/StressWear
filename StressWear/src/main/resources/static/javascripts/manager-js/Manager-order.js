// ------------------DOM - JQUERY------------------//
$("#list-order-tab").click(function (e) {
  $("#list-order-tab").css("border", "1px solid var(--gray_300)");
  $("#list-order-tab").css("background-color", "var(--gray_50)");
  $("#form-order-tab").css("border", "none");
  $("#form-order-tab").css("background-color", "white");
});

$("#form-order-tab").click(function (e) {
  $("#form-order-tab").css("border", "1px solid var(--gray_300)");
  $("#form-order-tab").css("background-color", "var(--gray_50)");
  $("#list-order-tab").css("border", "none");
  $("#list-order-tab").css("background-color", "white");
});

$(".filte-sort").click(function (e) {
  $(".filte-sort").show();
  $("#" + $(this).attr("id")).hide();
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
$(".btn-update-dh").css("pointer-events", "none");
$(".btn-update-dh").css("background-color", "var(--gray_200)");

// -------------------------- API - JSON -------------------------- //

const API_DONHANG = "http://localhost:8080/api/donhang";
const API_THONGTINDONHANG = "http://localhost:8080/api/thongtingiaohang";
const API_DONHANGCHITIET = "http://localhost:8080/api/donhangchitiet";
const app = angular.module("app", []);
app.controller("ctrl-dh", function ($scope, $http) {


  $scope.form_dh = {};
  $scope.list_dh = [];
  $scope.text_search = "";
  $scope.form_cp = {};

  $scope.load_data_dh = function () {
    var url = `${API_DONHANG}/paging`;
    $http.get(url).then((result) => {
      $scope.list_dh = result.data.content;
      console.log($scope.list_dh);
      $scope.infor_dh = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

    // sắp xếp dữ liệu trong table sản phẩm
    $scope.sort_list_dh = function (sort) {
      $scope.sort = sort;
      if (sort) {
        $scope.sort_list = `idDonHang`;
      } else {
        $scope.sort_list = `-idDonHang`;
      }
    }

    $("#method_payment").change(function () { 
      $scope.method_payment = this.value;
      $scope.load_data_dh();
    });

    $("#status_order").change(function () { 
      $scope.status_order = this.value;
      $scope.load_data_dh();
    });

    

  // load dữ liệu của những sản phẩm lên table và phân trang
  $scope.paging_data_dh = function (numberPage) {
    var url = `${API_DONHANG}/paging?page=${numberPage}&&idDonHang=${$scope.text_search}`;
    $http.get(url).then((result) => {
      $scope.list_dh = result.data.content;
      $scope.infor_dh = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  $scope.edit_dh = function (donhang) {
    $(".btn-update-dh").css("pointer-events", "all");
    $(".btn-update-dh").css("background-color", "white");
    donhang.ngayTao = new Date(donhang.ngayTao);
    $scope.form_dh = angular.copy(donhang);

    $http.get(`${API_DONHANGCHITIET}/donhang/${donhang.idDonHang}`).then((result) => {
      console.log(result.data);
      $scope.list_dhct = result.data;
      $scope.form_cp.tong_phu = 0.0;
      result.data.forEach(element => {
        $scope.form_cp.tong_phu += element.sanPhamDHCT.giaSanPham * element.soLuong;
      });
      $scope.form_cp.phi_van_chuyen = 30000.0;
      $scope.form_cp.tong_cong = $scope.form_cp.phi_van_chuyen + $scope.form_cp.tong_phu;
    }).catch((err) => {
      console.log("ERROR", err);
    });

  }

  $scope.update_dh = function () {
    var item1 = angular.copy($scope.form_dh);
    var url = `${API_DONHANG}/${item1.idDonHang}`;
    $http.put(url, item1).then((result) => {
      var index = $scope.list_dh.findIndex(item => item.idDonHang === result.data.idDonHang);
      $scope.list_dh[index] = result.data;
      alert("Cập nhật đơn hàng thành công");
    }).catch((err) => {
      console.log("ERROR", err);
    });

    var item2 = angular.copy($scope.form_dh.thongTinGiaoHang);
    var url = `${API_THONGTINDONHANG}/${item2.idThongTinGiaoHang}`;
    $http.put(url, item2).then((result) => {
      alert("Cập nhật thong tin giao hàng thành công");
    }).catch((err) => {
      console.log("ERROR", err);
    });

  }

  $scope.delete_dh = function (idDonHang) {
    var url = `${API_DONHANG}/${idDonHang}`;
    $http.delete(url).then((result) => {
      alert("Xóa đơn hàng thành công");
      var index = $scope.list_dh.findIndex(item => item.idDonHang === result.data.idDonHang);
      $scope.list_dh.splice(index, 1);
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  $scope.delete_dhct = function (idDonHangChiTiet) {
    var url = `${API_DONHANGCHITIET}/${idDonHangChiTiet}`;
    $http.delete(url).then((result) => {
      alert("Xóa đơn hàng chi tiết thành công");
      var index = $scope.list_dhctct.findIndex(item => item.idDonHangChiTiet === result.data.idDonHangChiTiet);
      $scope.list_dhct.splice(index, 1);
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  $scope.reset_dh = function () {
    $scope.form_dh = {
      idDonHang: "",
      ngayTao: new Date(),
      phuongThucThanhToan: "cash",
      trangThaiDonHang: "doing",
      taiKhoanMuaHang: {},
      thongTinGiaoHang: {}

    };
    $scope.list_dhct = [];
    $scope.form_cp = {};

    $(".btn-update-dh").css("pointer-events", "none");
    $(".btn-update-dh").css("background-color", "var(--gray_300)");
  };

  $scope.convert_date = function (date) {
    if ((typeof date) == "string") {
      if (date.length > 9) {
        var dateNew = new Date(date);
        let day = dateNew.getDate();
        let month = dateNew.getMonth();
        let year = dateNew.getFullYear();
        return `${year}-0${month + 1}-${day}`;
      }
      return date;
    } else {
      let day = date.getDate();
      let month = date.getMonth();
      let year = date.getFullYear();
      return `${year}-0${month + 1}-${day}`;
    }
  }

  // $scope.reset_dh();
  $scope.load_data_dh();
})




