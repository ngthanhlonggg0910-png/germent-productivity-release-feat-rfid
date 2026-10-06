import { element, by, ElementFinder } from 'protractor';

export class ProTaiKhoanCheckPointComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-tai-khoan-check-point div table .btn-danger'));
  title = element.all(by.css('jhi-pro-tai-khoan-check-point div h2#page-heading span')).first();

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

export class ProTaiKhoanCheckPointUpdatePage {
  pageTitle = element(by.id('jhi-pro-tai-khoan-check-point-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  taiKhoanInput = element(by.id('field_taiKhoan'));
  checkedInput = element(by.id('field_checked'));
  proCheckPointSelect = element(by.id('field_proCheckPoint'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTaiKhoanInput(taiKhoan) {
    await this.taiKhoanInput.sendKeys(taiKhoan);
  }

  async getTaiKhoanInput() {
    return await this.taiKhoanInput.getAttribute('value');
  }

  getCheckedInput() {
    return this.checkedInput;
  }

  async proCheckPointSelectLastOption() {
    await this.proCheckPointSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proCheckPointSelectOption(option) {
    await this.proCheckPointSelect.sendKeys(option);
  }

  getProCheckPointSelect(): ElementFinder {
    return this.proCheckPointSelect;
  }

  async getProCheckPointSelectedOption() {
    return await this.proCheckPointSelect.element(by.css('option:checked')).getText();
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

export class ProTaiKhoanCheckPointDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proTaiKhoanCheckPoint-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proTaiKhoanCheckPoint'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
