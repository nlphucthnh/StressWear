const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
  //Addess
  $scope.address = {
    ad: [],
    addAddress() {
      $http.get(`/api/thongtingiaohang`).then((resp) => {
        this.ad.push(resp.data);
        this.saveToLocalStorage();
        location.href = "/User/pay";
      });
    },
    saveToLocalStorage() {
      var json = JSON.stringify(angular.copy(this.ad));
      localStorage.setItem("address", json);
    },

    //Đọc giỏ hàng từ saveToLocalStorage
    loadFromLocalStorage() {
      var json = localStorage.getItem("address");
      this.ad = json ? JSON.parse(json) : [];
    },
    clear() {
      this.ad = [];
      this.saveToLocalStorage();
    },
  };

  $scope.address.loadFromLocalStorage();
  // var uniqueElements = $scope.address.ad.map((item) => item[0]);
  // console.log(uniqueElements);
  // // Quản lý giỏ hàng
  $scope.cart = {
    items: [],

    //Thêm sản phẩm vào
    add(idSanPham) {
      var item = this.items.find((item) => item.idSanPham == idSanPham);
      if (item) {
        item.qty++;
        this.saveToLocalStorage();
      } else {
        $http.get(`/api/sanpham/${idSanPham}`).then((resp) => {
          resp.data.qty = 1;
          this.items.push(resp.data);
          this.saveToLocalStorage();
        });
      }
    },

    // xóa sản phẩm
    remove(idSanPham) {
      var index = this.items.findIndex((item) => item.idSanPham == idSanPham);
      this.items.splice(index, 1);
      this.saveToLocalStorage();
    },

    // Xóa sạch các mặt hàng
    clear() {
      this.items = [];
      this.saveToLocalStorage();
    },

    // Tính thành tiền 1 sản phẩm
    amt_of(idSanPham) {},

    // Tính tổng số lượng các mặt hàng trong giỏ
    get count() {
      return this.items
        .map((item) => item.qty)
        .reduce((total, qty) => (total += qty), 0);
    },
    // Tổng thành tiền các mặt hàng trong giỏ
    // get amount() {
    //   return this.items
    //     .map((item) => item.qty * item.giaSanPham)
    //     .reduce((total, qty) => (total += qty), 0);
    // },

    get amount() {
      return this.items
        .map(
          (item) =>
            item.qty *
            (item.giaSanPham *
              (1 - (item.khuyenMaiSP ? item.khuyenMaiSP.phanTramKhuyenMai : 0)))
        )
        .reduce((total, amount) => (total += amount), 0);
    },

    // Lưu giỏ hàng vào localStorage
    saveToLocalStorage() {
      var json = JSON.stringify(angular.copy(this.items));
      localStorage.setItem("cart", json);
    },

    //Đọc giỏ hàng từ saveToLocalStorage
    loadFromLocalStorage() {
      var json = localStorage.getItem("cart");
      this.items = json ? JSON.parse(json) : [];
    },
  };

  $scope.cart.loadFromLocalStorage();

  var lastIdThongTinGiaoHang = null;

  if ($scope.address.ad.length > 0) {
    var lastItem = $scope.address.ad[$scope.address.ad.length - 1];
    if (Array.isArray(lastItem) && lastItem.length > 0) {
      lastIdThongTinGiaoHang = lastItem[lastItem.length - 1].idThongTinGiaoHang;
    }
  }
  

  // Đặt hàng
  $scope.order = {
    ngayTao: new Date(),
    phuongThucThanhToan: "Thanh toán sau khi nhận hàng", // Bỏ chú thích và để giá trị mặc định là chuỗi rỗng
    taiKhoanMuaHang: { tenDangNhap: $("#taiKhoanMuaHang").text() }, // Sử dụng một đối tượng chứa tên người dùng
    trangThaiDonHang: "Đang xử lý",
    thongTinGiaoHang: { idThongTinGiaoHang: lastIdThongTinGiaoHang }, //
    get ListDHCT() {
      return $scope.cart.items.map((item) => {
        return {
          sanPhamDHCT: { idSanPham: item.idSanPham },
          // // price: item.giaSanPham,
          soLuong: item.qty,
        };
      });
    },
    purchase() {
      var order = angular.copy(this);
      //thực hiện đặt hàng
      $http
        .post("/api/donhang", order)
        .then((resp) => {
          alert("Đặt hàng thành công!");
          $scope.cart.clear();
          $scope.address.clear();
          location.href = "/order/detail/"+ resp.data.idDonHang;

        })
        .catch((error) => {
          alert("Đặt hàng lỗi");
          console.log(error);
        });
    },
  };
});
