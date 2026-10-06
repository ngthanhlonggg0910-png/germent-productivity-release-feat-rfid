import { element, by, ElementFinder } from 'protractor';

export class ProNhaMayComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-nha-may div table .btn-danger'));
  title = element.all(by.css('jhi-pro-nha-may div h2#page-heading span')).first();

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

export class ProNhaMayUpdatePage {
  pageTitle = element(by.id('jhi-pro-nha-may-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenInput = element(by.id('field_ten'));
  sttInput = element(by.id('field_stt'));
  proCongTySelect = element(by.id('field_proCongTy'));

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

  async proCongTySelectLastOption() {
    await this.proCongTySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proCongTySelectOption(option) {
    await this.proCongTySelect.sendKeys(option);
  }

  getProCongTySelect(): ElementFinder {
    return this.proCongTySelect;
  }

  async getProCongTySelectedOption() {
    return await this.proCongTySelect.element(by.css('option:checked')).getText();
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

export class ProNhaMayDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proNhaMay-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proNhaMay'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
