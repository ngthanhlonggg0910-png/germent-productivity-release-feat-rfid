import { element, by, ElementFinder } from 'protractor';

export class SoLuongHangKiemComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-so-luong-hang-kiem div table .btn-danger'));
  title = element.all(by.css('jhi-so-luong-hang-kiem div h2#page-heading span')).first();

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

export class SoLuongHangKiemUpdatePage {
  pageTitle = element(by.id('jhi-so-luong-hang-kiem-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  soLuongInput = element(by.id('field_soLuong'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSoLuongInput(soLuong) {
    await this.soLuongInput.sendKeys(soLuong);
  }

  async getSoLuongInput() {
    return await this.soLuongInput.getAttribute('value');
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

export class SoLuongHangKiemDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-soLuongHangKiem-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-soLuongHangKiem'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
