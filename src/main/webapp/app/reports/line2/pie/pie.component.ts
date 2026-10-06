import { Component, Input, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { Line1Service } from 'app/reports/line1.service';

@Component({
  selector: 'jhi-pie',
  templateUrl: './pie.component.html',
  styleUrls: ['./pie.component.scss']
})
export class PieComponent implements OnInit {
  @Input() dataKeHoachThucHien: any;
  Highcharts = Highcharts;
  chartPie: {};

  constructor(private service: Line1Service) {}

  getKeHoachThucHien() {
    this.service.getThongTinChung().subscribe(res => {
      this.dataKeHoachThucHien = res;
      this.drawChart();
    });
  }

  drawChart() {
    this.chartPie = {
      chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
      },
      title: {
        text: null
      },
      subtitle: {
        text: null
      },
      tooltip: {
        split: false,
        pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
      },
      exporting: {
        enabled: true
      },
      credits: {
        enabled: false
      },
      legend: {
        symbolRadius: 0
      },
      accessibility: {
        point: {
          valueSuffix: '%'
        }
      },
      plotOptions: {
        pie: {
          allowPointSelect: true,
          cursor: 'pointer',
          showInLegend: true,
          dataLabels: {
            enabled: true,
            format: '{point.percentage:.2f}%',
            distance: -50,
            filter: {
              property: 'percentage',
              operator: '>',
              value: 4
            }
          }
        }
      },
      series: [
        {
          name: 'Status',
          colorByPoint: true,
          data: [
            { name: 'Thực hiện', y: this.dataKeHoachThucHien.soLuongMayRa_Phantram, color: '#0c65dc' },
            { name: 'Còn lại', y: this.dataKeHoachThucHien.soLuongConLai_Phantram, color: '#f1a007' }
          ]
        }
      ]
    };
  }

  ngOnInit() {
    this.getKeHoachThucHien();
  }
}
