import { element, by, ElementFinder } from 'protractor';

export class KhacPhucLoiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-khac-phuc-loi div table .btn-danger'));
  title = element.all(by.css('jhi-khac-phuc-loi div h2#page-heading span')).first();

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

export class KhacPhucLoiUpdatePage {
  pageTitle = element(by.id('jhi-khac-phuc-loi-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  maKPInput = element(by.id('field_maKP'));
  tenKPInput = element(by.id('field_tenKP'));
  nguoiTaoInput = element(by.id('field_nguoiTao'));
  ngayTaoInput = element(by.id('field_ngayTao'));
  userIdInput = element(by.id('field_userId'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMaKPInput(maKP) {
    await this.maKPInput.sendKeys(maKP);
  }

  async getMaKPInput() {
    return await this.maKPInput.getAttribute('value');
  }

  async setTenKPInput(tenKP) {
    await this.tenKPInput.sendKeys(tenKP);
  }

  async getTenKPInput() {
    return await this.tenKPInput.getAttribute('value');
  }

  async setNguoiTaoInput(nguoiTao) {
    await this.nguoiTaoInput.sendKeys(nguoiTao);
  }

  async getNguoiTaoInput() {
    return await this.nguoiTaoInput.getAttribute('value');
  }

  async setNgayTaoInput(ngayTao) {
    await this.ngayTaoInput.sendKeys(ngayTao);
  }

  async getNgayTaoInput() {
    return await this.ngayTaoInput.getAttribute('value');
  }

  async setUserIdInput(userId) {
    await this.userIdInput.sendKeys(userId);
  }

  async getUserIdInput() {
    return await this.userIdInput.getAttribute('value');
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

export class KhacPhucLoiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-khacPhucLoi-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-khacPhucLoi'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
