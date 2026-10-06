import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { Line1Service } from 'app/reports/line1.service';

@Component({
  selector: 'jhi-chart-bar',
  templateUrl: './chart-bar.component.html',
  styleUrls: ['./chart-bar.component.scss']
})
export class ChartBarComponent implements OnInit {
  Highcharts = Highcharts;
  chartCombine: {};
  dataSource: any = [];
  categories: any = [];
  tiLeLoi: any = [];
  mucTieu: any = [];
  dat: any = [];
  chuaDat: any = [];

  constructor(private service: Line1Service) {}

  getDataTheoGioHienTai() {
    this.service.getNangSuatTheoGio().subscribe(res => {
      this.dataSource = res;

      this.getCaption(this.dataSource);
      this.getValueTiLeLoi(this.dataSource);
      this.getValueMucTieu(this.dataSource);
      this.getValueDat(this.dataSource);
      this.drawChart();
    });
  }

  drawChart() {
    this.chartCombine = {
      chart: {
        height: '200px' // 16:9 ratio
      },
      title: {
        text: null
      },
      xAxis: {
        categories: this.categories
      },
      yAxis: {
        title: {
          text: 'Target Output'
        }
      },
      legend: {
        symbolRadius: 0,
        borderColor: '#0c65dc'
      },
      labels: {
        items: [
          {
            style: {
              left: '50px',
              top: '18px',
              color:
                // theme
                (Highcharts.defaultOptions.title.style && Highcharts.defaultOptions.title.style.color) || 'red'
            }
          }
        ]
      },
      exporting: {
        enabled: true
      },
      credits: {
        enabled: false
      },
      plotOptions: {
        spline: {
          dataLabels: {
            enabled: true,
            align: 'right',
            inside: false,
            format: '<span style="color:{series.color}"></span><b>{point.y}</b>{point.percentage:.1f}%<br/>',
            style: {
              fontWeight: 'bold'
            }
          }
        },
        column: {
          // pointWidth: 50,
          grouping: false,
          dataLabels: {
            enabled: true,
            inside: true,
            align: 'right',
            legend: {
              symbolRadius: 0
            },
            format: '<span style="color:{series.color}"></span><b>{point.y}</b><br/>',
            style: {
              fontWeight: '600',
              fontSize: '16px',
              color: '#000'
            }
          }
        }
      },
      series: [
        {
          type: 'column',
          name: 'Đạt',
          dataLabels: {
            style: {
              color: '#000'
            }
          },
          color: '#0c65dc',
          data: this.dat,
          legend: {
            symbolRadius: 0
          }
        },
        {
          type: 'column',
          name: 'Chưa đạt',
          dataLabels: {
            style: {
              color: '#000'
            }
          },
          color: '#f1a007',
          data: this.chuaDat,
          legend: {
            symbolRadius: 0
          }
        },
        {
          type: 'spline',
          name: 'Tỉ lệ lỗi',
          color: '#ffcd52',
          tooltip: {
            split: false,
            pointFormat: '<b>{point.y}</b> {point.percentage:.1f}%'
          },
          data: this.tiLeLoi,
          plotOptions: {
            spline: {}
          },
          marker: {
            lineWidth: 2,
            lineColor: Highcharts.getOptions().colors[3],
            fillColor: '#ece03c',
            symbol: 'triangle',
            format: '<span style="color:{series.color}"></span><b>{point.y}</b> {point.percentage:.1f}%<br/>'
          }
        },
        {
          type: 'line',
          name: 'Mục tiêu',
          data: this.mucTieu,
          center: [100, 80],
          size: 100,
          color: '#8c9198',
          dataLabels: {
            enabled: true
          },
          marker: {
            lineWidth: 2,
            lineColor: Highcharts.getOptions().colors[3],
            fillColor: '#8c9198',
            format: '<span style="color:{series.color}"></span><b>{point.y}</b>'
          }
        }
      ]
    };
  }

  ngOnInit() {
    this.getDataTheoGioHienTai();
  }
  getCaption(dataSource: any) {
    this.categories = [];
    for (let i = 0; i < dataSource.length; i++) {
      this.categories.push(dataSource[i].caption);
    }
  }
  getValueTiLeLoi(dataSource: any) {
    this.tiLeLoi = [];
    for (let i = 0; i < dataSource.length; i++) {
      this.tiLeLoi.push({ x: i, y: dataSource[i].valueTiLeLoi });
    }
  }
  getValueMucTieu(dataSource: any) {
    this.mucTieu = [];
    for (let i = 0; i < dataSource.length; i++) {
      this.mucTieu.push({ x: i, y: dataSource[i].valueMucTieu });
    }
  }
  getValueDat(dataSource: any) {
    this.chuaDat = [];
    this.dat = [];
    for (let i = 0; i < dataSource.length; i++) {
      if (dataSource[i].valueDat == null) {
        dataSource[i].valueDat = 0;
      }
      if (dataSource[i].valueMucTieu == null) {
        dataSource[i].valueMucTieu = 0;
      }
      if (dataSource[i].valueDat < dataSource[i].valueMucTieu) {
        this.chuaDat.push({ x: i, y: dataSource[i].valueDat });
      } else {
        this.dat.push({ x: i, y: dataSource[i].valueDat });
      }
    }
  }
}
