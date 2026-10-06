import { element, by, ElementFinder } from 'protractor';

export class SysFunctionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sys-function div table .btn-danger'));
  title = element.all(by.css('jhi-sys-function div h2#page-heading span')).first();

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

export class SysFunctionUpdatePage {
  pageTitle = element(by.id('jhi-sys-function-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  captionInput = element(by.id('field_caption'));
  levelInput = element(by.id('field_level'));
  imageUriInput = element(by.id('field_imageUri'));
  eventNameInput = element(by.id('field_eventName'));
  hotkeyInput = element(by.id('field_hotkey'));
  sysPermissionSelect = element(by.id('field_sysPermission'));
  sysModuleSelect = element(by.id('field_sysModule'));

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

  async sysPermissionSelectLastOption() {
    await this.sysPermissionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sysPermissionSelectOption(option) {
    await this.sysPermissionSelect.sendKeys(option);
  }

  getSysPermissionSelect(): ElementFinder {
    return this.sysPermissionSelect;
  }

  async getSysPermissionSelectedOption() {
    return await this.sysPermissionSelect.element(by.css('option:checked')).getText();
  }

  async sysModuleSelectLastOption() {
    await this.sysModuleSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sysModuleSelectOption(option) {
    await this.sysModuleSelect.sendKeys(option);
  }

  getSysModuleSelect(): ElementFinder {
    return this.sysModuleSelect;
  }

  async getSysModuleSelectedOption() {
    return await this.sysModuleSelect.element(by.css('option:checked')).getText();
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

export class SysFunctionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sysFunction-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sysFunction'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
