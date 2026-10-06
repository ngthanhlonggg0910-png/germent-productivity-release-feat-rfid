import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit, Injectable, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Line1Service } from '../line1.service';
import { SERVER_API_URL } from 'app/app.constants';
import { interval } from 'rxjs';
/*
import { NgImageSliderComponent } from 'ng-image-slider';
*/

@Component({
  selector: 'jhi-line1',
  templateUrl: './line1.component.html',
  styleUrls: ['./line1.component.scss']
})
@Injectable({
  providedIn: 'root'
})
export class Line1Component implements OnInit, AfterViewInit {
  elem: any;
  toggleFullScreen = true;
  public dataKeHoachThucHien: any = [];
  public dataChiTietLoi: any = [];
  public nsLdNgay: any = [];
  public videos: any = [];
  currentDay = '';
  dayNow = '';
  monthNow = '';
  monthNowNum = '';
  currentTime = '';
  dayNgayXuatDau = '';
  monthNgayXuatDau = '';
  dayNgayXuatHomNay = '';
  monthNgayXuatHomNay = '';
  sourceVideo = '';
  percentActual = 0;
  percentTakt = 0;
  playTimes: any = [];
  imageObject: any = [];
  isShowVideoPlayer = false;
  @ViewChild('myCarousel', { static: false }) carouselEl: ElementRef;
  @ViewChild('carousel carousel-inner', { static: false }) carouselInnerEl: ElementRef;
  start = 10000;
  timeFunction: any;
  timeSlide1 = 10000;
  timeSlide2 = 1000;
  timeSlide3 = 20000;
  monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  monthNamesNum = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];

  /* nang suat theo gio */
  mucTieu: any = [];
  dat: any = [];
  chuaDat: any = [];
  suaDat: any = [];
  dataSource: any = [];
  categories: any = [];
  colorThucHienGreen = '';
  colorThucHienRed = 'thuchien_red';
  tongDat = 0;
  tongSuaDat = 0;
  tongLoi = 0;

  percentFTT = 0; // (( tongthuchien - tongloi ) / tongthuchien)*100
  percentFTTClass = 'normal';
  debug = '';

  refinalTheoGio: any = [];

  constructor(private service: Line1Service, private route: ActivatedRoute, @Inject(DOCUMENT) private document: any) {}

  getDataTheoGioHienTai() {
    this.service.getNangSuatTheoGio().subscribe(res => {
      this.dataSource = res;
      this.getValueMucTieu(this.dataSource);
      this.getValueDat(this.dataSource);
      this.getCaption(this.dataSource);
    });
  }

  ngOnInit() {
    this.elem = document.documentElement;
    const id = this.route.snapshot.params.id;
    this.currentDay = this.getCurrentDate(new Date());
    this.currentTime = this.getCurrentTime(new Date());
    this.dayNow = this.getCurrentDay(new Date());
    this.monthNow = this.getCurrentMonth(new Date());
    this.monthNowNum = this.getCurrentMonthNum(new Date());
    this.service.fecthAllData(id);
    this.getDataTheoGioHienTai();
    this.service.getVideo().subscribe(res => {
      if (res) {
        if (res.length > 0) {
          for (let j = 0; j < res.length; j++) {
            if (res[j].name.indexOf(',') > 0) {
              this.buildSlider(res[j].name.split(','));
              // this.sourceVideo = SERVER_API_URL + "mmimages/video/" + res[0].name;
              this.buildPlayTime(res[j].timePlay.split(','));
              this.isShowVideoPlayer = this.isShowVideo();
              break;
            }
          }
        }
      }
    });
    this.service.getRefinalTheoGio().subscribe(res => {
      if (res) {
        for (let j = 0; j < res.length; j++) {
          if (this.refinalTheoGio.length < 9) {
            if (Number(res[j].soLuongLoi) >= 1) this.refinalTheoGio.push({ caption: res[j].caption, color: 'triangle-red' });
            else if (Number(res[j].soLuongLoi) === 0) this.refinalTheoGio.push({ caption: res[j].caption, color: 'triangle-green' });
            else this.refinalTheoGio.push({ caption: res[j].caption, color: 'triangle-white' });
          }
        }
      }
    });
    this.intervalCheckShowVideo();
    this.service.getThongTinChung().subscribe(res => {
      if (res) {
        this.dataKeHoachThucHien = res;
        this.currentDay = this.getCurrentDate(new Date());
        this.currentTime = this.getCurrentTime(new Date());
        this.dayNow = this.getCurrentDay(new Date());
        this.monthNow = this.getCurrentMonth(new Date());
        this.monthNowNum = this.getCurrentMonthNum(new Date());
        if (Number(res['soLuongMayRa']) === Number(res['soLuongPhanBo'])) {
          this.colorThucHienGreen = 'thuchien_green';
          this.colorThucHienRed = '';
        } else {
          this.colorThucHienGreen = '';
          this.colorThucHienRed = 'thuchien_red';
        }
        if (res['kehoach'] !== 0) {
          this.percentActual = (Number(res['thuchien']) / Number(res['kehoach'])) * 100;
        }
        if (res['talktime_thuchien'] !== 0) {
          this.percentTakt = (Number(res['talktime_kehoach']) / Number(res['talktime_thuchien'])) * 100;
        }
        if (res['ngaySanXuatDauTien']) {
          this.updateNgaySanXuatDauTien(res['ngaySanXuatDauTien']);
        }
        if (res['ngaySanXuatHomNay']) {
          this.updateNgaySanXuatHomNay(res['ngaySanXuatHomNay']);
        }
        this.tongLoi = 0;
        for (let i = 0; i < this.chuaDat.length; i++) {
          this.tongLoi += Number(this.chuaDat[i].value);
        }

        if (Number(res['thuchien']) > 0) {
          //this.percentFTT = ((Number(res['thuchien']) - tongloi) / Number(res['thuchien'])) * 100;
          this.debug = this.tongDat + ' - ' + this.tongLoi;
          this.percentFTT = (this.tongDat / (this.tongDat + this.tongLoi)) * 100;
          if (this.percentFTT < 0) this.percentFTT = 0;
          if (this.percentFTT <= 96) {
            this.percentFTTClass = 'alertx';
          }
        }
      }
    });
    this.service.getTopLoiChiTietLoi().subscribe(res => {
      if (res) {
        this.dataChiTietLoi = res;
        this.currentDay = this.getCurrentDate(new Date());
        this.currentTime = this.getCurrentTime(new Date());
        this.dayNow = this.getCurrentDay(new Date());
        this.monthNow = this.getCurrentMonth(new Date());
        this.monthNowNum = this.getCurrentMonthNum(new Date());
      }
    });
    this.service.getNangSuatLaoDongNgaySubject().subscribe(res => {
      if (res) {
        this.currentTime = this.getCurrentTime(new Date());
        this.nsLdNgay = res;
      }
    });
  }

  buildSlider(imageVideos) {
    this.imageObject = [];
    for (let i = 0; i < imageVideos.length; i++) {
      if (imageVideos[i].indexOf('.mp4') > 0) {
        this.imageObject.push(this.buildVideoObject(imageVideos[i].trim()));
      } else {
        this.imageObject.push(this.buildImageObject(imageVideos[i].trim()));
      }
    }
  }

  buildVideoObject(videoName) {
    return {
      video: SERVER_API_URL + 'mmimages/video/' + videoName
    };
  }

  buildImageObject(imageName) {
    return {
      image: SERVER_API_URL + 'mmimages/video/' + imageName,
      thumbImage: SERVER_API_URL + 'mmimages/video/' + imageName,
      alt: '',
      title: ''
    };
  }

  buildPlayTime(tmpTimePlays) {
    this.playTimes = [];
    const tmpSize = tmpTimePlays.length;
    for (let i = 0; i < tmpSize; i++) {
      const tmpTimePlay = tmpTimePlays[i].trim().split('-');
      if (tmpTimePlay.length === 2) {
        const tmpStartValues = tmpTimePlay[0].split('h');
        let startMinutes = Number(tmpStartValues[0]) * 60;
        if (tmpStartValues.length > 1) {
          startMinutes += Number(tmpStartValues[1]);
        }
        const tmpEndValues = tmpTimePlay[1].split('h');
        let endMinutes = Number(tmpEndValues[0]) * 60;
        if (tmpEndValues.length > 1) {
          endMinutes += Number(tmpEndValues[1]);
        }
        this.playTimes.push({ start: startMinutes, end: endMinutes });
      }
    }
  }

  intervalCheckShowVideo() {
    interval(1000).subscribe(_ => {
      const tmp = this.isShowVideo();
      if (tmp !== this.isShowVideoPlayer) {
        this.isShowVideoPlayer = tmp;
      }
    });
  }

  /**
   * Trigger the specified event on the specified element.
   * @param  {Object} elem  the target element.
   * @param  {String} event the type of the event (e.g. 'click').
   */
  triggerEvent(elem, event) {
    const clickEvent = new Event(event); // Create the event.
    elem.dispatchEvent(clickEvent); // Dispatch the event.
  }

  openFullscreen() {
    if (this.elem.requestFullscreen) {
      this.elem.requestFullscreen();
    } else if (this.elem.mozRequestFullScreen) {
      /* Firefox */
      this.elem.mozRequestFullScreen();
    } else if (this.elem.webkitRequestFullscreen) {
      /* Chrome, Safari and Opera */
      this.elem.webkitRequestFullscreen();
    } else if (this.elem.msRequestFullscreen) {
      /* IE/Edge */
      this.elem.msRequestFullscreen();
    }
  }

  closeFullscreen() {
    if (this.document.exitFullscreen) {
      this.document.exitFullscreen();
    } else if (this.document.mozCancelFullScreen) {
      /* Firefox */
      this.document.mozCancelFullScreen();
    } else if (this.document.webkitExitFullscreen) {
      /* Chrome, Safari and Opera */
      this.document.webkitExitFullscreen();
    } else if (this.document.msExitFullscreen) {
      /* IE/Edge */
      this.document.msExitFullscreen();
    }
  }

  openCloseFullScreen() {
    if (this.toggleFullScreen) {
      this.openFullscreen();
      this.toggleFullScreen = false;
    } else {
      this.closeFullscreen();
      this.toggleFullScreen = true;
    }
  }

  ngAfterViewInit() {}

  formatNumber(number) {
    if (number) {
      return number.toLocaleString('en-US', { maximumFractionDigits: 1 });
    }
    return number;
  }

  updateNgaySanXuatDauTien(ngaySanXuatDauTien) {
    const nsxDate = new Date(ngaySanXuatDauTien);
    this.dayNgayXuatDau = this.getCurrentDay(nsxDate);
    this.monthNgayXuatDau = this.getCurrentMonth(nsxDate);
  }

  updateNgaySanXuatHomNay(ngaySanXuatHomNay) {
    const nsxDate = new Date(ngaySanXuatHomNay);
    this.dayNgayXuatHomNay = this.getCurrentDay(nsxDate);
    this.monthNgayXuatHomNay = this.getCurrentMonth(nsxDate);
  }

  getCurrentDate(today: Date) {
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    return dd + '/' + mm;
  }

  getCurrentDateAll(today: Date) {
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    const yyyy = String(today.getFullYear());
    return yyyy + '-' + mm + '-' + dd;
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

  getCurrentDateYear(today: Date) {
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    const yyy = String(today.getFullYear());
    return dd + '/' + mm + '/' + yyy;
  }

  getCurrentTime(today: Date) {
    const hours = today.getHours();
    const minutes = today.getMinutes();
    const minutesString = minutes < 10 ? '0' + minutes : minutes;
    return hours + ':' + minutesString;
  }

  convertToDate(dateValue) {
    if (dateValue) {
      const date = new Date(dateValue);
      return this.getCurrentDateYear(date);
    }
    return '';
  }

  isShowVideo() {
    const today = new Date();
    const minutes = today.getHours() * 60 + today.getMinutes();

    if (this.playTimes.length > 0) {
      const tmpSize = this.playTimes.length;
      for (let i = 0; i < tmpSize; i++) {
        if (minutes >= this.playTimes[i].start && minutes <= this.playTimes[i].end) {
          return true;
        }
      }
    }
    return false;
  }

  getCaption(dataSource: any) {
    this.categories = [];
    this.suaDat = [];
    this.tongSuaDat = 0;
    for (let i = 0; i < dataSource.length; i++) {
      if (this.categories.length < 9) this.categories.push(dataSource[i].caption.split('*')[0]);
      if (this.suaDat.length < 9) {
        this.suaDat.push(dataSource[i].caption.split('*')[1]);
        this.tongSuaDat += Number(dataSource[i].caption.split('*')[1]);
      }
    }
  }
  getValueMucTieu(dataSource: any) {
    this.mucTieu = [];
    this.tongDat = 0;
    for (let i = 0; i < dataSource.length; i++) {
      if (this.mucTieu.length < 9) {
        if (Number(dataSource[i].valueDat) === 0) this.mucTieu.push({ color: 'dat_khongdat', value: dataSource[i].valueDat });
        else if (dataSource[i].valueDat >= dataSource[i].valueMucTieu) this.mucTieu.push({ color: 'dat', value: dataSource[i].valueDat });
        else this.mucTieu.push({ color: 'khongdat', value: dataSource[i].valueDat });
        this.tongDat += Number(dataSource[i].valueDat);
      }
    }
  }
  getValueDat(dataSource: any) {
    this.dat = [];
    this.chuaDat = [];
    for (let i = 0; i < dataSource.length; i++) {
      if (this.dat.length < 9) this.dat.push(dataSource[i].valueMucTieu);
      if (this.chuaDat.length < 9) {
        if (Number(dataSource[i].valueTiLeLoi) > 0) this.chuaDat.push({ color: 'red', value: dataSource[i].valueTiLeLoi });
        else this.chuaDat.push({ color: 'white', value: dataSource[i].valueTiLeLoi });
      }
    }
  }
}
