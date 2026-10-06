import { element, by, ElementFinder } from 'protractor';

export class ProIssueLoiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-issue-loi div table .btn-danger'));
  title = element.all(by.css('jhi-pro-issue-loi div h2#page-heading span')).first();

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

export class ProIssueLoiUpdatePage {
  pageTitle = element(by.id('jhi-pro-issue-loi-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  maLoiInput = element(by.id('field_maLoi'));
  cusCodeInput = element(by.id('field_cusCode'));
  tenLoiInput = element(by.id('field_tenLoi'));
  tenTiengAnhInput = element(by.id('field_tenTiengAnh'));
  labelInput = element(by.id('field_label'));
  sttInput = element(by.id('field_stt'));
  mauSacInput = element(by.id('field_mauSac'));
  ghiChuInput = element(by.id('field_ghiChu'));
  proIssueCumLoiSelect = element(by.id('field_proIssueCumLoi'));

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

  async setGhiChuInput(ghiChu) {
    await this.ghiChuInput.sendKeys(ghiChu);
  }

  async getGhiChuInput() {
    return await this.ghiChuInput.getAttribute('value');
  }

  async proIssueCumLoiSelectLastOption() {
    await this.proIssueCumLoiSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proIssueCumLoiSelectOption(option) {
    await this.proIssueCumLoiSelect.sendKeys(option);
  }

  getProIssueCumLoiSelect(): ElementFinder {
    return this.proIssueCumLoiSelect;
  }

  async getProIssueCumLoiSelectedOption() {
    return await this.proIssueCumLoiSelect.element(by.css('option:checked')).getText();
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

export class ProIssueLoiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proIssueLoi-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proIssueLoi'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
