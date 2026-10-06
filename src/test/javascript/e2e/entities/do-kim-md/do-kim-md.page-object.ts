import { element, by, ElementFinder } from 'protractor';

export class DoKimMdComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-do-kim-md div table .btn-danger'));
  title = element.all(by.css('jhi-do-kim-md div h2#page-heading span')).first();

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

export class DoKimMdUpdatePage {
  pageTitle = element(by.id('jhi-do-kim-md-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  valueInput = element(by.id('field_value'));
  displayInput = element(by.id('field_display'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setValueInput(value) {
    await this.valueInput.sendKeys(value);
  }

  async getValueInput() {
    return await this.valueInput.getAttribute('value');
  }

  async setDisplayInput(display) {
    await this.displayInput.sendKeys(display);
  }

  async getDisplayInput() {
    return await this.displayInput.getAttribute('value');
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

export class DoKimMdDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-doKimMd-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-doKimMd'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
