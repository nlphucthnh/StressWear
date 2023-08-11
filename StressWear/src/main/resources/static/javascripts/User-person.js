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