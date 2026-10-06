import { element, by, ElementFinder } from 'protractor';

export class SysFileComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sys-file div table .btn-danger'));
  title = element.all(by.css('jhi-sys-file div h2#page-heading span')).first();

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

export class SysFileUpdatePage {
  pageTitle = element(by.id('jhi-sys-file-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  fileNameInput = element(by.id('field_fileName'));
  fileUriInput = element(by.id('field_fileUri'));
  fileExtensionInput = element(by.id('field_fileExtension'));
  sizeInput = element(by.id('field_size'));
  uploadedAtInput = element(by.id('field_uploadedAt'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setFileNameInput(fileName) {
    await this.fileNameInput.sendKeys(fileName);
  }

  async getFileNameInput() {
    return await this.fileNameInput.getAttribute('value');
  }

  async setFileUriInput(fileUri) {
    await this.fileUriInput.sendKeys(fileUri);
  }

  async getFileUriInput() {
    return await this.fileUriInput.getAttribute('value');
  }

  async setFileExtensionInput(fileExtension) {
    await this.fileExtensionInput.sendKeys(fileExtension);
  }

  async getFileExtensionInput() {
    return await this.fileExtensionInput.getAttribute('value');
  }

  async setSizeInput(size) {
    await this.sizeInput.sendKeys(size);
  }

  async getSizeInput() {
    return await this.sizeInput.getAttribute('value');
  }

  async setUploadedAtInput(uploadedAt) {
    await this.uploadedAtInput.sendKeys(uploadedAt);
  }

  async getUploadedAtInput() {
    return await this.uploadedAtInput.getAttribute('value');
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

export class SysFileDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sysFile-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sysFile'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
