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

  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Year');
  data.addColumn('number', 'Sales');
  data.addColumn('number', 'Expenses');
  data.addColumn('number', 'Profit');

  apiData.forEach(item => {
    data.addRow([item.year, item.sales, item.expenses, item.profit]);
  });

  var options = {
    chart: {
      title: 'Company Performance',
      subtitle: 'Sales, Expenses, and Profit'
    },
    bars: 'vertical',
    vAxis: {format: 'decimal'},
    height: 400,
    colors: ['#1b9e77', '#d95f02', '#7570b3']
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
