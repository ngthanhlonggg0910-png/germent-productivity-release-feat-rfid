import { element, by, ElementFinder } from 'protractor';

export class ProFinalComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-final div table .btn-danger'));
  title = element.all(by.css('jhi-pro-final div h2#page-heading span')).first();

  async clickOnCreateButton() {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton() {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ProFinalUpdatePage {
  pageTitle = element(by.id('jhi-pro-final-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  khxhIdInput = element(by.id('field_khxhId'));
  maHangInput = element(by.id('field_maHang'));
  poNoInput = element(by.id('field_poNo'));
  ngayKiemFinalInput = element(by.id('field_ngayKiemFinal'));
  tenChuyenInput = element(by.id('field_tenChuyen'));
  ngayGioKiemInput = element(by.id('field_ngayGioKiem'));
  soLuongKiemAqlInput = element(by.id('field_soLuongKiemAql'));
  soLuongInput = element(by.id('field_soLuong'));
  doAmInput = element(by.id('field_doAm'));
  tongSoLoiInput = element(by.id('field_tongSoLoi'));
  soThuTuThungInput = element(by.id('field_soThuTuThung'));
  doKimMdInput = element(by.id('field_doKimMd'));
  aqlInput = element(by.id('field_aql'));
  maxLoiChoPhepInput = element(by.id('field_maxLoiChoPhep'));
  completedInput = element(by.id('field_completed'));
  soLuongPoKiemInput = element(by.id('field_soLuongPoKiem'));
  passInput = element(by.id('field_pass'));
  nguoiKiemInput = element(by.id('field_nguoiKiem'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setKhxhIdInput(khxhId) {
    await this.khxhIdInput.sendKeys(khxhId);
  }

  async getKhxhIdInput() {
    return await this.khxhIdInput.getAttribute('value');
  }

  async setMaHangInput(maHang) {
    await this.maHangInput.sendKeys(maHang);
  }

  async getMaHangInput() {
    return await this.maHangInput.getAttribute('value');
  }

  async setPoNoInput(poNo) {
    await this.poNoInput.sendKeys(poNo);
  }

  async getPoNoInput() {
    return await this.poNoInput.getAttribute('value');
  }

  async setNgayKiemFinalInput(ngayKiemFinal) {
    await this.ngayKiemFinalInput.sendKeys(ngayKiemFinal);
  }

  async getNgayKiemFinalInput() {
    return await this.ngayKiemFinalInput.getAttribute('value');
  }

  async setTenChuyenInput(tenChuyen) {
    await this.tenChuyenInput.sendKeys(tenChuyen);
  }

  async getTenChuyenInput() {
    return await this.tenChuyenInput.getAttribute('value');
  }

  async setNgayGioKiemInput(ngayGioKiem) {
    await this.ngayGioKiemInput.sendKeys(ngayGioKiem);
  }

  async getNgayGioKiemInput() {
    return await this.ngayGioKiemInput.getAttribute('value');
  }

  async setSoLuongKiemAqlInput(soLuongKiemAql) {
    await this.soLuongKiemAqlInput.sendKeys(soLuongKiemAql);
  }

  async getSoLuongKiemAqlInput() {
    return await this.soLuongKiemAqlInput.getAttribute('value');
  }

  async setSoLuongInput(soLuong) {
    await this.soLuongInput.sendKeys(soLuong);
  }

  async getSoLuongInput() {
    return await this.soLuongInput.getAttribute('value');
  }

  async setDoAmInput(doAm) {
    await this.doAmInput.sendKeys(doAm);
  }

  async getDoAmInput() {
    return await this.doAmInput.getAttribute('value');
  }

  async setTongSoLoiInput(tongSoLoi) {
    await this.tongSoLoiInput.sendKeys(tongSoLoi);
  }

  async getTongSoLoiInput() {
    return await this.tongSoLoiInput.getAttribute('value');
  }

  async setSoThuTuThungInput(soThuTuThung) {
    await this.soThuTuThungInput.sendKeys(soThuTuThung);
  }

  async getSoThuTuThungInput() {
    return await this.soThuTuThungInput.getAttribute('value');
  }

  async setDoKimMdInput(doKimMd) {
    await this.doKimMdInput.sendKeys(doKimMd);
  }

  async getDoKimMdInput() {
    return await this.doKimMdInput.getAttribute('value');
  }

  async setAqlInput(aql) {
    await this.aqlInput.sendKeys(aql);
  }

  async getAqlInput() {
    return await this.aqlInput.getAttribute('value');
  }

  async setMaxLoiChoPhepInput(maxLoiChoPhep) {
    await this.maxLoiChoPhepInput.sendKeys(maxLoiChoPhep);
  }

  async getMaxLoiChoPhepInput() {
    return await this.maxLoiChoPhepInput.getAttribute('value');
  }

  getCompletedInput() {
    return this.completedInput;
  }
  async setSoLuongPoKiemInput(soLuongPoKiem) {
    await this.soLuongPoKiemInput.sendKeys(soLuongPoKiem);
  }

  async getSoLuongPoKiemInput() {
    return await this.soLuongPoKiemInput.getAttribute('value');
  }

  getPassInput() {
    return this.passInput;
  }
  async setNguoiKiemInput(nguoiKiem) {
    await this.nguoiKiemInput.sendKeys(nguoiKiem);
  }

  async getNguoiKiemInput() {
    return await this.nguoiKiemInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ProFinalDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proFinal-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proFinal'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
