const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
  // Quản lý giỏ hàng
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
        .map((item) => item.qty * (item.giaSanPham * (1 - (item.khuyenMaiSP ? item.khuyenMaiSP.phanTramKhuyenMai : 0))))
        .reduce((total, amount) => (total += amount), 0);
    },
     

    // Lưu giỏ hàng vào localStorage
    saveToLocalStorage() {
      var json = JSON.stringify(angular.copy(this.items));
      localStorage.setItem("cart", json);
      console.log(user);
      console.log("ojkekeoekofd");
    },

    //Đọc giỏ hàng từ saveToLocalStorage
    loadFromLocalStorage() {
      var json = localStorage.getItem("cart");
      this.items = json ? JSON.parse(json) : [];
    },
  };

  $scope.cart.loadFromLocalStorage();

  // Đặt hàng

  $scope.order = {
    createDate: new Date(),
    address: "",
    account: {username: $("#username").text()},
    get orderDetails(){
      return $scope.cart.items.map(item => {
        return {
          product:{id: item.idSanPham},
          // price: item.price,
          // quantity: item.qty
        }
      });
    },
    purchase(){
      var order = angular.copty(this);

      //thực hiện đặt hàng
      $http.post("/api/donhang", order).then(resp => {
        alert("Đặt hàng thành công!");
        $scope.cart.clear()
        location.href = "/order/detail/" + resp.data.id;
      }).catch(error => {
        alert("Đặt hàng lỗi")
        console.log(error);
      })
    }
  }

});
