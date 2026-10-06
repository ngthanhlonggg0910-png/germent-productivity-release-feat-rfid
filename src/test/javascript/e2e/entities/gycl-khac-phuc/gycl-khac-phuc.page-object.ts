import { element, by, ElementFinder } from 'protractor';

export class GYCLKhacPhucComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-gycl-khac-phuc div table .btn-danger'));
  title = element.all(by.css('jhi-gycl-khac-phuc div h2#page-heading span')).first();

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

export class GYCLKhacPhucUpdatePage {
  pageTitle = element(by.id('jhi-gycl-khac-phuc-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  cumIdInput = element(by.id('field_cumId'));
  tenCumInput = element(by.id('field_tenCum'));
  gopYInput = element(by.id('field_gopY'));
  ketQuaInput = element(by.id('field_ketQua'));
  nguoiTaoInput = element(by.id('field_nguoiTao'));
  userIdInput = element(by.id('field_userId'));
  ngayTaoInput = element(by.id('field_ngayTao'));
  proGopYChatLuongSelect = element(by.id('field_proGopYChatLuong'));
  thuVienLoiSelect = element(by.id('field_thuVienLoi'));
  khacPhucLoiSelect = element(by.id('field_khacPhucLoi'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCumIdInput(cumId) {
    await this.cumIdInput.sendKeys(cumId);
  }

  async getCumIdInput() {
    return await this.cumIdInput.getAttribute('value');
  }

  async setTenCumInput(tenCum) {
    await this.tenCumInput.sendKeys(tenCum);
  }

  async getTenCumInput() {
    return await this.tenCumInput.getAttribute('value');
  }

  async setGopYInput(gopY) {
    await this.gopYInput.sendKeys(gopY);
  }

  async getGopYInput() {
    return await this.gopYInput.getAttribute('value');
  }

  async setKetQuaInput(ketQua) {
    await this.ketQuaInput.sendKeys(ketQua);
  }

  async getKetQuaInput() {
    return await this.ketQuaInput.getAttribute('value');
  }

  async setNguoiTaoInput(nguoiTao) {
    await this.nguoiTaoInput.sendKeys(nguoiTao);
  }

  async getNguoiTaoInput() {
    return await this.nguoiTaoInput.getAttribute('value');
  }

  async setUserIdInput(userId) {
    await this.userIdInput.sendKeys(userId);
  }

  async getUserIdInput() {
    return await this.userIdInput.getAttribute('value');
  }

  async setNgayTaoInput(ngayTao) {
    await this.ngayTaoInput.sendKeys(ngayTao);
  }

  async getNgayTaoInput() {
    return await this.ngayTaoInput.getAttribute('value');
  }

  async proGopYChatLuongSelectLastOption() {
    await this.proGopYChatLuongSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proGopYChatLuongSelectOption(option) {
    await this.proGopYChatLuongSelect.sendKeys(option);
  }

  getProGopYChatLuongSelect(): ElementFinder {
    return this.proGopYChatLuongSelect;
  }

  async getProGopYChatLuongSelectedOption() {
    return await this.proGopYChatLuongSelect.element(by.css('option:checked')).getText();
  }

  async thuVienLoiSelectLastOption() {
    await this.thuVienLoiSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async thuVienLoiSelectOption(option) {
    await this.thuVienLoiSelect.sendKeys(option);
  }

  getThuVienLoiSelect(): ElementFinder {
    return this.thuVienLoiSelect;
  }

  async getThuVienLoiSelectedOption() {
    return await this.thuVienLoiSelect.element(by.css('option:checked')).getText();
  }

  async khacPhucLoiSelectLastOption() {
    await this.khacPhucLoiSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async khacPhucLoiSelectOption(option) {
    await this.khacPhucLoiSelect.sendKeys(option);
  }

  getKhacPhucLoiSelect(): ElementFinder {
    return this.khacPhucLoiSelect;
  }

  async getKhacPhucLoiSelectedOption() {
    return await this.khacPhucLoiSelect.element(by.css('option:checked')).getText();
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

export class GYCLKhacPhucDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-gYCLKhacPhuc-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-gYCLKhacPhuc'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
