import { element, by, ElementFinder } from 'protractor';

export class ProCheckPointComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-check-point div table .btn-danger'));
  title = element.all(by.css('jhi-pro-check-point div h2#page-heading span')).first();

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

export class ProCheckPointUpdatePage {
  pageTitle = element(by.id('jhi-pro-check-point-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenInput = element(by.id('field_ten'));
  proChuyenSelect = element(by.id('field_proChuyen'));
  proCongDoanSelect = element(by.id('field_proCongDoan'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenInput(ten) {
    await this.tenInput.sendKeys(ten);
  }

  async getTenInput() {
    return await this.tenInput.getAttribute('value');
  }

  async proChuyenSelectLastOption() {
    await this.proChuyenSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proChuyenSelectOption(option) {
    await this.proChuyenSelect.sendKeys(option);
  }

  getProChuyenSelect(): ElementFinder {
    return this.proChuyenSelect;
  }

  async getProChuyenSelectedOption() {
    return await this.proChuyenSelect.element(by.css('option:checked')).getText();
  }

  async proCongDoanSelectLastOption() {
    await this.proCongDoanSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proCongDoanSelectOption(option) {
    await this.proCongDoanSelect.sendKeys(option);
  }

  getProCongDoanSelect(): ElementFinder {
    return this.proCongDoanSelect;
  }

  async getProCongDoanSelectedOption() {
    return await this.proCongDoanSelect.element(by.css('option:checked')).getText();
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

export class ProCheckPointDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proCheckPoint-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proCheckPoint'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
