import { element, by, ElementFinder } from 'protractor';

export class SysPermissionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sys-permission div table .btn-danger'));
  title = element.all(by.css('jhi-sys-permission div h2#page-heading span')).first();

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

export class SysPermissionUpdatePage {
  pageTitle = element(by.id('jhi-sys-permission-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  captionInput = element(by.id('field_caption'));
  levelInput = element(by.id('field_level'));
  imageUriInput = element(by.id('field_imageUri'));
  eventNameInput = element(by.id('field_eventName'));
  hotkeyInput = element(by.id('field_hotkey'));

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

  async setLevelInput(level) {
    await this.levelInput.sendKeys(level);
  }

  async getLevelInput() {
    return await this.levelInput.getAttribute('value');
  }

  async setImageUriInput(imageUri) {
    await this.imageUriInput.sendKeys(imageUri);
  }

  async getImageUriInput() {
    return await this.imageUriInput.getAttribute('value');
  }

  async setEventNameInput(eventName) {
    await this.eventNameInput.sendKeys(eventName);
  }

  async getEventNameInput() {
    return await this.eventNameInput.getAttribute('value');
  }

  async setHotkeyInput(hotkey) {
    await this.hotkeyInput.sendKeys(hotkey);
  }

  async getHotkeyInput() {
    return await this.hotkeyInput.getAttribute('value');
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

export class SysPermissionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sysPermission-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sysPermission'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
