import { element, by, ElementFinder } from 'protractor';

export class ThuVienLoiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-thu-vien-loi div table .btn-danger'));
  title = element.all(by.css('jhi-thu-vien-loi div h2#page-heading span')).first();

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

export class ThuVienLoiUpdatePage {
  pageTitle = element(by.id('jhi-thu-vien-loi-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  maLoiInput = element(by.id('field_maLoi'));
  tenLoiInput = element(by.id('field_tenLoi'));
  nguoiTaoInput = element(by.id('field_nguoiTao'));
  ngayTaoInput = element(by.id('field_ngayTao'));
  userIdInput = element(by.id('field_userId'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMaLoiInput(maLoi) {
    await this.maLoiInput.sendKeys(maLoi);
  }

  async getMaLoiInput() {
    return await this.maLoiInput.getAttribute('value');
  }

  async setTenLoiInput(tenLoi) {
    await this.tenLoiInput.sendKeys(tenLoi);
  }

  async getTenLoiInput() {
    return await this.tenLoiInput.getAttribute('value');
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

export class ThuVienLoiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-thuVienLoi-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-thuVienLoi'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
