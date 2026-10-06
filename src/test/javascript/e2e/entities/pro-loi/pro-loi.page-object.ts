import { element, by, ElementFinder } from 'protractor';

export class ProLoiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-loi div table .btn-danger'));
  title = element.all(by.css('jhi-pro-loi div h2#page-heading span')).first();

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

export class ProLoiUpdatePage {
  pageTitle = element(by.id('jhi-pro-loi-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  maLoiInput = element(by.id('field_maLoi'));
  cusCodeInput = element(by.id('field_cusCode'));
  tenLoiInput = element(by.id('field_tenLoi'));
  tenTiengAnhInput = element(by.id('field_tenTiengAnh'));
  labelInput = element(by.id('field_label'));
  sttInput = element(by.id('field_stt'));
  mauSacInput = element(by.id('field_mauSac'));
  qcInput = element(by.id('field_qc'));
  qaInput = element(by.id('field_qa'));
  checkedInput = element(by.id('field_checked'));
  ghiChuInput = element(by.id('field_ghiChu'));
  proCumLoiSelect = element(by.id('field_proCumLoi'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMaLoiInput(maLoi) {
    await this.maLoiInput.sendKeys(maLoi);
  }

  async getMaLoiInput() {
    return await this.maLoiInput.getAttribute('value');
  }

  async setCusCodeInput(cusCode) {
    await this.cusCodeInput.sendKeys(cusCode);
  }

  async getCusCodeInput() {
    return await this.cusCodeInput.getAttribute('value');
  }

  async setTenLoiInput(tenLoi) {
    await this.tenLoiInput.sendKeys(tenLoi);
  }

  async getTenLoiInput() {
    return await this.tenLoiInput.getAttribute('value');
  }

  async setTenTiengAnhInput(tenTiengAnh) {
    await this.tenTiengAnhInput.sendKeys(tenTiengAnh);
  }

  async getTenTiengAnhInput() {
    return await this.tenTiengAnhInput.getAttribute('value');
  }

  async setLabelInput(label) {
    await this.labelInput.sendKeys(label);
  }

  async getLabelInput() {
    return await this.labelInput.getAttribute('value');
  }

  async setSttInput(stt) {
    await this.sttInput.sendKeys(stt);
  }

  async getSttInput() {
    return await this.sttInput.getAttribute('value');
  }

  async setMauSacInput(mauSac) {
    await this.mauSacInput.sendKeys(mauSac);
  }

  async getMauSacInput() {
    return await this.mauSacInput.getAttribute('value');
  }

  getQcInput() {
    return this.qcInput;
  }
  getQaInput() {
    return this.qaInput;
  }
  getCheckedInput() {
    return this.checkedInput;
  }
  async setGhiChuInput(ghiChu) {
    await this.ghiChuInput.sendKeys(ghiChu);
  }

  async getGhiChuInput() {
    return await this.ghiChuInput.getAttribute('value');
  }

  async proCumLoiSelectLastOption() {
    await this.proCumLoiSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proCumLoiSelectOption(option) {
    await this.proCumLoiSelect.sendKeys(option);
  }

  getProCumLoiSelect(): ElementFinder {
    return this.proCumLoiSelect;
  }

  async getProCumLoiSelectedOption() {
    return await this.proCumLoiSelect.element(by.css('option:checked')).getText();
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

export class ProLoiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proLoi-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proLoi'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
