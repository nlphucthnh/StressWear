// ----------- DON - JQUERY HTML ----------- //
$("#form-promotion-tab").click(function (e) {
    $("#form-promotion-tab").css("border", "1px solid var(--gray_300)");
    $("#form-promotion-tab").css("background-color", "var(--gray_50)");
    $("#nhomLoai-tab").css("border", "none");
    $("#nhomLoai-tab").css("background-color", "white");
});

$("#nhomLoai-tab").click(function (e) {
    $("#nhomLoai-tab").css("border", "1px solid var(--gray_300)");
    $("#nhomLoai-tab").css("background-color", "var(--gray_50)");
    $("#form-promotion-tab").css("border", "none");
    $("#form-promotion-tab").css("background-color", "white");
});

// ----------- API - SPA ----------- //
const API_NHOMLOAI = "http://localhost:8080/api/nhomloai";
const API_KHUYENMAI = "http://localhost:8080/api/khuyenmai";
const app = angular.module("app", []);
app.controller("ctrl-nl", function ($scope, $http) {
    $scope.form_nl = {};
    $scope.list_nl = [];

    $scope.load_data_nl = function () {
        var url = `${API_NHOMLOAI}/paging`;
        $http.get(url).then((result) => {
            $scope.list_nl = result.data.content;
            $scope.infor_nl = result.data;
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.paging_data_nl = function (numberPage) {
        var url = `${API_NHOMLOAI}/paging?page=${numberPage}`;
        $http.get(url).then((result) => {
            $scope.list_nl = result.data.content;
            $scope.infor_nl = result.data;
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.edit_nl = function (nhomLoai) {
        $("#btn-update-nl").css("pointer-events", "all");
        $("#btn-update-nl").css("background-color", "white");

        $("#btn-add-nl").css("pointer-events", "none");
        $("#btn-add-nl").css("background-color", "var(--gray_500)");
        nhomLoai.ngayTao = new Date(nhomLoai.ngayTao);
        $scope.form_nl = angular.copy(nhomLoai);
    }

    $scope.update_nl = function (nhomLoai) {
        var item = angular.copy(nhomLoai);
        var url = `${API_NHOMLOAI}/${nhomLoai.idNhomLoai}`;
        $http.put(url, item).then((result) => {
            var index = $scope.list_nl.findIndex(item => item.idNhomLoai === result.data.idNhomLoai);
            $scope.list_nl[index] = result.data;
            alert("Cập nhật nhóm loại thành công");
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.create_nl = function () {
        var item = angular.copy($scope.form_nl);
        var url = `${API_NHOMLOAI}`;
        $http.post(url, item).then((result) => {
            $scope.list_nl.push(result.data);
            alert("Thêm nhóm loại thành công");
            $scope.reset_nl();
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.delete_nl = function (idNhomLoai) {
        var url = `${API_NHOMLOAI}/${idNhomLoai}`;
        $http.delete(url).then((result) => {
            alert("Xóa nhóm loại thành công");
            var index = $scope.list_nl.findIndex(item => item.idNhomLoai === result.data.idNhomLoai);
            $scope.list_nl.splice(index, 1);
            $scope.reset_nl();
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.reset_nl = function () {
        $scope.form_nl = {
            idNhomLoai: "",
            moTaNhomLoai: "",
            ngayTao: new Date(),
            tenNhomLoai: ""
        };
        $("#btn-update-nl").css("pointer-events", "none");
        $("#btn-update-nl").css("background-color", "var(--gray_100)");

        $("#btn-add-nl").css("pointer-events", "all");
        $("#btn-add-nl").css("background-color", "var(--gray_900)");
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

    $scope.reset_nl();
    $scope.load_data_nl();
});

app.controller("ctrl-km", function ($scope, $http) {
    $scope.form_km = {};
    $scope.list_km = [];

    $scope.convert_date = function (date) {
        if ((typeof date) == "string") {
            return date;
        } else {
            let day = date.getDate();
            let month = date.getMonth();
            let year = date.getFullYear();
            return `${year}-${month}-${day}`;
        }
    }

    $scope.load_data_km = function () {
        var url = `${API_KHUYENMAI}/paging`;
        $http.get(url).then((result) => {
            $scope.list_km = result.data.content;
            $scope.infor_km = result.data;
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.paging_data_km = function (numberPage) {
        var url = `${API_KHUYENMAI}/paging?page=${numberPage}`;
        $http.get(url).then((result) => {
            $scope.list_km = result.data.content;
            $scope.infor_km = result.data;
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.edit_km = function (khuyenMai) {
        $("#btn-update-km").css("pointer-events", "all");
        $("#btn-update-km").css("background-color", "white");

        $("#btn-add-km").css("pointer-events", "none");
        $("#btn-add-km").css("background-color", "var(--gray_500)");
        khuyenMai.ngayBatDau = new Date(khuyenMai.ngayBatDau);
        khuyenMai.ngayKetThuc = new Date(khuyenMai.ngayKetThuc);
        $scope.form_km = angular.copy(khuyenMai);
    }

    $scope.update_km = function (khuyenMai) {
        var item = angular.copy(khuyenMai);
        var url = `${API_KHUYENMAI}/${khuyenMai.idKhuyenMai}`;
        $http.put(url, item).then((result) => {
            var index = $scope.list_km.findIndex(item => item.idKhuyenMai === result.data.idKhuyenMai);
            $scope.list_km[index] = result.data;
            alert("Cập nhật khuyến mãi thành công");
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.create_km = function () {
        var item = angular.copy($scope.form_km);
        item.idKhuyenMai = makeid(10);
        var url = `${API_KHUYENMAI}`;
        $http.post(url, item).then((result) => {
            $scope.list_km.push(result.data);
            alert("Thêm khuyến mãi thành công");
            $scope.reset_km();
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.delete_km = function (idKhuyenMai) {
        var url = `${API_KHUYENMAI}/${idKhuyenMai}`;
        $http.delete(url).then((result) => {
            alert("Xóa khuyến mãi thành công");
            var index = $scope.list_km.findIndex(item => item.idKhuyenMai === result.data.idKhuyenMai);
            $scope.list_km.splice(index, 1);
            $scope.reset_km();
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }

    $scope.reset_km = function () {
        $scope.form_km = {
            idKhuyenMai: "",
            tenKhuyenMai: "",
            ngayBatDau: new Date(),
            ngayKetThuc: new Date(),
            phanTramKhuyenMai: 0.0
        };
        $("#btn-update-km").css("pointer-events", "none");
        $("#btn-update-km").css("background-color", "var(--gray_100)");

        $("#btn-add-km").css("pointer-events", "all");
        $("#btn-add-km").css("background-color", "var(--gray_900)");
    };

    $scope.convert_date = function (date) {
        if ((typeof date) == "string") {
            if (date.length > 10) {
                var dateNew = new Date(date);
                let day = dateNew.getDate();
                let month = dateNew.getMonth();
                let year = dateNew.getFullYear();
                return `${year}-0${month + 1}-${day}`;
            }
            return date;
        } else if((typeof date) == "object") {
            let day = date.getDate();
            let month = date.getMonth();
            let year = date.getFullYear();
            return `${year}-0${month + 1}-${day}`;
        }
    }

    function makeid(length) {
        let result = '';
        const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        const charactersLength = characters.length;
        let counter = 0;
        while (counter < length) {
          result += characters.charAt(Math.floor(Math.random() * charactersLength));
          counter += 1;
        }
        return result;
    }
    $scope.reset_km();
    $scope.load_data_km();
});