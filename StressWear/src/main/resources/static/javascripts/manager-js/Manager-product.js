// ------------------DOM - JQUERY------------------//
$("#list-product-tab").click(function (e) {
  $("#list-product-tab").css("border", "1px solid var(--gray_300)");
  $("#list-product-tab").css("background-color", "var(--gray_50)");
  $("#form-product-tab").css("border", "none");
  $("#form-product-tab").css("background-color", "white");
});

$("#form-product-tab").click(function (e) {
  $("#form-product-tab").css("border", "1px solid var(--gray_300)");
  $("#form-product-tab").css("background-color", "var(--gray_50)");
  $("#list-product-tab").css("border", "none");
  $("#list-product-tab").css("background-color", "white");
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
const API_SANPHAM = "http://localhost:8080/api/sanpham";
const API_SANPHAMCHITIET = "http://localhost:8080/api/sanphamchitiet";
const API_DANHSACHKT = "http://localhost:8080/api/danhsachkt";
const API_DANHSACHHA = "http://localhost:8080/api/danhsachha";
const API_HINHANH = "http://localhost:8080/api/hinhanh";
const app = angular.module("app", []);
app.controller("ctrl-sp", function ($scope, $http) {

  // ----------- Biến toàn cục----------- //
  $scope.form_sp = {};
  $scope.list_sp = [];
  $scope.form_spctct = {};
  $scope.list_spct = [];
  $scope.text_search = "";
  $scope.list_size = [];
  $scope.SPCT;

  // ----------- Hàm Sản Phẩm----------- //

  // load dữ liệu của những sản phẩm lên table
  $scope.load_data_sp = function () {
    var url = `${API_SANPHAM}/paging`;
    $http.get(url).then((result) => {
      $scope.list_sp = result.data.content;
      $scope.infor_sp = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  // load dữ liệu của những sản phẩm lên table và phân trang
  $scope.paging_data_sp = function (nameProduct, numberPage) {
    var url = `${API_SANPHAM}/paging?page=${numberPage}&&name=${nameProduct}`;
    $http.get(url).then((result) => {
      $scope.list_sp = result.data.content;
      $scope.infor_sp = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  // sắp xếp dữ liệu trong table sản phẩm
  $scope.sort_list_sp = function (sort) {
    $scope.sort = sort;
    if (sort) {
      $scope.sort_list = `${$("#sortField").val()}`;
    } else {
      $scope.sort_list = `-${$("#sortField").val()}`;
    }
  }

  $("#sortField").change(function () {
    $scope.sort_list_sp($scope.sort);
    $scope.load_data_sp();
  });

  // Hiện sản phẩm lên form
  $scope.edit_sp = function (sanPham) {
    $("#btn-update-sp").css("pointer-events", "all");
    $("#btn-update-sp").css("background-color", "white");

    $("#btn-add-sp").css("pointer-events", "none");
    $("#btn-add-sp").css("background-color", "var(--gray_500)");
    sanPham.ngayTaoSanPham = new Date(sanPham.ngayTaoSanPham);
    $scope.form_sp = angular.copy(sanPham);

    var url = `${API_SANPHAMCHITIET}/sanpham/${sanPham.idSanPham}`;
    $http.get(url).then((result) => {
      $scope.list_spct = [];
      $scope.list_spct = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }



  $scope.delete_sp = function (idSanPham) {
    var url = `${API_SANPHAM}/${idSanPham}`;
    $http.delete(url).then((result) => {
      alert("Xóa sản phẩm thành công");
      var index = $scope.list_sp.findIndex(item => item.idSanPham === result.data.idSanPham);
      $scope.list_sp.splice(index, 1);
      $scope.reset_sp();
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  $scope.reset_sp = function () {
    // $scope.form_sp = {
    //     idNhomLoai: "",
    //     moTaNhomLoai: "",
    //     ngayTao: new Date(),
    //     tenNhomLoai: ""
    // };
    // $("#btn-update-sp").css("pointer-events", "none");
    // $("#btn-update-sp").css("background-color", "var(--gray_100)");

    // $("#btn-add-sp").css("pointer-events", "all");
    // $("#btn-add-sp").css("background-color", "var(--gray_900)");
  };

  // ----------- Hàm Sản Phẩm Chi Tiết----------- //


  $scope.edit_spct = function (sanPhamChiTiet) {
    sanPhamChiTiet.mauSacSPCT.ngayTao = new Date(sanPhamChiTiet.mauSacSPCT.ngayTao);
    $scope.form_spct = angular.copy(sanPhamChiTiet);
    $("#code_hex-color").val(`#${sanPhamChiTiet.mauSacSPCT.maHex}`);
    get_data_kt(sanPhamChiTiet.idSanPhamChiTiet);
    get_data_ha(sanPhamChiTiet.idSanPhamChiTiet);
    $scope.SPCT = sanPhamChiTiet;
  }

  function get_data_kt(idSanPhamChiTiet) {
    var url = `${API_DANHSACHKT}/sanphamchitiet/${idSanPhamChiTiet}`;
    $http.get(url).then((result) => {
      result.data.forEach(element => {
        $("#size" + element.kichThuoc.idKichThuoc)[0].checked = true;
      });
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  function get_data_ha(idSanPhamChiTiet) {
    var url = `${API_DANHSACHHA}/sanphamchitiet/${idSanPhamChiTiet}`;
    $http.get(url).then((result) => {
      $scope.list_ha = result.data;
      console.log(result.data);
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  $scope.add_image = function () {
    $("#fileUpload").trigger("click");
  }

  $("#fileUpload").change(function () {
    $("#uploadImg").css("display", "none");
    $("#btn-loading").css("display", "block");
    const ref = firebase.storage().ref();
    const file = document.querySelector("#fileUpload").files[0];
    const metadata = {
      contentType: 'image/jpeg'
    };
    const nameFile = $("#fileUpload").val().split(/(\\|\/)/g).pop();
    const uploadIMG = ref.child(nameFile).put(file, metadata);
    uploadIMG
      .then(snapshot => snapshot.ref.getDownloadURL())
      .then(url => {
        console.log(url);
        $("#uploadImg").css("display", "block");
        $("#btn-loading").css("display", "none");

        img_post(url.replace("https://",""), file.size, nameFile);
      })
      .catch(console.error)
  });

  function img_post(idHinhAnh, size, name) {
    var hinhAnh = {
      idHinhAnh: idHinhAnh,
      tenHinhAnh: name,
      ngayDang: new Date(),
      dungLuongAnh: size
    }
    var url = `${API_HINHANH}`;
    $http.post(url, hinhAnh).then((result) => {
      // add_dsha(result.data, $scope.SPCT);
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  // function add_dsha(hinhAnh, SPCT) {
  //   var dsHinhAnh = {
  //     idHinhAnh: hinhAnh.idHinhAnh,
  //     idSanPhamChiTiet: SPCT.idSanPhamChiTiet,
  //     hinhAnh: {
  //       idHinhAnh: hinhAnh.idHinhAnh,
  //       tenHinhAnh: hinhAnh.tenHinhAnh,
  //       ngayDang: hinhAnh.ngayDang,
  //       dungLuongAnh: hinhAnh.dungLuongAnh
  //     },
  //     sanPhamChiTiet: {
  //       idSanPhamChiTiet: SPCT.idSanPhamChiTiet,
  //       tenMau: SPCT.tenMau,
  //       sanPhamSPCT: SPCT.sanPhamSPCT,
  //       mauSacSPCT: SPCT.mauSacSPCT
  //     }
  //   }
  //   var url = `${API_DANHSACHHA}`;
  //   $http.post(url,dsHinhAnh).then((result) => {
  //     $scope.list_ha.push(result.data);
  //     console.log(result.data);
  //     alert("Đã thêm hình ảnh vào danh sách");
  //   }).catch((err) => {
  //     console.log("ERROR", err);
  //   });
  // }


  // $scope.update_sp = function (nhomLoai) {
  //     var item = angular.copy(nhomLoai);
  //     var url = `${API_NHOMLOAI}/${nhomLoai.idNhomLoai}`;
  //     $http.put(url, item).then((result) => {
  //         var index = $scope.list_sp.findIndex(item => item.idNhomLoai === result.data.idNhomLoai);
  //         $scope.list_sp[index] = result.data;
  //         alert("Cập nhật nhóm loại thành công");
  //     }).catch((err) => {
  //         console.log("ERROR", err);
  //     });
  // }

  // $scope.create_sp = function () {
  //     var item = angular.copy($scope.form_sp);
  //     var url = `${API_NHOMLOAI}`;
  //     $http.post(url, item).then((result) => {
  //         $scope.list_sp.push(result.data);
  //         alert("Thêm nhóm loại thành công");
  //         $scope.reset_sp();
  //     }).catch((err) => {
  //         console.log("ERROR", err);
  //     });
  // }


  // load dữ liệu của những nhóm loại
  $scope.load_data_nl = function () {
    var url = `http://localhost:8080/api/nhomloai`;
    $http.get(url).then((result) => {
      $scope.list_nl = [];
      $scope.list_nl = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }


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
  $scope.load_data_sp();
  $scope.load_data_nl();
});


















