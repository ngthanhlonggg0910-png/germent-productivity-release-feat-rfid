import { element, by, ElementFinder } from 'protractor';

export class ProFinalChiTietLoiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-final-chi-tiet-loi div table .btn-danger'));
  title = element.all(by.css('jhi-pro-final-chi-tiet-loi div h2#page-heading span')).first();

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

export class ProFinalChiTietLoiUpdatePage {
  pageTitle = element(by.id('jhi-pro-final-chi-tiet-loi-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  maLoiInput = element(by.id('field_maLoi'));
  thoiGianInput = element(by.id('field_thoiGian'));
  dxInput = element(by.id('field_dx'));
  dyInput = element(by.id('field_dy'));
  isFrontInput = element(by.id('field_isFront'));
  isInsideInput = element(by.id('field_isInside'));
  warningInput = element(by.id('field_warning'));
  nguoiKiemInput = element(by.id('field_nguoiKiem'));
  proFinalSelect = element(by.id('field_proFinal'));
  proLoiSelect = element(by.id('field_proLoi'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMaLoiInput(maLoi) {
    await this.maLoiInput.sendKeys(maLoi);
  }

  async getMaLoiInput() {
    return await this.maLoiInput.getAttribute('value');
  }

  async setThoiGianInput(thoiGian) {
    await this.thoiGianInput.sendKeys(thoiGian);
  }

  async getThoiGianInput() {
    return await this.thoiGianInput.getAttribute('value');
  }

  async setDxInput(dx) {
    await this.dxInput.sendKeys(dx);
  }

  async getDxInput() {
    return await this.dxInput.getAttribute('value');
  }

  async setDyInput(dy) {
    await this.dyInput.sendKeys(dy);
  }

  async getDyInput() {
    return await this.dyInput.getAttribute('value');
  }

  getIsFrontInput() {
    return this.isFrontInput;
  }
  getIsInsideInput() {
    return this.isInsideInput;
  }
  getWarningInput() {
    return this.warningInput;
  }
  async setNguoiKiemInput(nguoiKiem) {
    await this.nguoiKiemInput.sendKeys(nguoiKiem);
  }

  async getNguoiKiemInput() {
    return await this.nguoiKiemInput.getAttribute('value');
  }

  async proFinalSelectLastOption() {
    await this.proFinalSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proFinalSelectOption(option) {
    await this.proFinalSelect.sendKeys(option);
  }

  getProFinalSelect(): ElementFinder {
    return this.proFinalSelect;
  }

  async getProFinalSelectedOption() {
    return await this.proFinalSelect.element(by.css('option:checked')).getText();
  }

  async proLoiSelectLastOption() {
    await this.proLoiSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proLoiSelectOption(option) {
    await this.proLoiSelect.sendKeys(option);
  }

  getProLoiSelect(): ElementFinder {
    return this.proLoiSelect;
  }

  async getProLoiSelectedOption() {
    return await this.proLoiSelect.element(by.css('option:checked')).getText();
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

export class ProFinalChiTietLoiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proFinalChiTietLoi-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proFinalChiTietLoi'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
