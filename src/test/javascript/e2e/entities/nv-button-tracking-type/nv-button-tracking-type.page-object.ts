import { element, by, ElementFinder } from 'protractor';

export class NVButtonTrackingTypeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-nv-button-tracking-type div table .btn-danger'));
  title = element.all(by.css('jhi-nv-button-tracking-type div h2#page-heading span')).first();

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

export class NVButtonTrackingTypeUpdatePage {
  pageTitle = element(by.id('jhi-nv-button-tracking-type-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  numberOfStateInput = element(by.id('field_numberOfState'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setNumberOfStateInput(numberOfState) {
    await this.numberOfStateInput.sendKeys(numberOfState);
  }

  async getNumberOfStateInput() {
    return await this.numberOfStateInput.getAttribute('value');
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

export class NVButtonTrackingTypeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-nVButtonTrackingType-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-nVButtonTrackingType'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
