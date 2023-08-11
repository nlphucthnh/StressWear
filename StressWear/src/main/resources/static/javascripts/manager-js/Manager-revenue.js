google.charts.load('current', {
  packages: ['bar']
});
google.charts.setOnLoadCallback(drawStuff);

async function fetchData(numberOfRows) {
  try {
    const response = await fetch('http://localhost:8080/api/donhangchitiet/thongkesanpham');
    const data = await response.json();
    return data.slice(0, numberOfRows);
  } catch (error) {
    console.error('Error fetching data:', error);
    return [];
  }
}

async function drawStuff() {
  try {
    const numberOfRowsToShow = 5;
    const apiData = await fetchData(numberOfRowsToShow);

    const chartData = apiData.map(item => [
      item.sanPham.tenSanPham,
      item.soLuong,
      item.ngayMuaSanPham,
      item.soLuong * item.sanPham.giaSanPham
    ]);

    const data = new google.visualization.arrayToDataTable([
      ['Tên sản phẩm', 'Số lượng', 'Ngày mua', 'Tổng tiền'],
      ...chartData
    ]);

    const options = {
      width: 800,
      legend: 'none',
      chart: {
        title: 'Thống kê sản phẩm',
        subtitle: 'Đã bán'
      },
      axes: {
        x: {
          0: {
            side: 'top',
            label: ''
          }
        }
      },
      bar: {
        groupWidth: '100%'
      }
    };

    const chart = new google.charts.Bar(document.getElementById('top_x_div'));
    chart.draw(data, google.charts.Bar.convertOptions(options));
  } catch (error) {
    console.error('Error drawing stuff:', error);
  }
}


// Đảm bảo thư viện Google Charts đã được load
google.charts.load('current', { packages: ['bar'] });
google.charts.setOnLoadCallback(drawStuff);



const API_DONHANG = "http://localhost:8080/api/donhangchitiet";
const app = angular.module("app", []);
app.controller("ctrl-revenue", function ($scope, $http) {
  $scope.list_thongkesanpham = [];
  $scope.list_thongkesanpham1 = [];


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

  $scope.load_data_thongkesanpham1 = function () {
    var url = `${API_DONHANG}`;
    $scope.totalAmount = 0; // Khai báo và khởi tạo biến totalAmount
    $http.get(url).then((result) => {
      $scope.list_thongkesanpham1 = result.data;
      $scope.infor_tksp1 = result.data
      result.data.forEach(commentitem => {
        $scope.totalAmount += commentitem.soLuong * commentitem.sanPhamDHCT.giaSanPham;
      });
      console.log(result.data);
    }).catch((err) => {
      console.log(err);
    });
  }

  //Search
  $scope.searchKeyword = ''; // Khai báo và khởi tạo biến searchKeyword

  $scope.searchProducts = function () {
    var url = `${API_DONHANG}/paging?searchKeyword=${$scope.searchKeyword}`;
    $http.get(url).then(result => {
      $scope.apiData = result.data.content;
      updateChart($scope.apiData);
    });
  };



  $scope.load_data_thongkesanpham(0);
  $scope.load_data_thongkesanpham1()
})