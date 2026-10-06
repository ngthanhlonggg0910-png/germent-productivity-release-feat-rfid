import { element, by, ElementFinder } from 'protractor';

export class ProRefinalComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-refinal div table .btn-danger'));
  title = element.all(by.css('jhi-pro-refinal div h2#page-heading span')).first();

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

export class ProRefinalUpdatePage {
  pageTitle = element(by.id('jhi-pro-refinal-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  proKHSXIdInput = element(by.id('field_proKHSXId'));
  checkPointIDInput = element(by.id('field_checkPointID'));
  proNSCLIdInput = element(by.id('field_proNSCLId'));
  ngayGioKiemInput = element(by.id('field_ngayGioKiem'));
  soLuongKiemInput = element(by.id('field_soLuongKiem'));
  hangDatHienTaiInput = element(by.id('field_hangDatHienTai'));
  doAmInput = element(by.id('field_doAm'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setProKHSXIdInput(proKHSXId) {
    await this.proKHSXIdInput.sendKeys(proKHSXId);
  }

  async getProKHSXIdInput() {
    return await this.proKHSXIdInput.getAttribute('value');
  }

  async setCheckPointIDInput(checkPointID) {
    await this.checkPointIDInput.sendKeys(checkPointID);
  }

  async getCheckPointIDInput() {
    return await this.checkPointIDInput.getAttribute('value');
  }

  async setProNSCLIdInput(proNSCLId) {
    await this.proNSCLIdInput.sendKeys(proNSCLId);
  }

  async getProNSCLIdInput() {
    return await this.proNSCLIdInput.getAttribute('value');
  }

  async setNgayGioKiemInput(ngayGioKiem) {
    await this.ngayGioKiemInput.sendKeys(ngayGioKiem);
  }

  async getNgayGioKiemInput() {
    return await this.ngayGioKiemInput.getAttribute('value');
  }

  async setSoLuongKiemInput(soLuongKiem) {
    await this.soLuongKiemInput.sendKeys(soLuongKiem);
  }

  async getSoLuongKiemInput() {
    return await this.soLuongKiemInput.getAttribute('value');
  }

  async setHangDatHienTaiInput(hangDatHienTai) {
    await this.hangDatHienTaiInput.sendKeys(hangDatHienTai);
  }

  async getHangDatHienTaiInput() {
    return await this.hangDatHienTaiInput.getAttribute('value');
  }

  async setDoAmInput(doAm) {
    await this.doAmInput.sendKeys(doAm);
  }

  async getDoAmInput() {
    return await this.doAmInput.getAttribute('value');
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

export class ProRefinalDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proRefinal-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proRefinal'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
