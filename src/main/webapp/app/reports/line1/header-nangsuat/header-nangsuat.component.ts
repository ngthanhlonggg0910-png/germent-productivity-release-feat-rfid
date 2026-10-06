import { Component, OnInit } from '@angular/core';
import { Line1Service } from '../../line1.service';

@Component({
  selector: 'jhi-header-nangsuat',
  templateUrl: './header-nangsuat.component.html',
  styleUrls: ['../line1.component.scss']
})
export class HeaderNangsuatComponent implements OnInit {
  public dataKeHoachThucHien: any = [];
  currentDay = '';
  dayNow = '';
  monthNow = '';
  monthNowNum = '';
  currentTime = '';
  monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  monthNamesNum = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];

  constructor(private service: Line1Service) {}

  ngOnInit() {
    this.service.getThongTinChung().subscribe(res => {
      if (res) {
        this.dataKeHoachThucHien = res;
        this.currentDay = this.getCurrentDate(new Date());
        this.currentTime = this.getCurrentTime(new Date());
        this.dayNow = this.getCurrentDay(new Date());
        this.monthNow = this.getCurrentMonth(new Date());
        this.monthNowNum = this.getCurrentMonthNum(new Date());
      }
    });
  }

  getCurrentDate(today: Date) {
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    return dd + '/' + mm;
  }

  getCurrentDay(today: Date) {
    return String(today.getDate()).padStart(2, '0');
  }

  getCurrentMonth(today: Date) {
    return this.monthNames[today.getMonth()];
  }
  getCurrentMonthNum(today: Date) {
    return this.monthNamesNum[today.getMonth()];
  }
  getCurrentTime(today: Date) {
    const hours = today.getHours();
    const minutes = today.getMinutes();
    const minutesString = minutes < 10 ? '0' + minutes : minutes;
    return hours + ' : ' + minutesString;
  }
}
