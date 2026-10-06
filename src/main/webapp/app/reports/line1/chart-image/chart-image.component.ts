import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ResizedEvent } from 'angular-resize-event';
import { SERVER_API_URL } from 'app/app.constants';
import { Line1Service } from 'app/reports/line1.service';

@Component({
  selector: 'jhi-chart-image',
  templateUrl: './chart-image.component.html',
  styleUrls: ['./chart-image.component.scss']
})
export class ChartImageComponent implements OnInit, AfterViewInit {
  @ViewChild('canvasFront', { static: true }) canvasFront: ElementRef<HTMLCanvasElement>;
  @ViewChild('canvasBack', { static: true }) canvasBack: ElementRef<HTMLCanvasElement>;
  @ViewChild('frontContain', { static: true }) frontContain: ElementRef;
  @ViewChild('backContain', { static: true }) backContain: ElementRef;

  frontWidth: number;
  frontHeight: number;
  backWidth: number;
  backHeight: number;

  defectR: number;
  defectLine: number;

  dataSource: any = [];

  private ctxF: CanvasRenderingContext2D;
  private ctxB: CanvasRenderingContext2D;

  constructor(private service: Line1Service) {}
  ngOnInit() {
    this.defectR = 5;
    this.defectLine = 2.5;
  }

  onFrontResized(event: ResizedEvent) {
    if (this.frontHeight <= 0 || Math.abs(event.newHeight - this.frontHeight) > 80 || Math.abs(event.newWidth - this.frontWidth) > 80) {
      this.frontWidth = event.newWidth;
      this.frontHeight = event.newHeight;
    }
  }

  onBackResized(event: ResizedEvent) {
    if (this.backHeight <= 0 || Math.abs(event.newHeight - this.backHeight) > 80 || Math.abs(event.newWidth - this.backWidth) > 80) {
      this.backWidth = event.newWidth;
      this.backHeight = event.newHeight;
    }
  }

  ngAfterViewInit() {
    this.frontWidth = 0;
    this.frontHeight = 0;
    this.backWidth = 0;
    this.backHeight = 0;

    this.ctxF = this.canvasFront.nativeElement.getContext('2d');
    this.ctxB = this.canvasBack.nativeElement.getContext('2d');

    this.service.getTopLoiChiTietLoi().subscribe(res => {
      this.dataSource = res;
      const backgroundTruoc = new Image();
      backgroundTruoc.src = SERVER_API_URL + this.dataSource.urlHinhMatTruoc;

      // // Make sure the image is loaded first otherwise nothing will draw.
      backgroundTruoc.onload = function() {
        const wrh = this.computeRatio(backgroundTruoc.width, backgroundTruoc.height);
        let canvasWidth = this.frontWidth;
        let canvasHeight = canvasWidth / wrh;
        if (canvasHeight > this.frontHeight) {
          canvasHeight = this.frontHeight;
          canvasWidth = canvasHeight * wrh;
        }

        this.canvasFront.nativeElement.width = canvasWidth;
        this.canvasFront.nativeElement.height = canvasHeight;
        this.ctxF.drawImage(backgroundTruoc, 0, 0, canvasWidth, canvasHeight);
        this.drawFrontErrorPoint(canvasWidth / backgroundTruoc.width, canvasHeight / backgroundTruoc.height);
      }.bind(this);

      const backgroundSau = new Image();
      backgroundSau.src = SERVER_API_URL + this.dataSource.urlHinhMatSau;

      // Make sure the image is loaded first otherwise nothing will draw.
      backgroundSau.onload = function() {
        // eslint-disable-next-line no-console
        console.log('load image ', SERVER_API_URL);
        const wrh = this.computeRatio(backgroundSau.width, backgroundSau.height);
        let canvasWidth = this.backWidth;
        let canvasHeight = canvasWidth / wrh;
        if (canvasHeight > this.backHeight) {
          canvasHeight = this.backHeight;
          canvasWidth = canvasHeight * wrh;
        }
        this.canvasBack.nativeElement.width = canvasWidth;
        this.canvasBack.nativeElement.height = canvasHeight;
        this.ctxB.drawImage(backgroundSau, 0, 0, canvasWidth, canvasHeight);
        this.drawBackErrorPoint(canvasWidth / backgroundSau.width, canvasHeight / backgroundSau.height);
      }.bind(this);
    });
  }

  computeRatio(imageWidth, imageHeight) {
    return imageWidth / imageHeight;
  }

  drawFrontErrorPoint(wScale, hScale) {
    for (let i = 0; i < this.dataSource.chiTietLoiDTOS.length; i++) {
      if (this.dataSource.chiTietLoiDTOS[i].isFront) {
        if (!this.dataSource.chiTietLoiDTOS[i].isInside) {
          this.ctxF.beginPath();
          this.ctxF.arc(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale,
            this.defectR,
            0,
            2 * Math.PI,
            true
          );
          this.ctxF.fillStyle = this.service.computeColor(this.dataSource.chiTietLoiDTOS[i].proLoi.mauSac);
          this.ctxF.fill();
        } else {
          this.ctxF.beginPath();
          this.ctxF.arc(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale,
            this.defectR,
            0,
            2 * Math.PI,
            true
          );
          this.ctxF.fillStyle = this.service.computeColor(this.dataSource.chiTietLoiDTOS[i].proLoi.mauSac);
          this.ctxF.fill();
          this.ctxF.strokeStyle = '#000000';
          this.ctxF.moveTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale - this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale - this.defectLine
          );
          this.ctxF.lineTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale + this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale + this.defectLine
          );
          this.ctxF.stroke();
          this.ctxF.moveTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale - this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale + this.defectLine
          );
          this.ctxF.lineTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale + this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale - this.defectLine
          );
          this.ctxF.stroke();
        }
      }
    }
  }

  drawBackErrorPoint(wScale, hScale) {
    for (let i = 0; i < this.dataSource.chiTietLoiDTOS.length; i++) {
      if (!this.dataSource.chiTietLoiDTOS[i].isFront) {
        if (!this.dataSource.chiTietLoiDTOS[i].isInside) {
          this.ctxB.beginPath();
          this.ctxB.arc(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale,
            this.defectR,
            0,
            2 * Math.PI,
            true
          );
          this.ctxB.fillStyle = this.service.computeColor(this.dataSource.chiTietLoiDTOS[i].proLoi.mauSac);
          this.ctxB.fill();
        } else {
          this.ctxB.beginPath();
          this.ctxB.arc(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale,
            this.defectR,
            0,
            2 * Math.PI,
            true
          );
          this.ctxB.fillStyle = this.service.computeColor(this.dataSource.chiTietLoiDTOS[i].proLoi.mauSac);
          this.ctxB.fill();
          this.ctxB.strokeStyle = '#000000';
          this.ctxB.moveTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale - this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale - this.defectLine
          );
          this.ctxB.lineTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale + this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale + this.defectLine
          );
          this.ctxB.stroke();
          this.ctxB.moveTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale - this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale + this.defectLine
          );
          this.ctxB.lineTo(
            this.dataSource.chiTietLoiDTOS[i].dx * wScale + this.defectLine,
            this.dataSource.chiTietLoiDTOS[i].dy * hScale - this.defectLine
          );
          this.ctxB.stroke();
        }
      }
    }
  }

  degreesToRadians(degrees) {
    return (degrees * Math.PI) / 180;
  }
  animate(): void {}
}
