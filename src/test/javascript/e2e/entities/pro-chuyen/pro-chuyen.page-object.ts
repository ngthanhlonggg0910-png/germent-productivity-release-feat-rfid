import { element, by, ElementFinder } from 'protractor';

export class ProChuyenComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-chuyen div table .btn-danger'));
  title = element.all(by.css('jhi-pro-chuyen div h2#page-heading span')).first();

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

export class ProChuyenUpdatePage {
  pageTitle = element(by.id('jhi-pro-chuyen-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenInput = element(by.id('field_ten'));
  sttInput = element(by.id('field_stt'));
  tenLoaiChuyenInput = element(by.id('field_tenLoaiChuyen'));
  proLoaiChuyenSelect = element(by.id('field_proLoaiChuyen'));
  proNhaMaySelect = element(by.id('field_proNhaMay'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenInput(ten) {
    await this.tenInput.sendKeys(ten);
  }

  async getTenInput() {
    return await this.tenInput.getAttribute('value');
  }

  async setSttInput(stt) {
    await this.sttInput.sendKeys(stt);
  }

  async getSttInput() {
    return await this.sttInput.getAttribute('value');
  }

  async setTenLoaiChuyenInput(tenLoaiChuyen) {
    await this.tenLoaiChuyenInput.sendKeys(tenLoaiChuyen);
  }

  async getTenLoaiChuyenInput() {
    return await this.tenLoaiChuyenInput.getAttribute('value');
  }

  async proLoaiChuyenSelectLastOption() {
    await this.proLoaiChuyenSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proLoaiChuyenSelectOption(option) {
    await this.proLoaiChuyenSelect.sendKeys(option);
  }

  getProLoaiChuyenSelect(): ElementFinder {
    return this.proLoaiChuyenSelect;
  }

  async getProLoaiChuyenSelectedOption() {
    return await this.proLoaiChuyenSelect.element(by.css('option:checked')).getText();
  }

  async proNhaMaySelectLastOption() {
    await this.proNhaMaySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proNhaMaySelectOption(option) {
    await this.proNhaMaySelect.sendKeys(option);
  }

  getProNhaMaySelect(): ElementFinder {
    return this.proNhaMaySelect;
  }

  async getProNhaMaySelectedOption() {
    return await this.proNhaMaySelect.element(by.css('option:checked')).getText();
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

export class ProChuyenDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proChuyen-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proChuyen'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
