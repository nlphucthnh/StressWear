// ------------------ Load image backgourd ------------------ //

$("#upload-bg-pen").click(function (e) {
    $("#upload_bg").trigger("click");

});
$("#upload_bg").change(function () {
    $("#spinner .spinner-border").show();
    $("#upload-bg-pen").css("pointer-events", "none");
    var img_bg_old = $(".background-profile-img").attr('src');
    const ref = firebase.storage().ref();
    const file = document.querySelector("#upload_bg").files[0];
    const metadata = {
        contentType: 'image/jpeg'
    };
    const nameFile = $("#upload_bg").val().split(/(\\|\/)/g).pop();
    const uploadIMG = ref.child(nameFile).put(file, metadata);
    uploadIMG
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
const API_TAIKHOAN = "http://localhost:8080/api/taikhoan";
const app = angular.module("app", []);
app.controller("ctrl-changePass", function ($scope, $http) {
    $scope.form_changepass = {
        matKhau : "",
        matKhauMoi : "",
        xacNhan : "",
    }
    var tenDangNhap = $("#username_profile").val();
    $scope.update_pass = function () {
        var url = `${API_TAIKHOAN}/${tenDangNhap}`;
        $http.get(url).then((result) => {
            console.log(result.data);
            if ((result.data.matKhau.trim() == $scope.form_changepass.matKhau) && ($scope.form_changepass.matKhauMoi == $scope.form_changepass.xacNhan)) {
                $scope.form_changepass = {
                    tenDangNhap: result.data.tenDangNhap.trim(),
                    matKhau: $scope.form_changepass.matKhauMoi,
                    email: result.data.email.trim(),
                    trangThai: true
                }
                var item = angular.copy($scope.form_changepass);
                $http.put(url,item).then((result) => {
                    alert("Đổi mật khẩu thành công")
                }).catch((err) => {
                    console.log("ERROR", err);
                });
            }else {
                alert("Đổi mật khẩu thất bại");  
            }
        }).catch((err) => {
            console.log("ERROR", err);
        });
    }
})