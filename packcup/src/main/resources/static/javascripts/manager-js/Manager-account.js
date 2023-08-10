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