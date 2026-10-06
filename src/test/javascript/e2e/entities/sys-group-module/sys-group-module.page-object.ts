import { element, by, ElementFinder } from 'protractor';

export class SysGroupModuleComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sys-group-module div table .btn-danger'));
  title = element.all(by.css('jhi-sys-group-module div h2#page-heading span')).first();

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

export class SysGroupModuleUpdatePage {
  pageTitle = element(by.id('jhi-sys-group-module-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  captionInput = element(by.id('field_caption'));
  primaryImageUriInput = element(by.id('field_primaryImageUri'));
  secondaryImageUriInput = element(by.id('field_secondaryImageUri'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setCaptionInput(caption) {
    await this.captionInput.sendKeys(caption);
  }

  async getCaptionInput() {
    return await this.captionInput.getAttribute('value');
  }

  async setPrimaryImageUriInput(primaryImageUri) {
    await this.primaryImageUriInput.sendKeys(primaryImageUri);
  }

  async getPrimaryImageUriInput() {
    return await this.primaryImageUriInput.getAttribute('value');
  }

  async setSecondaryImageUriInput(secondaryImageUri) {
    await this.secondaryImageUriInput.sendKeys(secondaryImageUri);
  }

  async getSecondaryImageUriInput() {
    return await this.secondaryImageUriInput.getAttribute('value');
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

export class SysGroupModuleDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sysGroupModule-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sysGroupModule'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
