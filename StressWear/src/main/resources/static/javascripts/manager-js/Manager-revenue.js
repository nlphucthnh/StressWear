google.charts.load('current', { 'packages': ['bar'] });
google.charts.setOnLoadCallback(drawStuff);

async function fetchData(numberOfRows) {
  try {
    const response = await fetch('http://localhost:8080/api/donhangchitiet/thongkesanpham');
    const data = await response.json();
    return data.slice(0, numberOfRows); // Assuming the API response is an array of objects
  } catch (error) {
    console.error('Error fetching data:', error);
    return [];
  }
}

async function drawStuff() {
  const numberOfRowsToShow = 5; // Set the number of rows you want to show on the chart
  const apiData = await fetchData(numberOfRowsToShow);

  const chartData = [['Tên sản phẩm', 'Số lượng', 'Ngày mua', 'Tổng tiền']];
  apiData.forEach(item => {
    chartData.push([item.sanPham.tenSanPham, item.soLuong, item.ngayMuaSanPham, item.soLuong * item.sanPham.giaSanPham]);
  });

  var data = new google.visualization.arrayToDataTable(chartData);

  var options = {
    width: 800,
    legend: { position: 'none' },
    chart: {
      title: 'Thống kê sản phẩm',
      subtitle: 'Đã bán'
    },
    axes: {
      x: {
        0: { side: 'top', label: '' } // Top x-axis.
      }
    },
    bar: { groupWidth: "100%" }
  };

  var chart = new google.charts.Bar(document.getElementById('top_x_div'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}


    const API_DONHANG= "http://localhost:8080/api/donhangchitiet";
    const app = angular.module("app", []);
    app.controller("ctrl-revenue", function ($scope, $http) {

      $scope.list_thongkesanpham = [];

      $scope.load_data_thongkesanpham = function (numberPage) {
        var url = `${API_DONHANG}/paging?page=${numberPage}`;
        $http.get(url).then((result) => {
          $scope.list_thongkesanpham = result.data.content;
          $scope.infor_tksp = result.data
          console.log(result.data);
        }).catch((err) => {
          console.log(err);
        });
      }
      $scope.load_data_thongkesanpham(0);
    })