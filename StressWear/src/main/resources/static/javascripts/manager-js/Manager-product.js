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

$("#btn_open_modal_spct").css("pointer-events", "none");
$("#btn_open_modal_spct").css("background-color", "var(--gray_300)");
$(".check-box-size .form-check").css("pointer-events", "none")
$(".check-box-size").css("background-color", "var(--gray_300)");
$("#uploadImg").css("pointer-events", "none")
$("#uploadImg").css("background-color", "var(--gray_300)");

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
const API_MAUSAC = "http://localhost:8080/api/mausac";
const app = angular.module("app", []);
app.controller("ctrl-sp", function ($scope, $http) {

  // ----------- Biến toàn cục----------- //
  $scope.form_sp = {};
  $scope.list_sp = [];
  $scope.form_spct = {};
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
    $(".btn-update-sp").css("pointer-events", "all");
    $(".btn-update-sp").css("background-color", "white");

    $(".btn-add-sp").css("pointer-events", "none");
    $(".btn-add-sp").css("background-color", "var(--gray_500)");
    $("#btn_open_modal_spct").css("pointer-events", "all");
    $("#btn_open_modal_spct").css("background-color", "var(--gray_800)");
    sanPham.ngayTaoSanPham = new Date(sanPham.ngayTaoSanPham);
    $scope.form_sp = angular.copy(sanPham);

    var url = `${API_SANPHAMCHITIET}/sanpham/${sanPham.idSanPham}`;
    $http.get(url).then((result) => {
      $scope.list_spct = [];
      $scope.list_spct = result.data;
    }).catch((err) => {
      console.log("ERROR", err);
    });
    $("#form-product-tab").trigger("click");
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
    $scope.form_sp = {
      idSanPham: "",
      tenSanPham: "",
      giaSanPham: 0,
      ngayTaoSanPham: new Date(),
      moTaSanPham: "",
      soLuongSP: 0,
      trangThaiTonKho: false,
      nhomLoaiSP: {},
      khuyenMaiSP: null
    };

    $scope.list_spct = [];
    $scope.reset_spct();
    $(".btn-update-sp").css("pointer-events", "none");
    $(".btn-update-sp").css("background-color", "var(--gray_100)");
    $(".btn-add-sp").css("pointer-events", "all");
    $(".btn-add-sp").css("background-color", "var(--gray_900)");
  };

  $scope.create_sp = function () {
    var item = angular.copy($scope.form_sp);
    var url = `${API_SANPHAM}`;
    $http.post(url, item).then((result) => {
      $scope.list_sp.push(result.data);
      $scope.form_sp.idSanPham = result.data.idSanPham;
      alert("Thêm sản phẩm thành công");
      $("#btn_open_modal_spct").css("pointer-events", "all");
      $("#btn_open_modal_spct").css("background-color", "var(--gray_800)");
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  $scope.update_sp = function () {
    var item = angular.copy($scope.form_sp);
    var url = `${API_SANPHAM}/${item.idSanPham}`;
    $http.put(url, item).then((result) => {
      var index = $scope.list_sp.findIndex(item => item.idSanPham == result.data.idSanPham);
      $scope.list_sp[index] = result.data;
      alert("Cập nhật sản phẩm thành công");
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }



  // ----------- Hàm Sản Phẩm Chi Tiết----------- //

  $scope.edit_spct = function (sanPhamChiTiet) {
    sanPhamChiTiet.mauSacSPCT.ngayTao = new Date(sanPhamChiTiet.mauSacSPCT.ngayTao);
    $scope.form_spct = angular.copy(sanPhamChiTiet);
    $("#code_hex-color").val(`${sanPhamChiTiet.mauSacSPCT.maHex}`);
    get_data_kt(sanPhamChiTiet.idSanPhamChiTiet);
    get_data_ha(sanPhamChiTiet.idSanPhamChiTiet);
    $scope.SPCT = sanPhamChiTiet;

    $(".btn-update-spct").css("pointer-events", "all");
    $(".btn-update-spct").css("background-color", "white");

    $(".btn-add-spct").css("pointer-events", "none");
    $(".btn-add-spct").css("background-color", "var(--gray_500)");

    $(".check-box-size .form-check").css("pointer-events", "all")
    $(".check-box-size").css("background-color", "#fff");
    $("#uploadImg").css("pointer-events", "all")
    $("#uploadImg").css("background-color", "var(--gray_700)");
  }

  function get_data_kt(idSanPhamChiTiet) {
    var url = `${API_DANHSACHKT}/sanphamchitiet/${idSanPhamChiTiet}`;
    $http.get(url).then((result) => {
      result.data.forEach(element => {
        $scope.list_size.push(element.kichThuoc.idKichThuoc.trim() + "/" + element.idDanhSachKT);
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

  // Post ảnh lên firebase và database
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
        $("#uploadImg").css("display", "block");
        $("#btn-loading").css("display", "none");
        img_post(url.replace("https://", ""), file.size / 1000000, nameFile);
      })
      .catch(console.error)
  });

  function img_post(url, size, name) {
    var hinhAnh = {
      idHinhAnh: "",
      tenHinhAnh: name,
      ngayDang: new Date(),
      dungLuongAnh: size,
      duongDan: url
    }
    var url = `${API_HINHANH}`;
    $http.post(url, hinhAnh).then((result) => {
      add_dsha(result.data, $scope.SPCT);
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  function add_dsha(hinhAnh, SPCT) {
    var dsHinhAnh = {
      idDanhSachHA: "",
      hinhAnh: hinhAnh,
      sanPhamChiTiet: SPCT
    }
    var url = `${API_DANHSACHHA}`;
    $http.post(url, dsHinhAnh).then((result) => {
      console.log(result);

      $scope.list_ha.push(result.data);
      alert("Đã thêm hình ảnh vào danh sách");
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  // Delete ảnh lên firebase và database
  $scope.delete_image = function (hinhAnh) {
    var message = "Bạn muốn xóa hình này ?";
    if (confirm(message)) {
      const storageRef = firebase.storage().ref();
      var desertRef = storageRef.child(hinhAnh.tenHinhAnh);
      desertRef.delete().then(() => {
        alert("xóa hình ảnh thành công");
      }).then(() => {
        $http.delete(`${API_HINHANH}/${hinhAnh.idHinhAnh}`).then((result) => {
          var index = $scope.list_ha.findIndex(item => item.idHinhAnh === result.data.idHinhAnh);
          $scope.list_ha.splice(index, 1);
          console.log("SUCCESS", result);
        })
      }).catch((error) => {
        console.log("không tìm thấy ảnh");
      });
    }
  }

  $scope.update_spct = function () {
    var MauSac = {
      idMauSac: "",
      tenMauSac: $scope.form_spct.mauSacSPCT.tenMauSac,
      maHex: $scope.form_spct.mauSacSPCT.maHex
    }
    $http.post(API_MAUSAC, MauSac).then((result) => {
      return result.data;
    }).then((result) => {
      var spct = angular.copy($scope.form_spct);
      spct.mauSacSPCT.idMauSac = result.idMauSac;
      var url = `${API_SANPHAMCHITIET}/${spct.idSanPhamChiTiet}`;
      $http.put(url, spct).then((result) => {
        var index = $scope.list_spct.findIndex(item => item.idSanPhamChiTiet == result.data.idSanPhamChiTiet);
        $scope.list_spct[index] = result.data;
        alert("Cập nhật thành công")
        return
      }).catch((err) => {
        console.log("ERROR", err);
      });
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }


  $(".check-box-size .form-check").click(function () {
    if (!$(this).children()[1].checked) {
      $(this).children()[1].checked = true;
      var index = $scope.list_size.findIndex(item => item.slice(0, item.indexOf("/")) == $(this).children()[1].value);
      console.log(index);
      if (index == -1) {
        var kichThuoc = {
          idDanhSachKT: "",
          kichThuoc: {
            idKichThuoc: $(this).children()[1].value,
            tenKichThuoc: ""
          },
          sanPhamChiTiet: $scope.SPCT
        }
        $http.post(API_DANHSACHKT, kichThuoc).then((result) => {
          $scope.list_size.push($(this).children()[1].value + "/" + result.data.idDanhSachKT);
          alert("Đã thêm kích thước");
        }).catch((err) => {
          console.log("ERROR", err);
        });
      }
    } else {
      $(this).children()[1].checked = false;
      var index = $scope.list_size.findIndex(item => item.slice(0, item.indexOf("/")) == $(this).children()[1].value);
      if (index >= 0) {
        var idDanhSachKT = $scope.list_size[index].slice($scope.list_size[index].indexOf("/") + 1);
        console.log(idDanhSachKT);
        $http.delete(`${API_DANHSACHKT}/${idDanhSachKT}`, kichThuoc).then((result) => {
          $scope.list_size.splice(index, 1);
          alert("Đã Xóa kích thước");
        }).catch((err) => {
          console.log("ERROR", err);
        });
      }
    }
  });

  $("#code_hex-color").change(function (e) {
    $("#input-text-color").val(this.value);
    $scope.form_spct.mauSacSPCT.maHex = this.value;
  });

  $scope.delete_spct = function (idSanPhamChiTiet) {
    var url = `${API_SANPHAMCHITIET}/${idSanPhamChiTiet}`;
    $http.delete(url).then((result) => {
      alert("Xóa sản phẩm chi tiết thành công");
      var index = $scope.list_spct.findIndex(item => item.idSanPhamChiTiet == result.data.idSanPhamChiTiet);
      $scope.list_spct.splice(index, 1);
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

  $scope.reset_spct = function () {
    $scope.form_spct = {
      idSanPhamChiTiet: "",
      tenMau: "",
      sanPhamSPCT: {},
      mauSacSPCT: {
        idMauSac: 1,
        tenMauSac: "",
        maHex: "000000"
      },
      ngayTao: null
    }
    var list = $(".check-box-size .form-check");
    for (let index = 0; index < 5; index++) {
      list[index].children[1].checked = false
    }

    $scope.list_ha = [];

    $("#btn-update-spct").css("pointer-events", "none");
    $("#btn-update-spct").css("background-color", "var(--gray_100)");

    $("#btn-add-spct").css("pointer-events", "all");
    $("#btn-add-spct").css("background-color", "var(--gray_900)");
  };

  $scope.create_spct = function () {
    var MauSac = {
      idMauSac: "",
      tenMauSac: $scope.form_spct.mauSacSPCT.tenMauSac,
      maHex: $scope.form_spct.mauSacSPCT.maHex
    }
    var sanPhamChiTiet = {
      idSanPhamChiTiet: "",
      tenMau: $scope.form_spct.tenMau,
      sanPhamSPCT: $scope.form_sp,
      mauSacSPCT: {},
      ngayTao: new Date()
    }
    $http.post(API_MAUSAC, MauSac).then((result) => {
      return result.data;
    }).then((data) => {
      sanPhamChiTiet.mauSacSPCT = data;
      var url = `${API_SANPHAMCHITIET}`;
      $http.post(url, sanPhamChiTiet).then((result) => {
        $scope.list_spct.push(result.data);
        $scope.SPCT = result.data;
        $scope.form_spct = result.data;
      }).then(() => {
        alert("Tạo sản phẩm chi tiết thành công");
        $(".check-box-size .form-check").css("pointer-events", "all")
        $(".check-box-size").css("background-color", "#fff");
        $("#uploadImg").css("pointer-events", "all")
        $("#uploadImg").css("background-color", "var(--gray_700)");
        $("#btn-add-spct").css("pointer-events", "none");
        $("#btn-add-spct").css("background-color", "var(--gray_500)");
        $("#btn-update-spct").css("pointer-events", "all");
        $("#btn-update-spct").css("background-color", "var(--gray_900)");
      })
    }).catch((err) => {
      console.log("ERROR", err);
    });
  }

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


















