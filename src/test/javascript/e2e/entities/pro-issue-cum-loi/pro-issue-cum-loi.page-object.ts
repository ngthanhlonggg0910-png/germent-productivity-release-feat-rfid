import { element, by, ElementFinder } from 'protractor';

export class ProIssueCumLoiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-issue-cum-loi div table .btn-danger'));
  title = element.all(by.css('jhi-pro-issue-cum-loi div h2#page-heading span')).first();

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

export class ProIssueCumLoiUpdatePage {
  pageTitle = element(by.id('jhi-pro-issue-cum-loi-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenInput = element(by.id('field_ten'));
  mauInput = element(by.id('field_mau'));
  toTruongInput = element(by.id('field_toTruong'));
  kyThuatInput = element(by.id('field_kyThuat'));
  qaInput = element(by.id('field_qa'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenInput(ten) {
    await this.tenInput.sendKeys(ten);
  }

  async getTenInput() {
    return await this.tenInput.getAttribute('value');
  }

  async setMauInput(mau) {
    await this.mauInput.sendKeys(mau);
  }

  async getMauInput() {
    return await this.mauInput.getAttribute('value');
  }

  getToTruongInput() {
    return this.toTruongInput;
  }
  getKyThuatInput() {
    return this.kyThuatInput;
  }
  getQaInput() {
    return this.qaInput;
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

export class ProIssueCumLoiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proIssueCumLoi-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proIssueCumLoi'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
