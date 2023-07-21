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
    if(!$(this).children()[1].checked){
      $(this).children()[1].checked = true;
    }else {
      $(this).children()[1].checked = false;
    }
});

// -------------------------- FORMAT MONYE -------------------------- //
$("input[data-type='currency']").on({
    keyup: function() {
      formatCurrency($(this));
    },
    blur: function() { 
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
  







// $("#fileUpload").change(function () {
//     $("#btn-loading").show();
//     $("#uploadImg").hide();
//     const ref = firebase.storage().ref();
//     const file = document.querySelector("#fileUpload").files[0];
//     const metadata = {
//         contentType: 'image/jpeg'
//     };
//     const nameFile = $("#fileUpload").val().split(/(\\|\/)/g).pop();
//     const uploadIMG = ref.child(nameFile).put(file, metadata);
//     uploadIMG
//         .then(snapshot => snapshot.ref.getDownloadURL())
//         .then(url => {
//             console.log(url);
//             $("#btn-loading").hide();
//             $("#uploadImg").show();
//             addHinhAnh(url, file.size, nameFile);
//             // console.log(file.size);
//         })
//         .catch(console.error)
// });

