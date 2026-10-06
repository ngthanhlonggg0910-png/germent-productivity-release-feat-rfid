import { element, by, ElementFinder } from 'protractor';

export class ProIssueComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-issue div table .btn-danger'));
  title = element.all(by.css('jhi-pro-issue div h2#page-heading span')).first();

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

export class ProIssueUpdatePage {
  pageTitle = element(by.id('jhi-pro-issue-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  maHangInput = element(by.id('field_maHang'));
  ponoInput = element(by.id('field_pono'));
  tenChuyenInput = element(by.id('field_tenChuyen'));
  ngayGioBatDauInput = element(by.id('field_ngayGioBatDau'));
  ngayGioKetThucInput = element(by.id('field_ngayGioKetThuc'));
  maLoiInput = element(by.id('field_maLoi'));
  moTaLoiInput = element(by.id('field_moTaLoi'));
  maMayInput = element(by.id('field_maMay'));
  soTramInput = element(by.id('field_soTram'));
  proChuyenIdInput = element(by.id('field_proChuyenId'));
  proSanPhamIdInput = element(by.id('field_proSanPhamId'));
  nguoiTaoInput = element(by.id('field_nguoiTao'));
  checkPointTypeInput = element(by.id('field_checkPointType'));
  checkPointIdInput = element(by.id('field_checkPointId'));
  checkPointNameInput = element(by.id('field_checkPointName'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMaHangInput(maHang) {
    await this.maHangInput.sendKeys(maHang);
  }

  async getMaHangInput() {
    return await this.maHangInput.getAttribute('value');
  }

  async setPonoInput(pono) {
    await this.ponoInput.sendKeys(pono);
  }

  async getPonoInput() {
    return await this.ponoInput.getAttribute('value');
  }

  async setTenChuyenInput(tenChuyen) {
    await this.tenChuyenInput.sendKeys(tenChuyen);
  }

  async getTenChuyenInput() {
    return await this.tenChuyenInput.getAttribute('value');
  }

  async setNgayGioBatDauInput(ngayGioBatDau) {
    await this.ngayGioBatDauInput.sendKeys(ngayGioBatDau);
  }

  async getNgayGioBatDauInput() {
    return await this.ngayGioBatDauInput.getAttribute('value');
  }

  async setNgayGioKetThucInput(ngayGioKetThuc) {
    await this.ngayGioKetThucInput.sendKeys(ngayGioKetThuc);
  }

  async getNgayGioKetThucInput() {
    return await this.ngayGioKetThucInput.getAttribute('value');
  }

  async setMaLoiInput(maLoi) {
    await this.maLoiInput.sendKeys(maLoi);
  }

  async getMaLoiInput() {
    return await this.maLoiInput.getAttribute('value');
  }

  async setMoTaLoiInput(moTaLoi) {
    await this.moTaLoiInput.sendKeys(moTaLoi);
  }

  async getMoTaLoiInput() {
    return await this.moTaLoiInput.getAttribute('value');
  }

  async setMaMayInput(maMay) {
    await this.maMayInput.sendKeys(maMay);
  }

  async getMaMayInput() {
    return await this.maMayInput.getAttribute('value');
  }

  async setSoTramInput(soTram) {
    await this.soTramInput.sendKeys(soTram);
  }

  async getSoTramInput() {
    return await this.soTramInput.getAttribute('value');
  }

  async setProChuyenIdInput(proChuyenId) {
    await this.proChuyenIdInput.sendKeys(proChuyenId);
  }

  async getProChuyenIdInput() {
    return await this.proChuyenIdInput.getAttribute('value');
  }

  async setProSanPhamIdInput(proSanPhamId) {
    await this.proSanPhamIdInput.sendKeys(proSanPhamId);
  }

  async getProSanPhamIdInput() {
    return await this.proSanPhamIdInput.getAttribute('value');
  }

  async setNguoiTaoInput(nguoiTao) {
    await this.nguoiTaoInput.sendKeys(nguoiTao);
  }

  async getNguoiTaoInput() {
    return await this.nguoiTaoInput.getAttribute('value');
  }

  async setCheckPointTypeInput(checkPointType) {
    await this.checkPointTypeInput.sendKeys(checkPointType);
  }

  async getCheckPointTypeInput() {
    return await this.checkPointTypeInput.getAttribute('value');
  }

  async setCheckPointIdInput(checkPointId) {
    await this.checkPointIdInput.sendKeys(checkPointId);
  }

  async getCheckPointIdInput() {
    return await this.checkPointIdInput.getAttribute('value');
  }

  async setCheckPointNameInput(checkPointName) {
    await this.checkPointNameInput.sendKeys(checkPointName);
  }

  async getCheckPointNameInput() {
    return await this.checkPointNameInput.getAttribute('value');
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

export class ProIssueDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proIssue-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proIssue'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
