import { Component, Input, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { Line1Service } from 'app/reports/line1.service';

@Component({
  selector: 'jhi-tab3-chart',
  templateUrl: './tab3-chart.component.html',
  styleUrls: ['./tab3-chart.component.scss']
})
export class Tab3ChartComponent implements OnInit {
  @Input() dataChiTietLoi: any;

  chartTab3: {};
  Highcharts = Highcharts;
  chartData = [];

  constructor(private service: Line1Service) {}

  getChiTietLoi() {
    this.service.getTopLoiChiTietLoi().subscribe(res => {
      this.dataChiTietLoi = res;
      this.buildChartData(this.dataChiTietLoi);
      this.drawChart();
    });
  }
  buildChartData(data) {
    this.chartData = [];
    data.topLois.forEach(element => {
      this.chartData.push(this.buildChartObject(element));
    });
  }
  buildChartObject(element) {
    const obj = {
      name: element.caption,
      color: this.service.computeColor(element.mau),
      data: [
        {
          name: element.caption,
          color: this.service.computeColor(element.mau),
          y: element.soLuong
        }
      ]
    };
    return obj;
  }

  drawChart() {
    this.chartTab3 = {
      chart: {
        type: 'column',
        options3d: {
          enabled: true,
          alpha: 15,
          beta: 15,
          depth: 50,
          viewDistance: 25
        }
      },
      title: {
        text: null
      },
      subtitle: {
        text: null
      },
      accessibility: {
        announceNewData: {
          enabled: true
        }
      },
      xAxis: {
        type: 'category'
      },
      yAxis: {
        title: {
          text: null
        }
      },
      legend: {
        symbolRadius: 0
      },
      plotOptions: {
        column: {
          // pointWidth: 50,
          grouping: false,
          dataLabels: {
            enabled: true,
            legend: {
              symbolRadius: 0
            },
            format: '<span style="color:{series.color}"></span><b>{point.y}</b><br/>',
            style: {
              fontWeight: '600'
            }
          }
        },
        series: {
          borderWidth: 0,
          dataLabels: {
            enabled: true,
            format: null
          }
        }
      },
      credits: {
        enabled: false
      },
      tooltip: {
        headerFormat: null,
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
      },

      series: this.chartData
    };
  }
  ngOnInit() {
    this.getChiTietLoi();
  }
}
