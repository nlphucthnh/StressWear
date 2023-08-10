 // Đợi tài liệu HTML tải xong
 document.addEventListener("DOMContentLoaded", function () {
    // Lấy thông báo và nút bằng cách sử dụng ID
    var notification = document.getElementById("notification");
    var continueBtn = document.getElementById("continueBtn");

    // Kiểm tra xem thông báo đã xuất hiện hay chưa
    if (notification.innerHTML.trim() !== "") {
      // Hiển thị nút "Tiếp tục thanh toán"
      continueBtn.style.display = "block"; // hoặc "inline-block", "flex", tùy theo loại display bạn muốn
    }
  });
  var citis = document.getElementById("city");
  var districts = document.getElementById("district");
  var wards = document.getElementById("ward");
  var Parameter = {
    url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
    method: "GET",
    responseType: "application/json",
  };
  var promise = axios(Parameter);
  promise.then(function (result) {
    renderCity(result.data);
  });

  function renderCity(data) {
    for (const x of data) {
      citis.options[citis.options.length] = new Option(x.Name, x.Name);
    }
    citis.onchange = function () {
      district.length = 1;
      ward.length = 1;
      if (this.value != "") {
        const result = data.filter(n => n.Name === this.value);

        for (const k of result[0].Districts) {
          district.options[district.options.length] = new Option(k.Name, k.Name);
        }
      }
    };
    district.onchange = function () {
      ward.length = 1;
      const dataCity = data.filter((n) => n.Name === citis.value);
      if (this.value != "") {
        const dataWards = dataCity[0].Districts.filter(n => n.Name === this.value)[0].Wards;

        for (const w of dataWards) {
          wards.options[wards.options.length] = new Option(w.Name, w.Name);
        }
      }
    };
  }