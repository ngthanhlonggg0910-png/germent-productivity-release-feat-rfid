import { element, by, ElementFinder } from 'protractor';

export class DungSaiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-dung-sai div table .btn-danger'));
  title = element.all(by.css('jhi-dung-sai div h2#page-heading span')).first();

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

export class DungSaiUpdatePage {
  pageTitle = element(by.id('jhi-dung-sai-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  cmLabelInput = element(by.id('field_cmLabel'));
  inchLabelInput = element(by.id('field_inchLabel'));
  cmValueInput = element(by.id('field_cmValue'));
  inchValueInput = element(by.id('field_inchValue'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCmLabelInput(cmLabel) {
    await this.cmLabelInput.sendKeys(cmLabel);
  }

  async getCmLabelInput() {
    return await this.cmLabelInput.getAttribute('value');
  }

  async setInchLabelInput(inchLabel) {
    await this.inchLabelInput.sendKeys(inchLabel);
  }

  async getInchLabelInput() {
    return await this.inchLabelInput.getAttribute('value');
  }

  async setCmValueInput(cmValue) {
    await this.cmValueInput.sendKeys(cmValue);
  }

  async getCmValueInput() {
    return await this.cmValueInput.getAttribute('value');
  }

  async setInchValueInput(inchValue) {
    await this.inchValueInput.sendKeys(inchValue);
  }

  async getInchValueInput() {
    return await this.inchValueInput.getAttribute('value');
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

export class DungSaiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dungSai-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dungSai'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
