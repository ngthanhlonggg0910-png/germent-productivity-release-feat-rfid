import { element, by, ElementFinder } from 'protractor';

export class ProNSCLComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-nscl div table .btn-danger'));
  title = element.all(by.css('jhi-pro-nscl div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ProNSCLUpdatePage {
  pageTitle = element(by.id('jhi-pro-nscl-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  taiKhoanInput = element(by.id('field_taiKhoan'));
  ngaySanXuatInput = element(by.id('field_ngaySanXuat'));
  sttInput = element(by.id('field_stt'));
  mucTieuNgayInput = element(by.id('field_mucTieuNgay'));
  thucHienNgayInput = element(by.id('field_thucHienNgay'));
  loiNgayInput = element(by.id('field_loiNgay'));
  suaNgayInput = element(by.id('field_suaNgay'));
  btpCapInput = element(by.id('field_btpCap'));
  qty1hInput = element(by.id('field_qty1h'));
  qty2hInput = element(by.id('field_qty2h'));
  qty3hInput = element(by.id('field_qty3h'));
  qty4hInput = element(by.id('field_qty4h'));
  qty5hInput = element(by.id('field_qty5h'));
  qty6hInput = element(by.id('field_qty6h'));
  qty7hInput = element(by.id('field_qty7h'));
  qty8hInput = element(by.id('field_qty8h'));
  qty9hInput = element(by.id('field_qty9h'));
  qty10hInput = element(by.id('field_qty10h'));
  qty11hInput = element(by.id('field_qty11h'));
  qty12hInput = element(by.id('field_qty12h'));
  qty13hInput = element(by.id('field_qty13h'));
  qty14hInput = element(by.id('field_qty14h'));
  reject1hInput = element(by.id('field_reject1h'));
  reject2hInput = element(by.id('field_reject2h'));
  reject3hInput = element(by.id('field_reject3h'));
  reject4hInput = element(by.id('field_reject4h'));
  reject5hInput = element(by.id('field_reject5h'));
  reject6hInput = element(by.id('field_reject6h'));
  reject7hInput = element(by.id('field_reject7h'));
  reject8hInput = element(by.id('field_reject8h'));
  reject9hInput = element(by.id('field_reject9h'));
  reject10hInput = element(by.id('field_reject10h'));
  reject11hInput = element(by.id('field_reject11h'));
  reject12hInput = element(by.id('field_reject12h'));
  reject13hInput = element(by.id('field_reject13h'));
  reject14hInput = element(by.id('field_reject14h'));
  man1hInput = element(by.id('field_man1h'));
  man2hInput = element(by.id('field_man2h'));
  man3hInput = element(by.id('field_man3h'));
  man4hInput = element(by.id('field_man4h'));
  man5hInput = element(by.id('field_man5h'));
  man6hInput = element(by.id('field_man6h'));
  man7hInput = element(by.id('field_man7h'));
  man8hInput = element(by.id('field_man8h'));
  man9hInput = element(by.id('field_man9h'));
  man10hInput = element(by.id('field_man10h'));
  man11hInput = element(by.id('field_man11h'));
  man12hInput = element(by.id('field_man12h'));
  man13hInput = element(by.id('field_man13h'));
  man14hInput = element(by.id('field_man14h'));
  manOnly7HoursInput = element(by.id('field_manOnly7Hours'));
  fix1hInput = element(by.id('field_fix1h'));
  fix2hInput = element(by.id('field_fix2h'));
  fix3hInput = element(by.id('field_fix3h'));
  fix4hInput = element(by.id('field_fix4h'));
  fix5hInput = element(by.id('field_fix5h'));
  fix6hInput = element(by.id('field_fix6h'));
  fix7hInput = element(by.id('field_fix7h'));
  fix8hInput = element(by.id('field_fix8h'));
  fix9hInput = element(by.id('field_fix9h'));
  fix10hInput = element(by.id('field_fix10h'));
  fix11hInput = element(by.id('field_fix11h'));
  fix12hInput = element(by.id('field_fix12h'));
  fix13hInput = element(by.id('field_fix13h'));
  fix14hInput = element(by.id('field_fix14h'));

  proKeHoachSanXuatSelect = element(by.id('field_proKeHoachSanXuat'));
  proCheckPointSelect = element(by.id('field_proCheckPoint'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTaiKhoanInput(taiKhoan: string): Promise<void> {
    await this.taiKhoanInput.sendKeys(taiKhoan);
  }

  async getTaiKhoanInput(): Promise<string> {
    return await this.taiKhoanInput.getAttribute('value');
  }

  async setNgaySanXuatInput(ngaySanXuat: string): Promise<void> {
    await this.ngaySanXuatInput.sendKeys(ngaySanXuat);
  }

  async getNgaySanXuatInput(): Promise<string> {
    return await this.ngaySanXuatInput.getAttribute('value');
  }

  async setSttInput(stt: string): Promise<void> {
    await this.sttInput.sendKeys(stt);
  }

  async getSttInput(): Promise<string> {
    return await this.sttInput.getAttribute('value');
  }

  async setMucTieuNgayInput(mucTieuNgay: string): Promise<void> {
    await this.mucTieuNgayInput.sendKeys(mucTieuNgay);
  }

  async getMucTieuNgayInput(): Promise<string> {
    return await this.mucTieuNgayInput.getAttribute('value');
  }

  async setThucHienNgayInput(thucHienNgay: string): Promise<void> {
    await this.thucHienNgayInput.sendKeys(thucHienNgay);
  }

  async getThucHienNgayInput(): Promise<string> {
    return await this.thucHienNgayInput.getAttribute('value');
  }

  async setLoiNgayInput(loiNgay: string): Promise<void> {
    await this.loiNgayInput.sendKeys(loiNgay);
  }

  async getLoiNgayInput(): Promise<string> {
    return await this.loiNgayInput.getAttribute('value');
  }

  async setSuaNgayInput(suaNgay: string): Promise<void> {
    await this.suaNgayInput.sendKeys(suaNgay);
  }

  async getSuaNgayInput(): Promise<string> {
    return await this.suaNgayInput.getAttribute('value');
  }

  async setBtpCapInput(btpCap: string): Promise<void> {
    await this.btpCapInput.sendKeys(btpCap);
  }

  async getBtpCapInput(): Promise<string> {
    return await this.btpCapInput.getAttribute('value');
  }

  async setQty1hInput(qty1h: string): Promise<void> {
    await this.qty1hInput.sendKeys(qty1h);
  }

  async getQty1hInput(): Promise<string> {
    return await this.qty1hInput.getAttribute('value');
  }

  async setQty2hInput(qty2h: string): Promise<void> {
    await this.qty2hInput.sendKeys(qty2h);
  }

  async getQty2hInput(): Promise<string> {
    return await this.qty2hInput.getAttribute('value');
  }

  async setQty3hInput(qty3h: string): Promise<void> {
    await this.qty3hInput.sendKeys(qty3h);
  }

  async getQty3hInput(): Promise<string> {
    return await this.qty3hInput.getAttribute('value');
  }

  async setQty4hInput(qty4h: string): Promise<void> {
    await this.qty4hInput.sendKeys(qty4h);
  }

  async getQty4hInput(): Promise<string> {
    return await this.qty4hInput.getAttribute('value');
  }

  async setQty5hInput(qty5h: string): Promise<void> {
    await this.qty5hInput.sendKeys(qty5h);
  }

  async getQty5hInput(): Promise<string> {
    return await this.qty5hInput.getAttribute('value');
  }

  async setQty6hInput(qty6h: string): Promise<void> {
    await this.qty6hInput.sendKeys(qty6h);
  }

  async getQty6hInput(): Promise<string> {
    return await this.qty6hInput.getAttribute('value');
  }

  async setQty7hInput(qty7h: string): Promise<void> {
    await this.qty7hInput.sendKeys(qty7h);
  }

  async getQty7hInput(): Promise<string> {
    return await this.qty7hInput.getAttribute('value');
  }

  async setQty8hInput(qty8h: string): Promise<void> {
    await this.qty8hInput.sendKeys(qty8h);
  }

  async getQty8hInput(): Promise<string> {
    return await this.qty8hInput.getAttribute('value');
  }

  async setQty9hInput(qty9h: string): Promise<void> {
    await this.qty9hInput.sendKeys(qty9h);
  }

  async getQty9hInput(): Promise<string> {
    return await this.qty9hInput.getAttribute('value');
  }

  async setQty10hInput(qty10h: string): Promise<void> {
    await this.qty10hInput.sendKeys(qty10h);
  }

  async getQty10hInput(): Promise<string> {
    return await this.qty10hInput.getAttribute('value');
  }

  async setQty11hInput(qty11h: string): Promise<void> {
    await this.qty11hInput.sendKeys(qty11h);
  }

  async getQty11hInput(): Promise<string> {
    return await this.qty11hInput.getAttribute('value');
  }

  async setQty12hInput(qty12h: string): Promise<void> {
    await this.qty12hInput.sendKeys(qty12h);
  }

  async getQty12hInput(): Promise<string> {
    return await this.qty12hInput.getAttribute('value');
  }

  async setQty13hInput(qty13h: string): Promise<void> {
    await this.qty13hInput.sendKeys(qty13h);
  }

  async getQty13hInput(): Promise<string> {
    return await this.qty13hInput.getAttribute('value');
  }

  async setQty14hInput(qty14h: string): Promise<void> {
    await this.qty14hInput.sendKeys(qty14h);
  }

  async getQty14hInput(): Promise<string> {
    return await this.qty14hInput.getAttribute('value');
  }

  async setReject1hInput(reject1h: string): Promise<void> {
    await this.reject1hInput.sendKeys(reject1h);
  }

  async getReject1hInput(): Promise<string> {
    return await this.reject1hInput.getAttribute('value');
  }

  async setReject2hInput(reject2h: string): Promise<void> {
    await this.reject2hInput.sendKeys(reject2h);
  }

  async getReject2hInput(): Promise<string> {
    return await this.reject2hInput.getAttribute('value');
  }

  async setReject3hInput(reject3h: string): Promise<void> {
    await this.reject3hInput.sendKeys(reject3h);
  }

  async getReject3hInput(): Promise<string> {
    return await this.reject3hInput.getAttribute('value');
  }

  async setReject4hInput(reject4h: string): Promise<void> {
    await this.reject4hInput.sendKeys(reject4h);
  }

  async getReject4hInput(): Promise<string> {
    return await this.reject4hInput.getAttribute('value');
  }

  async setReject5hInput(reject5h: string): Promise<void> {
    await this.reject5hInput.sendKeys(reject5h);
  }

  async getReject5hInput(): Promise<string> {
    return await this.reject5hInput.getAttribute('value');
  }

  async setReject6hInput(reject6h: string): Promise<void> {
    await this.reject6hInput.sendKeys(reject6h);
  }

  async getReject6hInput(): Promise<string> {
    return await this.reject6hInput.getAttribute('value');
  }

  async setReject7hInput(reject7h: string): Promise<void> {
    await this.reject7hInput.sendKeys(reject7h);
  }

  async getReject7hInput(): Promise<string> {
    return await this.reject7hInput.getAttribute('value');
  }

  async setReject8hInput(reject8h: string): Promise<void> {
    await this.reject8hInput.sendKeys(reject8h);
  }

  async getReject8hInput(): Promise<string> {
    return await this.reject8hInput.getAttribute('value');
  }

  async setReject9hInput(reject9h: string): Promise<void> {
    await this.reject9hInput.sendKeys(reject9h);
  }

  async getReject9hInput(): Promise<string> {
    return await this.reject9hInput.getAttribute('value');
  }

  async setReject10hInput(reject10h: string): Promise<void> {
    await this.reject10hInput.sendKeys(reject10h);
  }

  async getReject10hInput(): Promise<string> {
    return await this.reject10hInput.getAttribute('value');
  }

  async setReject11hInput(reject11h: string): Promise<void> {
    await this.reject11hInput.sendKeys(reject11h);
  }

  async getReject11hInput(): Promise<string> {
    return await this.reject11hInput.getAttribute('value');
  }

  async setReject12hInput(reject12h: string): Promise<void> {
    await this.reject12hInput.sendKeys(reject12h);
  }

  async getReject12hInput(): Promise<string> {
    return await this.reject12hInput.getAttribute('value');
  }

  async setReject13hInput(reject13h: string): Promise<void> {
    await this.reject13hInput.sendKeys(reject13h);
  }

  async getReject13hInput(): Promise<string> {
    return await this.reject13hInput.getAttribute('value');
  }

  async setReject14hInput(reject14h: string): Promise<void> {
    await this.reject14hInput.sendKeys(reject14h);
  }

  async getReject14hInput(): Promise<string> {
    return await this.reject14hInput.getAttribute('value');
  }

  async setMan1hInput(man1h: string): Promise<void> {
    await this.man1hInput.sendKeys(man1h);
  }

  async getMan1hInput(): Promise<string> {
    return await this.man1hInput.getAttribute('value');
  }

  async setMan2hInput(man2h: string): Promise<void> {
    await this.man2hInput.sendKeys(man2h);
  }

  async getMan2hInput(): Promise<string> {
    return await this.man2hInput.getAttribute('value');
  }

  async setMan3hInput(man3h: string): Promise<void> {
    await this.man3hInput.sendKeys(man3h);
  }

  async getMan3hInput(): Promise<string> {
    return await this.man3hInput.getAttribute('value');
  }

  async setMan4hInput(man4h: string): Promise<void> {
    await this.man4hInput.sendKeys(man4h);
  }

  async getMan4hInput(): Promise<string> {
    return await this.man4hInput.getAttribute('value');
  }

  async setMan5hInput(man5h: string): Promise<void> {
    await this.man5hInput.sendKeys(man5h);
  }

  async getMan5hInput(): Promise<string> {
    return await this.man5hInput.getAttribute('value');
  }

  async setMan6hInput(man6h: string): Promise<void> {
    await this.man6hInput.sendKeys(man6h);
  }

  async getMan6hInput(): Promise<string> {
    return await this.man6hInput.getAttribute('value');
  }

  async setMan7hInput(man7h: string): Promise<void> {
    await this.man7hInput.sendKeys(man7h);
  }

  async getMan7hInput(): Promise<string> {
    return await this.man7hInput.getAttribute('value');
  }

  async setMan8hInput(man8h: string): Promise<void> {
    await this.man8hInput.sendKeys(man8h);
  }

  async getMan8hInput(): Promise<string> {
    return await this.man8hInput.getAttribute('value');
  }

  async setMan9hInput(man9h: string): Promise<void> {
    await this.man9hInput.sendKeys(man9h);
  }

  async getMan9hInput(): Promise<string> {
    return await this.man9hInput.getAttribute('value');
  }

  async setMan10hInput(man10h: string): Promise<void> {
    await this.man10hInput.sendKeys(man10h);
  }

  async getMan10hInput(): Promise<string> {
    return await this.man10hInput.getAttribute('value');
  }

  async setMan11hInput(man11h: string): Promise<void> {
    await this.man11hInput.sendKeys(man11h);
  }

  async getMan11hInput(): Promise<string> {
    return await this.man11hInput.getAttribute('value');
  }

  async setMan12hInput(man12h: string): Promise<void> {
    await this.man12hInput.sendKeys(man12h);
  }

  async getMan12hInput(): Promise<string> {
    return await this.man12hInput.getAttribute('value');
  }

  async setMan13hInput(man13h: string): Promise<void> {
    await this.man13hInput.sendKeys(man13h);
  }

  async getMan13hInput(): Promise<string> {
    return await this.man13hInput.getAttribute('value');
  }

  async setMan14hInput(man14h: string): Promise<void> {
    await this.man14hInput.sendKeys(man14h);
  }

  async getMan14hInput(): Promise<string> {
    return await this.man14hInput.getAttribute('value');
  }

  async setManOnly7HoursInput(manOnly7Hours: string): Promise<void> {
    await this.manOnly7HoursInput.sendKeys(manOnly7Hours);
  }

  async getManOnly7HoursInput(): Promise<string> {
    return await this.manOnly7HoursInput.getAttribute('value');
  }

  async setFix1hInput(fix1h: string): Promise<void> {
    await this.fix1hInput.sendKeys(fix1h);
  }

  async getFix1hInput(): Promise<string> {
    return await this.fix1hInput.getAttribute('value');
  }

  async setFix2hInput(fix2h: string): Promise<void> {
    await this.fix2hInput.sendKeys(fix2h);
  }

  async getFix2hInput(): Promise<string> {
    return await this.fix2hInput.getAttribute('value');
  }

  async setFix3hInput(fix3h: string): Promise<void> {
    await this.fix3hInput.sendKeys(fix3h);
  }

  async getFix3hInput(): Promise<string> {
    return await this.fix3hInput.getAttribute('value');
  }

  async setFix4hInput(fix4h: string): Promise<void> {
    await this.fix4hInput.sendKeys(fix4h);
  }

  async getFix4hInput(): Promise<string> {
    return await this.fix4hInput.getAttribute('value');
  }

  async setFix5hInput(fix5h: string): Promise<void> {
    await this.fix5hInput.sendKeys(fix5h);
  }

  async getFix5hInput(): Promise<string> {
    return await this.fix5hInput.getAttribute('value');
  }

  async setFix6hInput(fix6h: string): Promise<void> {
    await this.fix6hInput.sendKeys(fix6h);
  }

  async getFix6hInput(): Promise<string> {
    return await this.fix6hInput.getAttribute('value');
  }

  async setFix7hInput(fix7h: string): Promise<void> {
    await this.fix7hInput.sendKeys(fix7h);
  }

  async getFix7hInput(): Promise<string> {
    return await this.fix7hInput.getAttribute('value');
  }

  async setFix8hInput(fix8h: string): Promise<void> {
    await this.fix8hInput.sendKeys(fix8h);
  }

  async getFix8hInput(): Promise<string> {
    return await this.fix8hInput.getAttribute('value');
  }

  async setFix9hInput(fix9h: string): Promise<void> {
    await this.fix9hInput.sendKeys(fix9h);
  }

  async getFix9hInput(): Promise<string> {
    return await this.fix9hInput.getAttribute('value');
  }

  async setFix10hInput(fix10h: string): Promise<void> {
    await this.fix10hInput.sendKeys(fix10h);
  }

  async getFix10hInput(): Promise<string> {
    return await this.fix10hInput.getAttribute('value');
  }

  async setFix11hInput(fix11h: string): Promise<void> {
    await this.fix11hInput.sendKeys(fix11h);
  }

  async getFix11hInput(): Promise<string> {
    return await this.fix11hInput.getAttribute('value');
  }

  async setFix12hInput(fix12h: string): Promise<void> {
    await this.fix12hInput.sendKeys(fix12h);
  }

  async getFix12hInput(): Promise<string> {
    return await this.fix12hInput.getAttribute('value');
  }

  async setFix13hInput(fix13h: string): Promise<void> {
    await this.fix13hInput.sendKeys(fix13h);
  }

  async getFix13hInput(): Promise<string> {
    return await this.fix13hInput.getAttribute('value');
  }

  async setFix14hInput(fix14h: string): Promise<void> {
    await this.fix14hInput.sendKeys(fix14h);
  }

  async getFix14hInput(): Promise<string> {
    return await this.fix14hInput.getAttribute('value');
  }

  async proKeHoachSanXuatSelectLastOption(): Promise<void> {
    await this.proKeHoachSanXuatSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proKeHoachSanXuatSelectOption(option: string): Promise<void> {
    await this.proKeHoachSanXuatSelect.sendKeys(option);
  }

  getProKeHoachSanXuatSelect(): ElementFinder {
    return this.proKeHoachSanXuatSelect;
  }

  async getProKeHoachSanXuatSelectedOption(): Promise<string> {
    return await this.proKeHoachSanXuatSelect.element(by.css('option:checked')).getText();
  }

  async proCheckPointSelectLastOption(): Promise<void> {
    await this.proCheckPointSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proCheckPointSelectOption(option: string): Promise<void> {
    await this.proCheckPointSelect.sendKeys(option);
  }

  getProCheckPointSelect(): ElementFinder {
    return this.proCheckPointSelect;
  }

  async getProCheckPointSelectedOption(): Promise<string> {
    return await this.proCheckPointSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ProNSCLDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proNSCL-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proNSCL'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
