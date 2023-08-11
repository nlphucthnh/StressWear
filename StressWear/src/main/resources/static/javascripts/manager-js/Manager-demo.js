google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawChart);

async function fetchData() {
  try {
    const response = await fetch('http://localhost:8080/api/donhangchitiet/thongkesanpham'); 
    const apiData = await response.json();
    return apiData;
  } catch (error) {
    console.error('Error fetching data:', error);
    return [];
  }
}
async function drawChart() {
  const apiData = await fetchData();

  // Group data by year and calculate total quantity and total amount for each year
  const groupedData = apiData.reduce((groups, item) => {
    const year = new Date(item.ngayMuaSanPham).getFullYear();
    if (!groups[year]) {
      groups[year] = {
        year: year,
        totalAmount: 0
      };
    }
    groups[year].totalAmount += item.soLuong * item.sanPham.giaSanPham;
    return groups;
  }, {});

  // Convert grouped data to an array of arrays for the chart
  const chartData = Object.values(groupedData).map(group => {
    return [group.year.toString(), group.totalAmount];
  });

  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Year');
  data.addColumn('number', 'Tổng tiền');

  data.addRows(chartData);

  var options = {
    chart: {
      title: 'Thống kê theo năm',
      subtitle: 'Tổng số lượng và tổng số tiền theo năm'
    },
    bars: 'vertical',
    vAxis: {format: 'decimal'},
    height: 400,
    colors: ['#1b9e77', '#d95f02']
  };

  var chart = new google.charts.Bar(document.getElementById('chart_div'));

  chart.draw(data, google.charts.Bar.convertOptions(options));

  var btns = document.getElementById('btn-group');

  btns.onclick = function (e) {
    if (e.target.tagName === 'BUTTON') {
      options.vAxis.format = e.target.id === 'none' ? '' : e.target.id;
      chart.draw(data, google.charts.Bar.convertOptions(options));
    }
  };
}
