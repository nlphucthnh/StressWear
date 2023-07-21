// ------------------DOM - JQUERY------------------//
$("#list-order-tab").click(function (e) {
    $("#list-order-tab").css("border", "1px solid var(--gray_300)");
    $("#list-order-tab").css("background-color", "var(--gray_50)");
    $("#form-order-tab").css("border", "none");
    $("#form-order-tab").css("background-color", "white");
});

$("#form-order-tab").click(function (e) {
    $("#form-order-tab").css("border", "1px solid var(--gray_300)");
    $("#form-order-tab").css("background-color", "var(--gray_50)");
    $("#list-order-tab").css("border", "none");
    $("#list-order-tab").css("background-color", "white");
});

$(".filte-sort").click(function (e) {
    $(".filte-sort").show();
    $("#" + $(this).attr("id")).hide();
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

