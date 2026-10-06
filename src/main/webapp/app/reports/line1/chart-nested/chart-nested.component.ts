import { Component, Input, OnInit } from '@angular/core';
// import { debug } from 'console';
import * as Highcharts from 'highcharts';
import { Line1Service } from 'app/reports/line1.service';

@Component({
  selector: 'jhi-chart-nested',
  templateUrl: './chart-nested.component.html',
  styleUrls: ['./chart-nested.component.scss']
})
export class ChartNestedComponent implements OnInit {
  @Input() dataKeHoachThucHien: any;
  Highcharts = Highcharts;
  chartStackColumn: {};
  constructor(private service: Line1Service) {}

  getKeHoachThucHien() {
    this.service.getThongTinChung().subscribe(res => {
      this.dataKeHoachThucHien = res;
      this.drawChart();
    });
    
  }

  drawChart() {
    this.chartStackColumn = {
      chart: {
        type: 'column'
      },
      title: {
        text: null
      },
      credits: {
        enabled: false
      },
      tooltip: {
        headerFormat: '<b>{point.x}</b><br/>',
        pointFormat: '{series.name}: {point.y}%<br/>Total: {point.stackTotal}%'
      },
      xAxis: {
        categories: ['']
      },
      yAxis: {
        min: 0,
        max: 100,
        title: {
          text: this.dataKeHoachThucHien.caption
        },
        stackLabels: {
          enabled: false,
          style: {
              fontWeight: 'bold',
              color: ( // theme
                  Highcharts.defaultOptions.title.style &&
                  Highcharts.defaultOptions.title.style.color
              ) || 'gray'
          }
        }
      },
      plotOptions: {
        column: {
          grouping: false,
          shadow: false,
          showInLegend: false,
          stacking: 'normal',
          dataLabels: {
            enabled: true,
            formatter() {
              return this.y + '%';
            },
            style: {
              fontWeight: 'bold'
            },
            padding: -30
          }
        }
      },
      series: [
        {
          name: 'Kế hoạch',
          data: [this.dataKeHoachThucHien.kehoach_phantram],
          color: '#f1a007'
        },
        {
          name: 'Thực hiện',
          data: [this.dataKeHoachThucHien.thuchien_phantram],
          color: '#0c65dc'
        }
      ]
    };
  }
  ngOnInit() {
    this.getKeHoachThucHien();
  }
}
