// ------------------ Load image backgourd ------------------ //

$("#upload-bg-pen").click(function (e) {
    $("#upload_bg").trigger("click");
    // khi ấn vào btn sẽ auto click 
});
$("#upload_bg").change(function () {
    $("#spinner .spinner-border").show(); // hiện icon loading
    $("#upload-bg-pen").css("pointer-events", "none"); // ko cho phép nhấn vào btn pencil
    var img_bg_old = $(".background-profile-img").attr('src'); // lấy img lưu 
    const ref = firebase.storage().ref(); // gọi firebase
    const file = document.querySelector("#upload_bg").files[0]; // lấy file ra 
    const metadata = {
        contentType: 'image/jpeg' // định dạng kiểu dữ liệu là hình ảnh
    };
    const nameFile = $("#upload_bg").val().split(/(\\|\/)/g).pop(); // cắt chuỗi http 
    const uploadIMG = ref.child(nameFile).put(file, metadata);
    uploadIMG // insert lên fire base
        .then(snapshot => snapshot.ref.getDownloadURL())
        .then(url => {
            $(".background-profile-img").attr('src', url);
            $("#upload_bg-text").val(url);
            $("#spinner .spinner-border").hide();
            $("#upload-bg-pen").css("pointer-events", "all");
        })
        .catch(console.error)
});

// ------------------ Load image backgourd ------------------ //


// ------------------ Load image avatar ------------------ //

$("#upload-avata-pen").click(function (e) {
    $("#upload_avatar").trigger("click");

});
$("#upload_avatar").change(function () {
    $("#upload-avata-pen").css("pointer-events", "none");
    var img_bg_old = $(".avata-img").attr('src');
    const ref = firebase.storage().ref();
    const file = document.querySelector("#upload_avatar").files[0];
    const metadata = {
        contentType: 'image/jpeg'
    };
    const nameFile = $("#upload_avatar").val().split(/(\\|\/)/g).pop();
    const uploadIMG = ref.child(nameFile).put(file, metadata);
    uploadIMG
        .then(snapshot => snapshot.ref.getDownloadURL())
        .then(url => {
            $(".avata-img").attr('src', url);
            $("#upload_avatar-text").val(url);
            $("#upload-avata-pen").css("pointer-events", "all");
        })
        .catch(console.error)
});

// ------------------ Load image backgourd ------------------ //


const API_DONHANG = "http://localhost:8080/api/donhang";
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    $scope.list_dh = [];
    $http.get(API_DONHANG).then((result) => {
       console.log(result.data);
       result.data.forEach(element => {
            if(element.taiKhoanMuaHang.tenDangNhap == $("#tenDangNhap").val()){
                $scope.list_dh.push(element)
            }
       });
       
    }).catch((err) => {
        
    });
})
app.controller("shopping-cart-ctrl", function ($scope, $http) {
    // function showError() {
    //   const errorMessage = document.getElementById("error-message");
    //   errorMessage.classList.add("visible");
  
    //   // Sau 3 giây, ẩn thông báo lỗi
    //   setTimeout(() => {
    //     errorMessage.classList.remove("visible");
    //   }, 3000);
    // }
  
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
          // Kiểm tra nếu số lượng hiện tại đã đạt tối đa (ví dụ: 10 là số lượng tối đa)
          if (item.qty >= item.soLuongSP) {
            showCartNotification("Đã hết hàng !!");
          } else {
            showsussce("Thêm vào giỏ thành công");
            item.qty++;
            this.saveToLocalStorage();
          }
        } else {
          $http.get(`/api/sanpham/${idSanPham}`).then((resp) => {
            resp.data.qty = 1;
            this.items.push(resp.data);
            this.saveToLocalStorage();
          });
        }
      },
  
      //Bắt lỗi số lượng
      updateQty(item) {
        // Giả sử số lượng tối đa là `item.soLuong` (thay `soLuong` bằng tên thật trong bảng sản phẩm)
        if (item.qty >= item.soLuongSP) {
          item.qty = item.soLuongSP;
          showCartNotification("Đã hết hàng !!");
        }
        this.saveToLocalStorage();
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
      get countByProductId() {
        var idSanPhamInput = document.getElementById("idSanPham");
        var targetIdSanPham = parseInt(idSanPhamInput.value);
        
        return this.items
          .filter(item => item.idSanPham === targetIdSanPham)
          .map(item => item.qty)
          .reduce((total, qty) => (total += qty), 0);
      },
  
      // Tổng thành tiền các mặt hàng trong giỏ
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
      phuongThucThanhToan: "cash", // Bỏ chú thích và để giá trị mặc định là chuỗi rỗng
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
            document
              .getElementById("showAlertButton")
              .addEventListener("click", function () {
                // Hàm hiển thị thông báo
                Swal.fire({
                  title: "Thông báo",
                  text: "Đặt hàng thành công",
                  icon: "success",
                  confirmButtonText: "OK",
                }).then((result) => {
                  if (result.isConfirmed) {
                    $scope.cart.clear();
                    $scope.address.clear();
                    location.href = "/order/detail/" + resp.data.idDonHang;
                  }
                });
              });
          })
          .catch((error) => {
            // showError();
            console.log(error);
          });
      },
    };
    $scope.orderpaypal = {
      ngayTao: new Date(),
      phuongThucThanhToan: "credit", // Bỏ chú thích và để giá trị mặc định là chuỗi rỗng
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
      purchasepaypal() {
        var order = angular.copy(this);
        //thực hiện đặt hàng
        $http
          .post("/api/donhang", order)
          .then((resp) => {
            $scope.cart.clear();
            $scope.address.clear();
            // location.href = "/order/detail/" + resp.data.idDonHang;
          })
          .catch((error) => {
            // showError();
            console.log(error);
          });
      },
    };
  });
  function showCartNotification(message) {
    var toast = document.querySelector(".toast").cloneNode(true);
    var toastBody = toast.querySelector(".toast-body");
    toastBody.textContent = message;
  
    var toastContainer = document.querySelector(".toast-container");
    
      toast.classList.add("bg-danger", "text-white"); // Thêm lớp bg-danger và text-white
    
    
    toastContainer.appendChild(toast);
  
    var bootstrapToast = new bootstrap.Toast(toast);
    bootstrapToast.show();
  
    setTimeout(function () {
      bootstrapToast.hide();
    }, 3000); // 3000 milliseconds = 3 seconds
  }
  
  function showsussce(message) {
    var toast = document.querySelector(".toast").cloneNode(true);
    var toastBody = toast.querySelector(".toast-body");
    toastBody.textContent = message;
  
    var toastContainer = document.querySelector(".toast-container");
    
      toast.classList.add("bg-success", "text-white"); // Thêm lớp bg-danger và text-white
    
    
    toastContainer.appendChild(toast);
  
    var bootstrapToast = new bootstrap.Toast(toast);
    bootstrapToast.show();
  
    setTimeout(function () {
      bootstrapToast.hide();
    }, 3000); // 3000 milliseconds = 3 seconds
  }
  
  document
    .getElementById("addToCartButton")
    .addEventListener("click", function () {});
  
    